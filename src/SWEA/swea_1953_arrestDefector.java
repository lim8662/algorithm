package SWEA;

import java.util.*;
import java.io.*;

public class swea_1953_arrestDefector {
	
	static int[][] map, tmap; 
	static boolean[][] visited;
	static int H, W, R, C, L, ans;
	static final int[] dx = { -1, 1, 0, 0}; // 상 하 좌 우
	static final int[] dy = { 0, 0, -1, 1};
	static final int[][] tunnel = { {0,1,2,3}, {0,1}, {2,3}, {0,3}, {1,3}, {1,2}, {0,2} };
    static final int[][] connect = { {1,2,5,6}, {1,2,4,7}, {1,3,4,5}, {1,3,6,7} };
    
	public static void main(String args[]) throws Exception {	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer dx;
 	
    	int T = Integer.parseInt(br.readLine());    
    	for(int tc=1; tc<=T; tc++) {
    		
    		dx = new StringTokenizer(br.readLine()); // 입력
			H = Integer.parseInt(dx.nextToken()); // 맵 크기
			W = Integer.parseInt(dx.nextToken());
			R = Integer.parseInt(dx.nextToken()); // 탈주범 위치
			C = Integer.parseInt(dx.nextToken()); 
			L = Integer.parseInt(dx.nextToken()); // 지연 시간
            map = new int[H][W];
            visited = new boolean[H][W];
            ans = 1;
            
            for (int i = 0; i < H; i++) { // 맵 입력
            	dx = new StringTokenizer(br.readLine()); 
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(dx.nextToken());
				}
			}
			arrest();
            System.out.println("#" + tc + " " + ans);
         }
    }

	private static void arrest() { // 예상 위치의 수 찾기
		if(L == 1) return; // 1시간이면 바로 검거
		
		int time = 1;
		Queue<Integer> q = new ArrayDeque<Integer>(); // 미 방문 터널의 위치
		q.add(R*100 + C);
		visited[R][C] = true;
		
		int cur, x, y, type, d, nx, ny, nt;
		while(!q.isEmpty()) {
			if(time == L) return; // 총 소요시간 까지 찾아봤으면 종료
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				cur = q.poll();
				x = cur / 100; y = cur % 100; 
				type = map[x][y] - 1; // 터널 타입
				
				for (int dir = 0; dir < tunnel[type].length; dir++) {
					
					d = tunnel[type][dir]; // 이동할 방향
					nx = x + dx[d];
					ny = y + dy[d];
					if(nx >= H || nx < 0 || ny >= W || ny < 0 
							|| visited[nx][ny] || map[nx][ny] == 0) continue;
					
					// 이동이 가능한 터널인지 확인
					nt = map[nx][ny];
					for (int t = 0; t < connect[d].length; t++) {
						if(connect[d][t] == nt) {
							visited[nx][ny] = true;
							q.add(nx * 100 + ny);
							ans++; break;
						}
					}
					
				}		
			}
			time++;
		}	
	}
}
