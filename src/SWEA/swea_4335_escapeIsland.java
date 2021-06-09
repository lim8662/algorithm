package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_4335_escapeIsland {

	static int N, ans;
	static int[][] cubes;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 상자수 입력
			ans = 0;
			cubes = new int[N][3];
			
			for (int i = 0; i < N; i++) { // 각 상자의 변의 길이 입력받기 x, y, z
				st = new StringTokenizer(br.readLine());
				cubes[i][0] = Integer.parseInt(st.nextToken());
				cubes[i][1] = Integer.parseInt(st.nextToken());
				cubes[i][2] = Integer.parseInt(st.nextToken());
			}
			
			solve();
			
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	private static void solve() {
		
		
	}
	
}
