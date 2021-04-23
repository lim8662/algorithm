package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1194_fullmoon {
	static class Walker { 
		int x; int y; // 현재 위치
		int key; // 가진 키 상태
		int move; // 이동한 횟수
		
		Walker(int x, int y, int key, int move) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.move = move;
		}
	}
	static int R, C, start;
	static char[][] map;
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static boolean[][][] visited; // x,y,key 열쇠를 얻으면 다시 모든 곳 탐색 가능
	
	public static void main(String[] args) throws IOException {		
		input();	
		maze();
	}

	private static void maze() { // 미로 탐색
		Queue<Walker> q = new ArrayDeque<>();
		int sx = start / 100;
		int sy = start % 100;
		q.add(new Walker(sx, sy, 0, 0));
		visited[sx][sy][0] = true;
		
		while(!q.isEmpty()) {
			Walker cur = q.poll();
			int r = cur.x;
			int c = cur.y;
			
			if(map[r][c] == '1') { // 출구면 탈출
				System.out.println(cur.move); return;
			}
			else if(map[r][c] - 'a' >= 0 && map[r][c] - 'a' <= 5) { // 열쇠가 있다면 줍는다.
				cur.key |= 1 << map[r][c] - 'a'; 
			}
			else if(map[r][c] - 'A' >= 0 && map[r][c] - 'A' <= 5) { // 문이 있다면
				if((cur.key & 1 << map[r][c] - 'A') == 0) { // 열쇠가 없으면 이동 x
					continue;
				}
			}
			for (int d = 0; d < 4; d++) { // 4방 이동
				int nr = r + dx[d];
				int nc = c + dy[d];
				if(nr < 0 || nc < 0 || nr >= R || nc >= C 
						|| map[nr][nc] == '#'|| visited[nr][nc][cur.key]) continue;
				
				q.add(new Walker(nr, nc, cur.key, cur.move + 1));
				visited[nr][nc][cur.key] = true;	
			}
		}
		System.out.println(-1); // 탈출 실패
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C][64]; // 6가지 키의 subset 개수
		
		for (int r = 0; r < R; r++) {
			char[] arr = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				char cur = arr[c];
				if(cur == '0') start = r * 100 + c; // 시작 위치 저장
				map[r][c] = cur;
			}
		}
	}
}
