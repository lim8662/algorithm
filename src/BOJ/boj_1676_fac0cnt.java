package BOJ;

import java.io.*;
import java.util.*;

public class boj_1676_fac0cnt {
	
	// N!의 0의 수 (N <= 500
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		
		solve(N);
		
	}

	private static void solve(int n) {
		int cnt = 0;
		
		for (int i = 1; i <= n; i++) {
			if(i % 5 == 0) cnt++;
			if(i % 25 == 0) cnt++;
			if(i % 125 == 0) cnt++;
		}
		
		System.out.println(cnt);
	}
}


