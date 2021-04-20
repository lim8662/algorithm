package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_1767_processor {
	
	private static int n, core, answer, count;
	private static int[][] map;
	private static ArrayList<int[]> list;
	private final static int[] dx = { -1, 0, 1, 0 };
	private final static int[] dy = { 0, 1, 0, -1 };
    
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			list = new ArrayList<>();
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 가장자리를 뺀 코어 리스트에 저장
					if (map[i][j] == 1 && i != 0 && i != n - 1 && j != 0 && j != n - 1)
						list.add(new int[] { i, j });
				}
			}
			core = 0;
			answer = Integer.MAX_VALUE;

			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void dfs(int depth, int c, int line) { //첫번째 코어부터 순서대로 모든 경우의 수에 대해 완전 탐색
		if (depth == list.size()) {
			if (core < c) { //더 많은 코어가 연결되었다면 갱신(최대 연결 규칙)
				core = c;
				answer = line;
			} else if (core == c) { // 코어수가 같다면 길이 비교해서 작은값 저장
				if (answer > line)
					answer = line;
			}
			return;
		}
		// 모든 방향 체크
		for (int d = 0; d < 4; d++) {
			if (isConnect(list.get(depth), d)) { // 연결 가능하면 
				fill(list.get(depth), d, 1); // 해당 방향으로 연결한 후 
				dfs(depth + 1, c + 1, line + count); // 연결된 코어 수 증가 시키고 다음 코어 탐색
				fill(list.get(depth), d, 0); // 백트래킹
			}
		}
		dfs(depth + 1, c, line); // 다음 코어 탐색

	}

	
	private static void fill(int[] index, int dir, int value) { //해당 방향으로 끝까지 채움
		count = 0; // 전선 길이
		int x = index[0] + dx[dir];
		int y = index[1] + dy[dir];
		
		while (x >= 0 && y >= 0 && x < n && y < n) {
			map[x][y] = value;
			count++;
			x = x + dx[dir];
			y = y + dy[dir];
		}
	}
	
	private static boolean isConnect(int[] index, int dir) { //해당 방향으로 연결 가능한지 확인
		int x = index[0] + dx[dir];
		int y = index[1] + dy[dir];
		
		while (x >= 0 && y >= 0 && x < n && y < n) {
			if (map[x][y] != 0)
				return false;
			x = x + dx[dir];
			y = y + dy[dir];
		}
		return true;
	}

}
