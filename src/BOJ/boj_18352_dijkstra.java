package BOJ;

import java.io.*;
import java.util.*;
public class boj_18352_dijkstra {
	
	static class City implements Comparable<City> {
		int n;
		int dist;
		
		public City(int n, int dist) {
			super();
			this.n = n;
			this.dist = dist;
		}

		@Override
		public int compareTo(City o) {
			return this.dist - o.dist;
		}	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(in.nextToken()); // 도시 수
		int M = Integer.parseInt(in.nextToken()); // 도로 수
		int L = Integer.parseInt(in.nextToken()); // 목표 최단 거리
		int S = Integer.parseInt(in.nextToken()); // 시작 도시
		
		ArrayList<City>[] adjList = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			in = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(in.nextToken()); // 출발 도시
			int to = Integer.parseInt(in.nextToken()); // 도착 도시
			adjList[from].add(new City(to, 1));
		}
		
		int[] stDist = new int[N+1]; // 시작 도시에서 i도시 까지의 최단 거리
		Arrays.fill(stDist, Integer.MAX_VALUE);
		stDist[S] = 0;
		
		PriorityQueue<City> pq = new PriorityQueue<>(); 
		boolean[] visited = new boolean[N+1]; // 도시 방문 체크
		
		pq.add(new City(S, 0));
		
		City cur = null;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			
			if(stDist[cur.n] < cur.dist) continue;
			visited[cur.n] = true;
			
			for(City next : adjList[cur.n]) {
				if(!visited[next.n] && stDist[next.n] > stDist[cur.n] + 1) {
					stDist[next.n] = stDist[cur.n] + 1;
					pq.add(new City(next.n, stDist[next.n]));
				}
			}
		}
		
		StringBuilder out = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if(stDist[i] == L) out.append(i).append('\n'); 
		}
		if(out.length() == 0) System.out.println(-1);
		else System.out.println(out.toString());
	} 
}
