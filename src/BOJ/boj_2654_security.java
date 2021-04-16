package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2654_security {

	static int C, R, N, rd, sr, sc, sum;
	static int[][] map;
	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); // 가로 크기
		R = Integer.parseInt(st.nextToken()); // 세로 크기
		N = Integer.parseInt(br.readLine()); // 상점 수
		map = new int[R + 1][C + 1];

		for (int i = 1; i <= N + 1; i++) { // 상점 및 경비 위치 입력
			st = new StringTokenizer(br.readLine());
			int side = Integer.parseInt(st.nextToken()); // 상점 위치
			int dist = Integer.parseInt(st.nextToken()); // 거리
			int r = 0, c = 0;

			switch (side) {
			case 1: // 북
				r = 0; c = dist; rd = 0; // 우회전시 방향
				break;
			case 2: // 남
				r = R; c = dist; rd = 2;
				break;
			case 3: // 서
				r = dist; c = 0; rd = 3;
				break;
			case 4: // 동
				r = dist; c = C; rd = 1;
				break;
			}
			if(i == N+1) { // 경비면
				sr = r; sc = c; // 경비 위치 저장
			} else map[r][c] = i;
		}		
		// 각 상점에 대한 최단거리 구하기
		int len = 0, round = (R+C) * 2;
		for (int i = 1; i <= N; i++) {
			len = moveRight(i);
			sum += Math.min(len, round - len);	
		}
		System.out.println(sum);
	}

	private static int moveRight(int i) { // i번 상점을 우회전하며 탐색
		int dist = 0, r = sr, c = sc, d = rd;
		
		while(true) {
			// 이동
			r += dx[d]; c += dy[d]; dist++;
			
			// 도착시 종료
			if(map[r][c] == i) break;
						
			// 꼭짓점이면 방향변경
			if((d == 0 && c == C) || (d == 1 && r == R) || 
			   (d == 2 && c == 0) || (d == 3 && r == 0) )
				d = (d+1) % 4;
		}
		return dist;
	}
}
