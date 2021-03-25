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
	static final int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0},  // 4방탐색
			{2,1}, {1,2}, {-2,1}, {-1,2}, {1,-2}, {2,-1}, {-1,-2}, {-2,-1}}; // 8방 탐색
	
	static class Monkey {
		int xy, k;

		public Monkey(int xy, int k) {
			this.xy = xy;
			this.k = k;
		}		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		K = Integer.parseInt(br.readLine()); // 말 이동 가능 회수
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		end = (R-1) * 1000 + (C-1); // 도착 좌표
		
		for (int i = 0; i < R; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();// bfs로 최소 이동 횟수 탐색
		
		System.out.println(step);
	}
	
	private static void bfs() {
		Queue<Monkey> q = new ArrayDeque<>(); // 현 좌표와 남은 말이동 횟수를 저장
		visited = new boolean[R][C][K+1];
		
		q.add(new Monkey(0, K)); // 0,0 부터 출발
		visited[0][0][K] = true;
		
		Monkey cur = null;
		int cx = 0, cy = 0, ch = 0, nx = 0, ny = 0;
		while (!q.isEmpty()) {
			
			int size = q.size();
			for(int s = 0; s < size; s++) { // 같은 이동 횟수의 좌표 탐색
				cur = q.poll(); // 현 위치
				if(cur.xy == end) return; // 도착했으면 종료
				
				cx = cur.xy / 1000;
				cy = cur.xy % 1000;
				ch = cur.k; // 남은 말이동 횟수
				
				for (int d = 0; d < 12; d++) { // 이동 가능한 12가지 위치 고려
					nx = cx + dir[d][0];
					ny = cy + dir[d][1];
					if(nx < 0 || nx >= R || ny < 0 || ny >= C // 경계를 넘거나 
							|| visited[nx][ny][ch] || map[nx][ny] == 1) continue; // 방문했거나 장애물이 있으면 넘김
					
					
					if(d >= 4) { // 말이동
						 if(ch == 0) break; // 말이동 횟수가 없다면 말이동은 고려 x
						 q.add(new Monkey(nx*1000 + ny, ch-1)); // 말이동 
						 visited[nx][ny][ch-1] = true;
					}
					else {
						 q.add(new Monkey(nx*1000 + ny, ch)); // 4방 이동
						 visited[nx][ny][ch] = true;
					}
				}
			}
			step++;	// 이동 횟수 증가
		}
		step = -1; // 도착 불가능 하면
	}
}
