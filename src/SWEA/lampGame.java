package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lampGame {
	
	static char[][] map;
	static int[][] dp;
	static int sx, sy, ex, ey;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/SWEA/mario.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[4][5];
		dp = new int[4][5];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < 5; j++) {
				map[i][j] = row[j];
				if(map[i][j] == 'A') {
					sx = i; sy = j;
				}
				else if(map[i][j] == 'B') {
					ex = i; ey = j;
				}
			}
		}
		
		int min = getDist(ex, ey);
		System.out.println(min);
	}

	private static int getDist(int x, int y) {
		// if(n == 0) return 0;
		// if(n < 0) return -100000;
		// if(dp[n] != 0) return dp[n];
		
		// 맵을 벗어나거나 벽이면 경로 x
		if(x < 0 || x >= 4 || y < 0 || y >= 5 || map[x][y] == '#') return 100; 
		if(x == sx && y == sy) return 1; // 시작점이면 
		
		int east = getDist(x, y+1);
		int west = getDist(x, y-1);
		int south = getDist(x+1, y);
		int north = getDist(x-1, y);
		
		int temp = Math.min(east, west);
		int temp2 = Math.min(south, north);
		int ret = Math.min(temp, temp2);
		
		dp[x][y] = ret;
		return ret;
	}
	
	
	

	
}
