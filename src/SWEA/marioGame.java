package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class marioGame {
	
	static int N;
	static int[] score, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/SWEA/mario.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N+6];
		dp = new int[N+6];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = -1000000;
		//(n + 1) ~ (n + 5)
		for (int i = 1; i <=5; i++) {
			int ret = getPoint(N + i);
			if(ret > max) max = ret;
		}
		
		System.out.println(max);
	}

	private static int getPoint(int n) {
		if(n == 0) return 0;
		if(n < 0) return -100000;
		if(dp[n] != 0) return dp[n];
		
		int a = getPoint(n - 2);
		int b = getPoint(n - 7);
		
		int ret = Math.max(a, b);
		dp[n] = ret + score[n];
		return dp[n];
	}
	
	
	

	
}
