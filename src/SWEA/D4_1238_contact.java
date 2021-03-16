package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class D4_1238_contact {
	static int ans, N, S, from, to;
	static List<Integer>[] edge; // 간선 저장
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 수
			S = Integer.parseInt(st.nextToken()); // 시작점
			edge = new ArrayList[N+1];
			
			for (int i = 0; i <= N; i++) 
				edge[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int dataCnt = st.countTokens();
			for (int i = 0; i < dataCnt; i+=2) { // 연락망 입력
				from =  Integer.parseInt(st.nextToken());  
				to =  Integer.parseInt(st.nextToken()); 
				
				if(!edge[from].contains(to))
					edge[from].add(to);
			}
		
			boolean[] visited = new boolean[N+1]; // 정점 방문 여부
			Queue<Integer> q = new ArrayDeque<>(); // 방문할 정점 저장
			q.add(S);
			visited[S] = true; // 큐에 들어갈 때 방문 체크를 해서 다음 탐색할 정점의 중복 방지
			
			int step = 0;
			while(!q.isEmpty()) {
				int max = 0; // 현 단계 최대 번호
				int nextCnt = 0; // 다음 단계에 방문할 정점의 수
				
				int size = q.size();
				for (int i = 0; i < size; i++) { // 현 단계 모든 정점 탐색
					
					int cur = q.poll();
					max = (cur > max) ? cur : max;
					
					for(int to : edge[cur]) {
						if(!visited[to])  {
							q.add(to);
							visited[to] = true;
							nextCnt++;
						}
					}
				}
				
				if(nextCnt == 0) { // 마지막 연락 단계면
					ans = max;
				}
				step++;
			}	
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
}