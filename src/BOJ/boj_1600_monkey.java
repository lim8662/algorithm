package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1600_monkey {
	static int K, R, C, start, end, step;
	static int[][] map;
	static boolean[][][] visited;
	static final int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}, {2,1}, {1,2}, {-2,1}, {-1,2}, {1,-2}, {2,-1}, {-1,-2}, {-2,-1}}; // 8방 탐색
	
	static class Monkey {
		int x, y, k, move;

		public Monkey(int x, int y, int k, int move) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.move = move;
		}		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		K = Integer.parseInt(br.readLine()); // 말 이동 가능 회수
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); 
		R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		//end = (R-1) * 1000 + (C-1); // 도착 좌표
		
		for (int i = 0; i < R; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		int nx = 0, ny = 0, nk = 0, moveCnt = 0;
		Monkey cur = null;
		visited = new boolean[R][C][K+1];
		
		Queue<Monkey> q = new ArrayDeque<>(); // 현 좌표와 남은 말이동 횟수를 저장
		visited[0][0][K] = true;
		q.add(new Monkey(0, 0, K, 0)); // 0,0 부터 출발
		
		while (!q.isEmpty()) {
			cur = q.poll(); // 현 위치

			moveCnt = cur.move;
			if(cur.x == R -1 && cur.y == C-1) return moveCnt; // 도착했으면 종료
			
			for (int d = 0; d < 12; d++) { // 이동 가능한 12가지 위치 고려
				if(d >= 4 && cur.k == 0) break; 
				nx = cur.x + dir[d][0];
				ny = cur.y + dir[d][1];
				nk = d >= 4 ? cur.k -1 : cur.k; // 남은 말이동 횟수
				
				if(nx < 0 || nx >= R || ny < 0 || ny >= C // 경계를 넘거나 
						|| visited[nx][ny][nk] || map[nx][ny] == 1) continue; // 방문했거나 장애물이 있으면 넘김
				
				q.add(new Monkey(nx, ny, nk, moveCnt+1)); // 이동
				visited[nx][ny][nk] = true;	 	
			}
		}
		return -1; // 도착 불가능 하면
	}
}
