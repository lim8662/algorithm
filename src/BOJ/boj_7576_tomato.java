package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7576_tomato {

	static int C, R, time;
	static int[][] tomato;
	static boolean[][] visited;
	static Queue<Integer> q = new ArrayDeque<Integer>(); // 익은 토마토 위치 저장
	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); // 가로 크기
		R = Integer.parseInt(st.nextToken()); // 세로 크기
		tomato = new int[R][C];
		visited = new boolean[R][C];
			
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				tomato[r][c] = Integer.parseInt(st.nextToken());
				if(tomato[r][c] == 1) {
					q.add(r*1000 + c); visited[r][c] = true;
				}			
			}
		}
		ripe();		
		
		int cnt = 0; // 모두 익었는지 체크
		for (int r = 0; r < R; r++) 
		for (int c = 0; c < C; c++) {
			if(tomato[r][c] == 0) cnt++;
		}
		if(cnt > 0) System.out.println(-1); // 안익은 토마토가 있으면
		else System.out.println(time); // 모두 익었다면
	}

	private static void ripe() {
		int cur = 0, r = 0, c = 0, size = 0, nr = 0, nc = 0;
		
		while(!q.isEmpty()) {
			

			size = q.size();
			for (int i = 0; i < size; i++) {
				cur = q.poll(); // 익은 과일
				r = cur / 1000;
				c = cur % 1000;
			
				for (int d = 0; d < 4; d++) { // 4방탐색
					nr = r + dx[d];
					nc = c + dy[d];
					
					if (nr >= R || nr < 0 || nc >= C || nc < 0 ||
						visited[nr][nc] || tomato[nr][nc] < 0) 
							continue;

					if(tomato[nr][nc] == 0) { // 안익은 토마토 익힘
						q.add(nr * 1000 + nc);
						tomato[nr][nc] = 1;
						visited[nr][nc] = true;
					}
				}		
			}
			// 추가로 익은 토마토가 없다면 종료
			if(q.isEmpty()) return;
			
			// 시간 경과
			time++;
		}
	}
}
