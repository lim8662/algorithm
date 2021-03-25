package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2565_LIS {
	static int N, E, total;
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int v1, v2;

		public Edge(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
		}
		
		public int compareTo(Edge o) {
			return this.v1 < o.v1 ? -1 : 1;
		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine()); // 전선 수
		StringTokenizer st;
		Edge[] edges = new Edge[N];
		
		int v1 = 0, v2 = 0;
		for (int e = 0; e < N; e++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());

			edges[e] = new Edge(v1, v2);
		}
		
		Arrays.sort(edges);
		
		int[] LIS = new int[N];
		int max = 0;
		for (int i = 0; i < N ; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if(edges[j].v2 < edges[i].v2 && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			if(max < LIS[i]) max = LIS[i];
		}
		System.out.println(N - max);
	}
	
}
