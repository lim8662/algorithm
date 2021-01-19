
import java.io.*;
import java.util.*;

class boj_4963 {
	// 섬의 개수
	// 섬과 바다의 지도에서 인접(대각선 포함)한 섬은 하나의 섬으로 취급하여
	// 독립된 섬의 갯수를 리턴
/*
5 4
1 0 1 0 0
1 0 0 0 0
1 0 1 0 1
1 0 0 1 0
0 0 
 */
	private static int[][] arr, check;
	private static int w, h;
	private static int[] dx = { 0, 0, 1, -1, 1, -1, 1, -1 };
	private static int[] dy = { -1, 1, 0, 0, -1, 1, 1, -1 };
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			arr = new int[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 0;
			check = new int[h][w];

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == 1 && check[i][j] == 0)
						dfs(i, j, ++count);
				}
			}
			System.out.println(count);
		}
	}

	private static void dfs(int x, int y, int count) {
		// bfs
		// Queue<int[]> queue = new LinkedList<>();
		// queue.add(new int[] { x, y });
		check[x][y] = count;

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < h && 0 <= ny && ny < w) {
				if (arr[nx][ny] == 1 && check[nx][ny] == 0)
					dfs(nx, ny, count);
			}
		}

	}
}
