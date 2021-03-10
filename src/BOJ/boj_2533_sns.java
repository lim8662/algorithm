package BOJ;

import java.io.*;
import java.util.*;

public class boj_2533_sns {
	public static int N, v1, v2;
	public static List<Integer>[] edge;
	public static int[][] dp;
	
	public static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		edge = new ArrayList[N+1];
		dp = new int[N+1][2];

		for (int i = 0; i <= N; i++) 
			edge[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) { // 간선 입력
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			edge[v1].add(v2);
			edge[v2].add(v1);
		}		

		dp(1, 0);

		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}

	public static void dp(int cur, int parent) {
		dp[cur][0] = 0; 
		dp[cur][1] = 1;
		
		for(int next : edge[cur]) {

			if(next != parent) { 
				dp(next, cur); 
				dp[cur][0] += dp[next][1];
				dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
			}
		}
	}
}
