package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1197_MST {
	static int N, E, total;
	static int[] parents;
	
	static class Edge {
		int v1, v2, w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
	static Edge[] edges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 수
		E = Integer.parseInt(st.nextToken()); // 간선 수
		parents = new int[N + 1]; // 
		edges = new Edge[E];
		
		for (int n = 1; n <= N; n++) { // 단위 집합 생성
			parents[n] = n * -1;
		}
		
		int v1 = 0, v2 = 0, w = 0;
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			edges[e] = new Edge(v1, v2, w);
		}
		
		Arrays.sort(edges, (a,b) -> a.w - b.w);
		
//		for (int i = 0; i < E; i++) {
//			System.out.printf("%d %d %d\n", edges[i].v1, edges[i].v2, edges[i].w);
//		}
		//System.out.println(Arrays.toString(parents));
		
		int cnt = 0; // 선택된 간선 수
		
		for (int e = 0; e < E; e++) {		
			if(union(edges[e].v1, edges[e].v2)) {
				total += edges[e].w;
				
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(total);
	}
	
	private static boolean union(int a, int b) { // a집합에게 b집합을 결합
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot; 
		return true;
	}
	
	private static int findSet(int s) { //대표를 찾음
		if(parents[s] < 0) return s;
		return parents[s] = findSet(parents[s]); // 경로 압축
		
	}
}
