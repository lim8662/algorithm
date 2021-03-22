package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1753_shortestPath {
	static class Vertex implements Comparable<Vertex> {
		int no, totalDist;

		public Vertex(int no, int totalDist) {
			this.no = no;
			this.totalDist = totalDist;
		}
		
		public int compareTo(Vertex o) {
			return this.totalDist - o.totalDist;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점수
		int E = Integer.parseInt(st.nextToken()); // 간선수
		
		int start = Integer.parseInt(in.readLine()); // 출발점
		
		ArrayList<Vertex>[] adjList = new ArrayList[N+1]; // 간선이 적은경우 사용
		
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Vertex>();
		}
		
		int v1, v2, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adjList[v1].add(new Vertex(v2, w)); // totalDist를 가중치로 사용
		}
		
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new Vertex(start, 0)); 
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll(); // 큐안에 정점중 totalDist 비용이 최소인 정점이 선택
			
			if(visited[cur.no]) continue; // 이미 방문한 더 비용이 큰 정점 넘김 
			
			visited[cur.no] = true;
			
			// cur를 경유지로 하여 처리하지 않은 다른 정점으로의 최소비용 점검
			for (Vertex v : adjList[cur.no]) {
				if(!visited[v.no] && dist[v.no] > cur.totalDist + v.totalDist) {
					dist[v.no] =cur.totalDist + v.totalDist;
					pq.offer(new Vertex(v.no, dist[v.no]));
				}
			}
		}
		
		StringBuilder out = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if(dist[i] != Integer.MAX_VALUE)
				out.append(dist[i]).append("\n");
			else
				out.append("INF\n");
		}
		System.out.println(out.toString());
	}
}
