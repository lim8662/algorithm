package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17070_pipeMove1 {
	
	static int R, C, T, pos;
	static int[][] map, tmap;
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0 , 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken()); 
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) pos = i;
			}
		}

	}

	
}
