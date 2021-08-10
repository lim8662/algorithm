package BOJ;

import java.io.*;
import java.util.*;

public class boj_2606_virus {
	// 서로 연결된 컴퓨터 정보
	// 1번을 통해 바이러스에 걸리게 되는 컴퓨터의 수 출력
	static boolean[] visited;
	static boolean[][] edge;
	static int N,cnt = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 컴퓨터 수(100이하)
		int P = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수
		
		visited = new boolean[N+1]; // 컴퓨터 감염 여부
		edge = new boolean[N+1][N+1];
		
		StringTokenizer st = null; 
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			edge[v1][v2] = edge[v2][v1] = true;	
		}
		
		visited[1] = true;
		infect(1);
		
		System.out.println(cnt);
	}
	
	public static void infect(int n) {
		
		for (int i = 1; i <= N; i++) {
			if(edge[n][i] && !visited[i]) {
				visited[i] = true;
				cnt++;
				infect(i);
			}
		}
	}
	
}
