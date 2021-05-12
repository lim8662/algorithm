package BOJ;

import java.io.*;
import java.util.*;

public class boj_21611_blizzard {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, m;
	static int[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int answer;
	
	public static void main(String args[]) throws IOException {
		String[] info = br.readLine().split(" ");
		n = st(info[0]); m = st(info[1]);
		
		board = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				board[i][j] = st(line[j]);
			}
		}
		
		for(int i = 0; i < m; i++) {
			String[] line = br.readLine().split(" ");
			int dir = st(line[0]); int dist = st(line[1]);
			if(dir == 1) dir = 3;
			else if(dir == 2) dir = 1;
			else if(dir == 3) dir = 2;
			else dir = 0;
			
			//공격
			attack(dir, dist);
			//다시 놓기, 쌓인거 터트리기, 군집화
			fill();
			
		}
		System.out.println(answer);
	}
	
	public static void fill() {
		int x = n/2, y = n/2-1, dir = 2; 
		ArrayList<Integer> arr = new ArrayList<>();
		while(true) {
			if(board[x][y] != 0) {
				arr.add(board[x][y]);
				board[x][y] = 0;
			}
			
			if((x+y == n-1) || (x-y == 1 && y < n/2) || (x == y && y > n/2)) {
				dir = (dir+4-1)%4;
			}
			x += dx[dir];
			y += dy[dir];			
			
			if(x == 0 && y == -1) break;
		}
		
		arr = bomb(arr);
		
		arr = transform(arr);
		
		
		//다시 넣기
		x = n/2; y = n/2-1; dir = 2;
		while(!arr.isEmpty()) {
			board[x][y] = arr.remove(0);
			
			if((x+y == n-1) || (x-y == 1 && y < n/2) || (x == y && y > n/2)) {
				dir = (dir+4-1)%4;
			}
			x += dx[dir];
			y += dy[dir];			
			
			if(x == 0 && y == -1) break;
		}
	}
	
	public static ArrayList<Integer> transform(ArrayList<Integer> arr) {
		if(arr.isEmpty()) return arr;
		
		ArrayList<Integer> save = new ArrayList<>();
		
		int count = 0;
		int r_num = arr.get(0);
		
		int idx = 0;
		while(idx < arr.size()) {
			if(arr.get(idx) == r_num) {
				count++;
				idx++;
			}
			else {//다르다면
				save.add(count); save.add(r_num);
				count = 1;
				r_num = arr.get(idx);
				idx++;
			}
			
			if(idx == arr.size()) {
				save.add(count); save.add(r_num);
			}
		}
		
		return save;
	}
	
	
	public static ArrayList<Integer> bomb(ArrayList<Integer> arr) {
		if(arr.isEmpty()) return arr;
		
		boolean popped = false;
		int start = 0;
		int count = 0;
		int r_num = arr.get(start);
		
		int idx = 0;
		while(idx < arr.size()) {
			if(arr.get(idx) == r_num) {
				count++;
				idx++;
			}
			else {//다르다면
				if(count >= 4) {
					for(int i = 0; i < count; i++) {
						answer += arr.remove(start);
					}
					idx = start;
					count = 1;
					r_num = arr.get(start);
					popped = true;
					idx++;
				}
				else {
					start = idx;
					count = 1;
					r_num = arr.get(start);
					idx++;
				}
			}
			
			if(idx == arr.size() && count >= 4) {
				popped = true;
				for(int i = 0; i < count; i++) {
					answer += arr.remove(start);
				}
			}
		}
		
		if(popped) return bomb(arr);
		else return arr;
	}
	
	
	public static void attack(int dir, int dist) {
		int x = n/2; int y = n/2;
		for(int i = 1; i <= dist; i++) {
			int nX = x + i*dx[dir];
			int nY = y + i*dy[dir];
			if(valid(nX, nY)) {
				board[nX][nY] = 0;
			}
		}
	}
	
	public static void print() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(board[i][j]  + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int st(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean valid(int x, int y) {
		if(x < 0 || x >=n || y < 0 || y >= n) return false;
		else return true;
	}
}
