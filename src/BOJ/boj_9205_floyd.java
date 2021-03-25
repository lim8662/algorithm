package BOJ;

import java.util.*;
import java.io.*;

public class boj_9205_floyd {

	static class Edge {
		int v1, v2;

		public Edge(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
		}
	}

	static boolean[][] edges; // 경로들을 저장
	static int[][] pos; // 건물의 좌표
	static int N;
	
	private static void makeEdge() {
	
		for (int s = 0; s < N + 2; s++) {
			for (int e = s + 1; e < N + 2; e++) {

				int w = Math.abs(pos[e][0] - pos[s][0]) // 거리 계산
						+ Math.abs(pos[e][1] - pos[s][1]);

				if (w <= 1000) // 이동 가능한 거리면
					edges[s][e] = edges[e][s] = true; // 경로 저장
			}
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer dx;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine()); // 편의점 수
			pos = new int[N + 2][2];
			edges = new boolean[N + 2][N + 2];
			
			for (int i = 0; i < N + 2; i++) { // 경유지의 좌표 입력
				dx = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(dx.nextToken());
				pos[i][1] = Integer.parseInt(dx.nextToken());
			}

			makeEdge();
			
			// 모든 좌표를 경유하여 경로 탐색
			for (int via = 0; via < N+2; via++) { // 경유위치
				for (int f = 0; f < N+2; f++) { // 시작위치
					for ( int t = f+1; t < N+2; t++) { // 도착위치
						if(edges[f][via] && edges[via][t]) 
							edges[f][t] = edges[t][f] = true;
					}
				}
			}			
			if(edges[0][N+1]) System.out.println("happy");
			else System.out.println("sad");
		}
	}
}
