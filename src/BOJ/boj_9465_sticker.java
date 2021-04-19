package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9465_sticker {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] score, dp;
		
		for (int tc = 0; tc < T; tc++) {
			// 입력
			int N = Integer.parseInt(br.readLine());
			score = new int[2][N+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				score[0][i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				score[1][i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[2][N+1];
			dp[0][1] = score[0][1]; // 초기화
			dp[1][1] = score[1][1];
			
			for (int i = 2; i <= N; i++) { // 1칸 대각선 뒤, 2칸 대각선 뒤 중 큰 값을 더함
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + score[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + score[1][i];
			}
			
			System.out.println(Math.max(dp[0][N], dp[1][N]));
		}				
	}	
}
