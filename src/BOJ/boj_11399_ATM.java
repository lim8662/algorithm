package BOJ;

import java.io.*;
import java.util.*;

public class boj_11399_ATM {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(P);
		
		int[] sum = new int[N];
		sum[0] = P[0];
		
		for (int i = 1; i < N; i++) 
			sum[i] = P[i] + sum[i-1];

		int total = 0;
		for(int s : sum) total += s;
		
		System.out.println(total);
	}
}
