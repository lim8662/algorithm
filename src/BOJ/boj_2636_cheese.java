package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2636_cheese {
	static int R, C, size, cnt, time;
	static int[][] map;
	static boolean[][] visited;
	
	static final int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // 4방 탐색
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) size++;
			}
		}
		
		while(size > 0) { // 치즈 녹이기
			time++;
			bfs(); // bfs로 공기와 접촉한 치즈 녹임
			if(size == cnt) // 모두 녹았다면 출력
				System.out.printf("%d\n%d\n", time, size);		
			size -= cnt; // 치즈 크기 최신화
		}
	}
	
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		visited = new boolean[R][C];
		cnt = 0; // 녹은 치즈 개수
		
		q.add(0); // 0,0 부터 탐색
		visited[0][0] = true;
		
		int cur = 0, cx = 0, cy = 0, nx = 0, ny = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			cx = cur / 100;
			cy = cur % 100;
			
			for (int d = 0; d < 4; d++) { // 4방탐색하여 치즈가 있다면 녹이고 빈공간만 추가 탐색
				nx = cx + dir[d][0];
				ny = cy + dir[d][1];
				if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue; //경계를 넘거나 방문했다면 넘김
				
				visited[nx][ny] = true;
				if(map[nx][ny] > 0) { // 치즈면 녹임
					map[nx][ny] = 0;
					cnt++;
				} else q.add(nx*100 + ny); // 빈 공간이면 이동
		
			}
		}
	}
}
