package SWEA;

import java.util.*;
import java.io.*;

public class swea_1249_supplyRoute {

	static int[][] map; 
	static boolean[] visited;
	static int N, min, ans;
	static final int[] dx = {1, 0, -1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	int T = Integer.parseInt(br.readLine());
        
    	for(int tc=1; tc<=T; tc++) {
        	N = Integer.parseInt(br.readLine()); // 맵크기
            map = new int[N][N];
            visited = new boolean[N];
            ans = 0; min = 0;
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
            
      
            
            // 경로 탐색
            
            System.out.println("#" + tc + " " + ans);
        }
    }

}