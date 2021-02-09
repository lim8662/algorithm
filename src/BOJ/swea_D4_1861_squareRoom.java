package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_D4_1861_squareRoom {
	static int N;
	static int[][] room; // 방번호 저장
	static int[][] visited; // 방문한 방의 지나온 방의 갯수 저장
	static int start; // 출발점
	static int max; // 최대 방문한 방의 개수
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			room = new int[N][N];
			max = 0;
			start = 0;
			visited = new int[N][N];
			// 방번호 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 모든 방을 시작점으로 탐색
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if(visited[x][y] == 0) { 
						int cnt = dfs(x, y, 1); // (x,y)를 시작점으로 최대 방문 수
						// 방문을 더 많이 했다면 시작점 및 방문 수 갱신
						if(cnt > max) {
							start = room[x][y];
							max = cnt;
						}
						// 방문 수가 최대와 같다면 더 작은 방번호로 갱신
						else if (cnt == max) { 
							start = Math.min(start, room[x][y]);
						}
					}	
				}
			}
			// 출력
			System.out.printf("#%d %d %d\n", tc, start, max);
		}
	}
	// 깊이 우선 탐색
	private static int dfs(int x, int y, int cnt) {
		visited[x][y] = cnt;
		// 4방 탐색
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;	
			// 1 증가하는 방으로 방문(증감 무관하게 한경로의 길이는 같음)
			if(room[x][y] + 1 == room[nx][ny]) {
				if (visited[nx][ny] < cnt + 1) 
					return dfs(nx,ny, cnt+1);
			}
		}
		return cnt;	// 방문할 방이 없다면 현 경로 길이 반환
	}
}
