package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_15683_monitoring {
	
	static class CCTV {
		int type;
		int x;
		int y;
		
		public CCTV(int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
		}	
	}
	
	static int R, C, size, min = Integer.MAX_VALUE;
	static int[][] map, tmap;
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static ArrayList<CCTV> cctv = new ArrayList<>();
	static final int[][] type2 = { {0, 1}, {2, 3}};
	static final int[][] type3 = { {0, 3}, {0, 2}, {1, 2}, {1, 3}};
	static final int[][] type4 = { {0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		int cur = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				cur = Integer.parseInt(st.nextToken());
				if(cur >= 1 && cur <= 5) {
					cctv.add(new CCTV(cur, i, j));
				}
				map[i][j] = cur;
			}
		}
		size = cctv.size();
		copymap();
		monitor(0, 0);
		System.out.println(min);
	}
	
	private static boolean monitor(int cnt, int idx) {
		 
		if(cnt == size) { // 모든 cctv를 보았다면
			int blind = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(tmap[i][j] == 0) blind++; // 사각지대 크기 구함
				}
			}
			min = (min > blind) ? blind : min; // 시각지대 최솟값 갱신
			return true;
		}
		
		CCTV ctv = cctv.get(idx);
		switch (ctv.type) {
		case 1: 
			for (int d = 0; d < 4; d++) { // 모든 방향에 대해 감시
				checkSite(cctv.get(idx), d);		
				monitor(cnt+1, idx+1); // 다음 cctv 보기
				backTracking(cctv.get(idx), d); // 백트래킹
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				checkSite(cctv.get(idx), type2[i][0]);
				checkSite(cctv.get(idx), type2[i][1]);
				monitor(cnt+1, idx+1);
				backTracking(cctv.get(idx), type2[i][0]); // 백트래킹
				backTracking(cctv.get(idx), type2[i][1]); // 백트래킹
			}		
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				checkSite(cctv.get(idx), type3[i][0]);
				checkSite(cctv.get(idx), type3[i][1]);
				monitor(cnt+1, idx+1);
				backTracking(cctv.get(idx), type3[i][0]); // 백트래킹
				backTracking(cctv.get(idx), type3[i][1]); // 백트래킹
			}	
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				checkSite(cctv.get(idx), type4[i][0]);
				checkSite(cctv.get(idx), type4[i][1]);
				checkSite(cctv.get(idx), type4[i][2]);
				monitor(cnt+1, idx+1);
				backTracking(cctv.get(idx), type4[i][0]); // 백트래킹
				backTracking(cctv.get(idx), type4[i][1]); // 백트래킹
				backTracking(cctv.get(idx), type4[i][2]); // 백트래킹
			}	
			break;
		case 5:
			for (int d = 0; d < 4; d++) checkSite(cctv.get(idx), d);	
			monitor(cnt+1, idx+1);
			for (int d = 0; d < 4; d++) backTracking(cctv.get(idx), d); // 백트래킹
			break;
		}
		
		return false;
	}

	private static void backTracking(CCTV ctv, int d) {
		int nx = ctv.x;
		int ny = ctv.y;
		
		while(true) {
			nx += dx[d];
			ny += dy[d];
			if(nx < 0 || nx >= R || ny < 0 || ny >= C || tmap[nx][ny] == 6) break;
			if(tmap[nx][ny] >= 7) tmap[nx][ny] -= 7;
		}
		
	}

	private static void checkSite(CCTV ctv, int d) { // 시야 범위를 7로 채운다
		int nx = ctv.x;
		int ny = ctv.y;
		
		while(true) {
			nx += dx[d];
			ny += dy[d];		
			if(nx < 0 || nx >= R || ny < 0 || ny >= C || tmap[nx][ny] == 6) break;
			if(tmap[nx][ny] >= 1 && tmap[nx][ny] <= 5) continue;
			tmap[nx][ny] += 7; // 0 or 7은 7증가
		}	
	}

	private static int[][] copymap() {
		tmap = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmap[i][j] = map[i][j];
			}
		}
		return null;
	}
	
		
}
