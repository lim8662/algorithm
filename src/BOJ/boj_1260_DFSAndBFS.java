package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1260_DFSAndBFS {
	
	static List<Integer>[] edge; 
	static boolean[] visited;
	static StringBuilder out = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점 수
		int M = Integer.parseInt(st.nextToken()); // 간선 수
		int S = Integer.parseInt(st.nextToken()); // 시작 점
		edge = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) 
			edge[i] = new ArrayList<>();
		
		int v1 = 0, v2 = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			edge[v1].add(v2);
			edge[v2].add(v1);
		}
		
		for (int i = 1; i <= N; i++) { 
			if(edge[i].size() > 1)
				Collections.sort(edge[i]);
		}
		
		dfs(S);
		out.append("\n");
        Arrays.fill(visited, false);
		bfs(S);
		System.out.println(out.toString());
	}

	private static void dfs(int s) {
		visited[s] = true;
		out.append(s + " ");
		
		for(int next : edge[s]) {
			if(!visited[next]) dfs(next);
		}
		
	}
	
	private static void bfs(int s) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(s);
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			out.append(cur + " ");
			
			for(int next : edge[cur]) {
				if(!visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
	}	
	
}
