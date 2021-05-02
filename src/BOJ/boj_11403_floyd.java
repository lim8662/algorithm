package BOJ;

import java.io.*;
import java.util.*;

public class boj_11403_floyd {
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = null;
		int N = Integer.parseInt(br.readLine()); // 정점 수
		int[][] map = new int[N][N]; // 인접 행렬
		
		for (int i = 0; i < N; i++) { // 간선 정보 입력
			in = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int edge = Integer.parseInt(in.nextToken());
				if(edge == 0) map[i][j] = INF;
				else map[i][j] = edge;
			}	
		}
		
		for (int via = 0; via < N; via++) 
		for (int s = 0; s < N; s++) 
		for (int e = 0; e < N; e++) {
			if(map[s][via] != INF && map[via][e] != INF) {
				map[s][e] = Math.min(map[s][e], map[s][via] + map[via][e]);
			}
		}	

		StringBuilder out = new StringBuilder();
		for (int i = 0; i < N; i++) { // 경로 정보 출력
			for (int j = 0; j < N; j++) {
				if(map[i][j] == INF) out.append("0 ");
				else out.append("1 ");
			}
			out.append('\n');
		}
		System.out.println(out.toString());
	} 
}
