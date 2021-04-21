package BOJ;

import java.io.*;
import java.util.*;

public class boj_11660_intervalSum {

	static int N, M;
	static int[][] map, dp;
	static StringBuilder out = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 맵 크기
		M = Integer.parseInt(st.nextToken()); // 연산 수
		map = new int[N][N];
		dp = new int[N][N];
				
		for (int i = 0; i < N; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cumulativeSum(); // 누적합 테이블 만들기

		int x1, y1, x2, y2;
		for (int i = 0; i < M; i++) { // 구간합 연산 입력
			st = new StringTokenizer(br.readLine());	
			x1 = Integer.parseInt(st.nextToken())-1;
			y1 = Integer.parseInt(st.nextToken())-1;
			x2 = Integer.parseInt(st.nextToken())-1;
			y2 = Integer.parseInt(st.nextToken())-1;
			intervalSum(x1,y1,x2,y2);			
		}
		System.out.println(out.toString());	
	}

	private static void intervalSum(int x1, int y1, int x2, int y2) {
		
		
		int sum = dp[x2][y2];
		if(y1 >= 1) sum -= dp[x2][y1-1];
		if(x1 >= 1) sum -= dp[x1-1][y2];
		if(x1 >= 1 && y1 >= 1) sum += dp[x1-1][y1-1];
		
		out.append(sum).append('\n');
	}

	private static void cumulativeSum() {
		dp[0][0] = map[0][0]; // 초기화
		
		for (int i = 1; i < dp.length; i++) { // 0행 0열 누적합
			dp[0][i] = dp[0][i-1] + map[0][i];
			dp[i][0] = dp[i-1][0] + map[i][0];
		}
		
		for (int r = 1; r < N; r++) { // 안쪽 누적합
			for (int c = 1; c < N; c++) {
				dp[r][c] = dp[r][c-1] + dp[r-1][c] - dp[r-1][c-1] + map[r][c];
			}
		}
	}
}
