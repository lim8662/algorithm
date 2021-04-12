package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17070_pipeMove1 {
	
	static class Pipe {
		int x; 
		int y;
		char type; // 가로 h, 세로 v, 대각선 d 
		
		public Pipe(int x, int y, char type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}	
	}
	
	static int N, cnt, map[][], dp[][][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 		
		N = Integer.parseInt(br.readLine()); 
		map = new int[N][N];
		dp = new int[N][N][3];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//bfs();
		dp();
		System.out.println(cnt);
	}

	private static void dp() {
		
		
	}

	private static void bfs() {
		Queue<Pipe> q = new ArrayDeque<>();
		q.add(new Pipe(0, 1, 'h'));
		
		Pipe cur = null;
		int cx = 0, cy = 0, nx = 0, ny = 0;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			if(cur.x == N-1 && cur.y == N-1) { // 도작점이면 경우의수 증가
				cnt++; continue;
			}
			
			cx = cur.x;
			cy = cur.y;
			
			switch (cur.type) {
			case 'h': // 가로 
				if(cy + 1 < N && map[cx][cy+1] == 0)  // 가로 이동
					q.add(new Pipe(cx, cy + 1, 'h'));
				
				if(cx + 1 < N && cy + 1 < N && map[cx+1][cy+1] == 0 && map[cx+1][cy] == 0 && map[cx][cy+1] == 0)
					q.add(new Pipe(cx+1, cy+1, 'd')); // 대각선 이동
				break;
			case 'v': // 세로
				if(cx + 1 < N && map[cx+1][cy] == 0)  // 세로 이동
					q.add(new Pipe(cx+1, cy, 'v'));
				
				if(cx + 1 < N && cy + 1 < N && map[cx+1][cy+1] == 0 && map[cx+1][cy] == 0 && map[cx][cy+1] == 0)
					q.add(new Pipe(cx+1, cy+1, 'd')); // 대각선 이동
				break;
			case 'd': // 대각선
				
				if(cy + 1 < N && map[cx][cy+1] == 0)  // 가로 이동
					q.add(new Pipe(cx, cy + 1, 'h'));
				
				if(cx + 1 < N && map[cx+1][cy] == 0)  // 세로 이동
					q.add(new Pipe(cx+1, cy, 'v'));
				if(cx + 1 < N && cy + 1 < N && map[cx+1][cy+1] == 0 && map[cx+1][cy] == 0 && map[cx][cy+1] == 0)
					q.add(new Pipe(cx+1, cy+1, 'd')); // 대각선 이동	
				break;
			}		
		}
	}
}
