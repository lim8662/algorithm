package BOJ;

import java.io.*;
import java.util.*;

public class boj_1003 {
	//피보나치 함수
	static Integer[][] dp = new Integer[41][2];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		StringBuilder out = new StringBuilder();
		
		//초기화
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			fibonacci(N);
			out.append(dp[N][0] + " " + dp[N][1] + "\n");
		}

		System.out.println(out.toString());
	}
	
	public static Integer[] fibonacci(int n) {
		
		if(dp[n][0] == null || dp[n][1] == null) {
			dp[n][0] = fibonacci(n-1)[0] + fibonacci(n-2)[0];
			dp[n][1] = fibonacci(n-1)[1] + fibonacci(n-2)[1];
		}
		
		return dp[n];
	}
}
