package SWEA;

import java.util.*;
import java.io.*;

public class swea_5656_brickBreaker {
	
	static int[][] map, tmap; 
	static boolean[][] visited;
	static int N, R, C, ans;
	static int[] picked;
	static final int[] dx = { 0, 0, 1, -1 };
	static final int[] dy = { 1, -1, 0, 0 };
	
    public static void main(String args[]) throws Exception {	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer dx;
 	
    	int T = Integer.parseInt(br.readLine());    
    	for(int tc=1; tc<=T; tc++) {
    		
    		dx = new StringTokenizer(br.readLine()); // 입력
			N = Integer.parseInt(dx.nextToken());
			C = Integer.parseInt(dx.nextToken());
			R = Integer.parseInt(dx.nextToken());
            picked = new int[N];
            map = new int[R][C];
            ans = Integer.MAX_VALUE;
            
            for (int i = 0; i < R; i++) { // 맵 입력
            	dx = new StringTokenizer(br.readLine()); 
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(dx.nextToken());
				}
			}
			selectLine(0);
            System.out.println("#" + tc + " " + ans);
         }
    }

	private static void selectLine(int cnt) { // 구슬을 쏠 라인 선택
		
		if(cnt == N) {
			shooting(picked);
			return;
		}
		
		for (int i = 0; i < C; i++) {
			picked[cnt] = i;
			selectLine(cnt+1);
		}
	}

	private static void shooting(int[] line) { // 선택된 라인에 구슬들 쏘기		
		tmap = new int[R][C]; // 시뮬레이션 할 맵
		for (int i = 0; i < R; i++)  
		for (int j = 0; j < C; j++) {
			tmap[i][j] = map[i][j];
		}

		for (int c = 0; c < N; c++) { // 뽑은 순서대로 구슬 쏘기
			int r = 0;
			while(r < R && tmap[r][line[c]] == 0 ) ++r;
			if(r == R) continue; // 빈라인이면 쏘지 않음
			
			pang(line[c]); // 벽돌 깨기
			
			down(); // 벽돌 정렬
		}

		// 남은 벽돌 수 확인
		int cnt = 0;
		for (int i = 0; i < R; i++)  
		for (int j = 0; j < C; j++) {
			if(tmap[i][j] > 0) cnt++;
		}
		ans = Math.min(ans, cnt);
	}
	
	private static void pang(int c) { // 벽돌 터뜨리기
		Queue<Integer> q = new ArrayDeque<Integer>(); // 폭발 벽돌의 위치
		
		for (int r = 0; r < R; r++) { // 벽돌 찾기
			if(tmap[r][c] > 0) {
				if( tmap[r][c] > 1) q.add(r*100 + c); // 폭발 벽돌이면 큐에 저장
				else tmap[r][c] = -1; // 일반 벽돌이면 터뜨림
				break;
			}	
		}
		// 연쇄 터뜨리기
		int cur = 0, cx = 0, cy = 0, power = 0, nx = 0, ny = 0; 
		while(!q.isEmpty()) { 
			cur = q.poll();
			cx = cur / 100; cy = cur % 100;
			power = tmap[cx][cy] - 1; // 폭발력
			tmap[cx][cy] = -1; 
			
			for (int d = 0; d < 4; d++) { // 4방으로
		
				for (int p = 1; p <= power; p++) { // 폭발 범위 탐색
					nx = cx + dx[d] * p;
					ny = cy + dy[d] * p;
					
					if(nx >= R || nx < 0 || ny >= C || ny < 0 // 경계를 넘거나 
							|| tmap[nx][ny] < 1) continue; // 빈칸이면 넘김
					
					if(tmap[nx][ny] > 1) q.add(nx * 100 + ny); // 폭발 벽돌이면 큐에 저장
					else tmap[nx][ny] = -1; // 일반 벽돌이면 터뜨림
					
				}
			}
		}		
	}
	
	private static void down() { // 벽돌 정렬

		int[][] smap = new int[R][C]; // 정렬된 맵
		for(int[] init : smap) { // 초기화
			Arrays.fill(init, 0);
		}
		for (int col = 0; col < C; col++) {
			int nr = R-1; // 벽돌을 놓을 포인터
			for (int row = R-1; row >= 0; row--) {
				if(tmap[row][col] == 0) break;
				if(tmap[row][col] == -1) continue;
				smap[nr--][col] = tmap[row][col]; // 정렬해서 배치하고 포인터 이동
			}
		}
		tmap = smap;	
	}
}
