package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11437_LCA {
	static List<Integer>[] edge;
	static int N, M;
	static int[] D, P; // 각 노드의 깊이
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuilder out = new StringBuilder();
		N = Integer.parseInt(br.readLine()); // 입력 개수
		edge = new ArrayList[N+1];
		D = new int[N+1];
		P = new int[N+1];
		
		for (int i = 0; i <= N; i++) 
			edge[i] = new ArrayList<>();
		
		StringTokenizer st;
		int  v1, v2;
		for (int i = 0; i < N - 1; i++) { // 간선 입력
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			edge[v1].add(v2);
			edge[v2].add(v1);
		}		
		//루트 1번부터 각 노드 깊이 구하기
		dfs(1, 0);
		
		M = Integer.parseInt(br.readLine()); // LCA 쌍 수
		for (int i = 0; i < M; i++) { 
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			while(D[v1] != D[v2]) // 두 노드의 깊이를 같게 만든다 
			{
				if(D[v1] > D[v2]) v1 = P[v1];
				else v2 = P[v2];
			}
			
			while(v1 != v2) { // 공통 조상까지 올라간다
				v1 = P[v1];
				v2 = P[v2];
			}
			out.append(v1).append('\n');
		}		
		System.out.println(out.toString());
	}
	
	private static void dfs(int n, int d) {
		D[n] = d; // 현재 노드 깊이 기록
		
		for(int child : edge[n]) { // 부모가 아닌 인접 노드를 방문
			if(child != P[n]) {
				P[child] = n; // 자손 노드에 부모 기록
				dfs(child, d+1);
			}
		}
	}
}
