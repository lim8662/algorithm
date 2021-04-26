package BOJ;

import java.io.*;
import java.util.*;

public class boj_15900_EscapeTree {
	
	static int N, cnt;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		StringTokenizer st; 
		int v1, v2;
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}
		
		dfs(0, 1, 0);
		
		if(cnt % 2 == 0) System.out.println("No"); // 1칸씩 이동하여 이동 횟수가 짝수면 짐
		else System.out.println("Yes");
	}

	private static void dfs(int parent, int cur, int move) { // 모든 말의 이동 횟수의 합 구하기
		
		if(adjList[cur].size() == 1) cnt += move;
		
		for(int child : adjList[cur]) {
			if(child == parent) continue;
			dfs(cur, child, move+1);
		}
		
	}
}
