package SWEA;

import java.util.*;
import java.io.*;

public class D4_1251_hanaro {
	
	static class Edge implements Comparable<Edge>{
		int v1;
		double w;

		public Edge(int v1, double w) {
			this.v1 = v1;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			//return Double.compare(this.w, o.w);
			return this.w < o.w ? -1 : 1;
		}	
	}
	
	static PriorityQueue<Edge> edges = new PriorityQueue<>(); // 경로들을 저장
	static int[][] island; // 섬들의 좌표
	static boolean[] visited;
	static double ans, E;
	static int N;
	static int cnt;
	
	private static void bfs() {
		cnt = 0; // 선택된 경로 수
        
		edges.add(new Edge(0, 0)); // 0번 섬부터 bfs로 최단 경로 탐색
        
        while(!edges.isEmpty()) {
        	Edge cur = edges.poll();
        	
        	if(visited[cur.v1]) continue;
        	
        	visited[cur.v1] = true;
        	ans += cur.w;
        	cnt++;
        	
			if(cnt == N) break; // 모든 섬이 선택 되면 종료
			
        	for (int n = 1; n < N; n++) {
            	if(!visited[n]) {
            		edges.add(new Edge(n, E * (Math.pow((island[n][0] - island[cur.v1][0]), 2) 
							+ Math.pow((island[n][1] - island[cur.v1][1]), 2))));
            		
            	}	
    		}
        }
	}
	
    public static void main(String args[]) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer dx, dy;
    	
    	int T = Integer.parseInt(br.readLine());
        
    	for(int tc=1; tc<=T; tc++) {
    		// 입력
        	N = Integer.parseInt(br.readLine()); // 섬의 수
            island = new int[N][2]; 
            visited = new boolean[N];
            edges.clear();
            ans = 0;
            
            dx = new StringTokenizer(br.readLine());
            dy = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {  // 섬들의 좌표 입력
				island[i][0] = Integer.parseInt(dx.nextToken());
				island[i][1] = Integer.parseInt(dy.nextToken());
			}
     
            E = Double.parseDouble(br.readLine()); // 환경 부담 세율
            
            bfs();
            System.out.println("#" + tc + " " + Math.round(ans));
            //System.out.printf("#%d %d\n", tc, Math.round(ans)); 
        }
    	br.close();
    }

	
}