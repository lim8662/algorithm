package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15681_treeAndQuery {

	static int N, R, Q, v1, v2;
	static ArrayList<Integer>[] edge; // 간선 저장
	static int dp[]; // 서브 트리 정점 수;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder out = new StringBuilder();
		N = Integer.parseInt(st.nextToken()); // 정점 수
		R = Integer.parseInt(st.nextToken()); // 루트 
		Q = Integer.parseInt(st.nextToken()); // 쿼리 수
		dp = new int[N+1]; 
		edge = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) 
			edge[i] = new ArrayList<>();
		
		for (int i = 0; i < N - 1; i++) { // 간선 입력
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			edge[v1].add(v2);
			edge[v2].add(v1);
		}		
		dp(R, 0);
		
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			out.append(dp[q]).append('\n');
		}		
		System.out.println(out.toString());
	}
	
	public static int dp(int r, int p) { // 서브 트리 정점 수 구하기
		int cnt = 1;
		
		for(int next : edge[r]) {
			if(next != p) 
				cnt += dp(next, r);
		}
		return dp[r] = cnt;
	}
}
