package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2011_passcode {

	static final int mod = 1000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int n = s.length();
		s = " " + s;
		int[] dp = new int[n+1];
		dp[0] = 1;
		
		for (int i = 1; i <= n; i++) {
			int cur = s.charAt(i) - '0';
			
			// 한자리 암호로 해석할 수 있다면
			if(1 <= cur && cur <= 9) {
				dp[i] += dp[i-1];
				dp[i] %= mod;
			}
			
			if(i == 1) continue;
			
			int pre = s.charAt(i-1) - '0';
			
			if(pre == 0) continue;
			
			cur = pre * 10 + cur;
			// 두자리 암호로 해석할 수 있다면
			if(10 <= cur && cur <= 26) {
				dp[i] += dp[i-2];
				dp[i] %= mod;
			}	
		}
		System.out.println(dp[n]);
	}
}
