package SWEA;

import java.util.*;
import java.io.*;

public class swea_1868_findMine {
	
	static int[][] map, tmap; 
	static boolean[][] open;
	static int N, clk;
	static final int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1}; // 상 하 좌 우, 좌상, 좌하, 우상, 우하
	static final int[] dy = { 0, 0, -1, 1, -1, -1, 1, 1};
	
	public static void main(String args[]) throws Exception {	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int T = Integer.parseInt(br.readLine());    
    	for(int tc=1; tc<=T; tc++) {
    		
    		N = Integer.parseInt(br.readLine()); // 맵 크기
    		map = new int[N][N];
            clk = 0;
            
            int nx, ny;
            for (int i = 0; i < N; i++) { // 맵 입력
            	char[] str = br.readLine().toCharArray(); 
				for (int j = 0; j < N; j++) {
					char cur = str[j];
					
					if(cur == '*') { // 지뢰면 8방에 수 증가
						map[i][j] = -1;
						for (int d = 0; d < 8; d++) {
							nx = i + dx[d];
							ny = j + dy[d];
							if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] < 0) continue;
							map[nx][ny] += 1;
						}
					}
					
				}
			}

            open = new boolean[N][N]; 
            for (int i = 0; i < N; i++) // 모든 칸에 대해
			for (int j = 0; j < N; j++) {
				if(!open[i][j] && map[i][j] >= 0) { // 열리지 않은 빈공간이면 클릭 
					click(i,j); clk++;
				}
			}
            System.out.println("#" + tc + " " + clk);
         }
    }

	private static void click(int i, int j) { // bfs로 인접칸 탐색
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		q.add(i*1000 + j);
		open[i][j] = true;
		
		int cur, x, y, nx, ny;
		while(!q.isEmpty()) {
			cur = q.poll();
			x = cur / 1000;
			y = cur % 1000;
			
			for (int d = 0; d < 8; d++) { // 8방에 방문하지 않은 0이 있다면 탐색 
				nx = x + dx[d];
				ny = y + dy[d];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N 
						|| open[nx][ny] || map[nx][ny] < 0) continue;
				if(map[x][y] == 0) open[nx][ny] = true; // 현 위치가 0이면 8방 오픈
				
				if(map[nx][ny] == 0 ) { // 인접한 0이 있다면 연쇄 오픈
					q.add(nx * 1000 + ny);
					open[nx][ny] = true;
				}
			}
		}
	}
}
