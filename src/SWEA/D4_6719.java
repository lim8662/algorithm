package SWEA;

import java.util.Arrays;
import java.util.Scanner;

class D4_6719 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int M[] = new int[N];
			float A = 0;
			
			for (int i = 0; i < N; i++)
				M[i] = sc.nextInt();
			Arrays.sort(M);
			
			for (int i = N - K; i < N; i++) {
				A = (A + M[i]) / 2 ;
			}
			System.out.printf("#%d %f\n", tc, A);
		}
	}
}