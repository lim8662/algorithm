package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17144_fineDust {
	
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
		
		while(T > 0) { // T초만큼 시뮬레이션	
			spread(); // 미세먼지 확산					
			airCleaner(); // 공기 청정기 작동
			T--;
		}
		
		
		int total = 0; // 남은 미세먼지 총량
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if( map[i][j] > 0 ) total += map[i][j];
			}
		}
		System.out.println(total);
	}

	private static void spread() {
		tmap = new int[R][C];
		tmap[pos][0] = -1;
		tmap[pos-1][0] = -1;
		
		int cur = 0, part = 0, nx = 0, ny = 0;
		for (int x = 0; x < R; x++) 
		for (int y = 0; y < C; y++) {
			cur = map[x][y]; // 현위치의 미세먼지 양
			if(cur <= 0) continue;
			part = cur / 5; // 확산되는 양
			
			for (int d = 0; d < 4; d++) { // 4방 확산
				nx = x + dx[d];
				ny = y + dy[d];
				if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] < 0) continue;
				
				tmap[nx][ny] += part; // 확산
				cur -= part;
			}
			tmap[x][y] += cur; // 확산되고 남은양 

		}
		map = tmap;
	}
	
	private static void airCleaner() {
		
		System.out.println();
		for (int i = 0; i < R; i++) { // 맵 입력
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		tmap = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmap[i][j] = map[i][j];
			}
		}
		int row = pos;
		int col = 1;
		tmap[row][1] = 0; // 아래쪽 시계방향 순환
		while(col < C - 1) {
			tmap[row][col+1] = map[row][col];
			col++;
		}
		
		while(row < R - 1) {
			tmap[row+1][col] = map[row][col];
			row++;
		}
		
		while(col > 0) {
			tmap[row][col-1] = map[row][col];
			col--;
		}
		
		while(row > pos + 1) {
			tmap[row-1][col] = map[row][col];
			row--;
		}
		
		row = pos-1;
		col = 1;
		tmap[row][1] = 0; // 위쪽 반시계방향 순환
		while(col < C - 1) {
			tmap[row][col+1] = map[row][col];
			col++;
		}
		
		while(row > 0) {
			tmap[row-1][col] = map[row][col];
			row--;
		}
		
		while(col > 0) {
			tmap[row][col-1] = map[row][col];
			col--;
		}

		while(row < pos - 2) {
			tmap[row+1][col] = map[row][col];
			row++;
		}		
		map = tmap;
		

	}
}
