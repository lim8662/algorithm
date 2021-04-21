package SWEA;

import java.io.*;
import java.util.*;

public class swea_5643_HeightRank {
	
	static int N, M, ans;
	static ArrayList<ArrayList<Integer>> adjList;
	static boolean[] visited;
	static int[] s, l; // 각 학생보다 작거나 큰 학생 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adjList = new ArrayList<>();
			s = new int[N+1]; l = new int[N+1];
			
			for (int i = 0; i <= N; i++) { // 배열 크기를 N+1로 하면, index를 N까지 반복해야함
 				adjList.add(new ArrayList<>());
			}
			
			int a, b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				adjList.get(a).add(b);
			}
			
			
			for (int i = 1; i <= N; i++) { // 각 학생에 대해 작은 학생 수 와 큰 학생 수를 구함
				visited = new boolean[N+1];
				dfs(i, i);
			}
			
			for (int n = 1; n <= N; n++) {
				if(s[n] + l[n] == N - 1) { // 모든 다른학생과 비교가 가능하면
					ans++;
				}
			}		
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	private static void dfs(int target, int i) { // 대상 학생보다 큰 학생을 방문
		visited[i] = true;

		for(int next : adjList.get(i)) {
			if(!visited[next]) {
				l[target]++; // 대상 학생보다 큰 학생의 수 증가
				s[next]++; // 큰 학생의 작은 학생 수 증가
				dfs(target, next);
			}
		}	
	}
}
