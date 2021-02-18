package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2839_greedy {
	static int N, ans = -1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine());

		int remain1 = 0, remain2 = 0;		
		for(int i = 0; i <= N; i++) { // 5kg 봉지로 배달하는 양
			remain1 = N - (i * 5);
			
			for (int j = 0; j <= remain1; j++) { // 3kg 봉지로 배달하는 양
				remain2 = remain1 - (j * 3);
				
				if(remain2 == 0) { // 정확히 나누어 떨어지면
					if(ans < 0) ans = i + j;
					else ans = Math.min(ans, i + j);
				}
			}
		}		
		System.out.println(ans);
	}
}
