package SWEA;

import java.io.*;
import java.util.*;

class SWEA_1949 {
	// n 3~8, h 1~20, k 1~5 1���� �۰Ե� �ȼ� ����
	// ���� ���� ���츮���� ������ ���� �������� ���μ��� �̵�
	// �� �ѹ� k��ŭ ���� �� ����
	// ���� �� ���θ� ���
	private static int[][] map;
	private static boolean[][] visit;
	private static int n, ans;
	private static List<int[]> peak;
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			peak = new ArrayList<int[]>();
			ans = 0;
			int max = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (max < map[i][j]) { // �ְ�� ����
						max = map[i][j];
						peak.clear();
						peak.add(new int[] { i, j });
					} else if (max == map[i][j]) {
						peak.add(new int[] { i, j });
					}
				}
			}

			for (int i = 0; i < peak.size(); i++) { // �ְ�� ���� ��å�� Ž��
				visit = new boolean[n][n];
				dfs(peak.get(i), k, 1);
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	public static void dfs(int[] p, int k, int length) { // �� ��ǥ, ���� ����, ���� ����  // ������ ���� ���츮�� ���̸� �Ѱ��ִ� ��ĵ� ������
		if (ans < length)
			ans = length;

		int x = p[0];
		int y = p[1];
		visit[x][y] = true;
		
		for (int d = 0; d < 4; d++) { // 4���� Ž��
			int nx = p[0] + dx[d];
			int ny = p[1] + dy[d];
			
			if (nx < 0 || nx == n || ny < 0 || ny == n || visit[nx][ny]) // ��踦 �Ѱų� �̹� �湮�ߴٸ� ���� ��ȯ
				continue;

			if (map[x][y] > map[nx][ny]) { // �� ���ٸ� ��ĭ �̵�
				dfs(new int[] { nx, ny }, k, length + 1);

			} else if (k > 0 && map[x][y] > map[nx][ny] - k) { // ���� ���� ���� ��Ƽ� �̵��� ���� �ϴٸ�
				int tmp = map[nx][ny];
				map[nx][ny] = map[x][y] - 1;

				dfs(new int[] { nx, ny }, 0, length + 1); // ��� ��ĭ �̵�
				map[nx][ny] = tmp; // ��Ʈ��ŷ
			}
			visit[nx][ny] = false; // ��Ʈ��ŷ, ����� �ߺ��� ����ϱ� ����
		}
	}
}
