package BOJ;

import java.io.*;
import java.util.*;

public class boj_1003 {
	//피보나치 함수
	
	static int cntZero = 0;
	static int cntOne = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		StringBuilder out = new StringBuilder();
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			cntZero = 0;
			cntOne = 0;
			fibonacci(N);
			out.append(cntZero + " " + cntOne + "\n");
		}

		
		
		System.out.println(out.toString());
	}
	
	public static int fibonacci(int n) {
		if(n==0) {
			cntZero++;
			return 0;
		} 
		return fibonacci(n-1) + fibonacci(n-2);
	}
}
