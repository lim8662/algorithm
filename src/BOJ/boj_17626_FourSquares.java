package BOJ;

import java.io.*;
import java.util.*;

public class boj_17626_FourSquares {
	// 10분 설계 20분 구현 -greedy
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		
		brute(N);
		dp(N);
		
	}

	private static void dp(int n) {
		int[] dp = new int[n+1];
		
		dp[0] = 0; dp[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			int min = 100000;
			for (int j = 1; j*j < i; j++) {
				min = Math.min(min, dp[i-j*j]);
			}
			dp[i] = min + 1;
		}
		System.out.println(dp[n]);
		
	}

	private static void brute(int n) {
		int[] min = new int[50001];
		int sqn = 0;
		int max = 0;
		
		for (int i = 1; i < 300; i++) { // 제곱수 1개로 표현가능한 자연수들 저장
			sqn = i * i;
			if(sqn > 50000) {
				max = i-1;
				break;
			}
			min[sqn] = 1; 
		}
		// 제곱수 2개로 표현 가능한 자연수들 저장
		for (int i = 1; i <= max; i++) {
			for (int j = 1; j <= max; j++) {
			sqn = (i*i) + (j*j);
			if(sqn > 50000) break;
			if(min[sqn] == 0) min[sqn] = 2;
			}
		}
		
		// 제곱수 3개로 표현 가능한 자연수들 저장
		for (int i = 1; i <= max; i++) 
		for (int j = 1; j <= max; j++) 
		for (int k = 1; k <= max; k++){
			sqn = (i*i) + (j*j) + (k*k);
			if(sqn > 50000) break;
			if( min[sqn] == 0 ) min[sqn] = 3;
		}
		
		if(min[n] == 0) System.out.println(4);
		else System.out.println(min[n]);
	}
}
