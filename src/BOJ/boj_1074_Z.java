package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1074_Z {
	static int N, r ,c;
	static final int[][] order = {{0,0}, {0,1}, {1,0}, {1,1}}; // Z 방문 순서
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N); // 배열 크기
		System.out.println(z(size, r, c));
	}
	
	public static int z(int n, int x, int y) { // n은 영역 크기 / x,y는 방문 좌표
		if(n == 2) { // 최소 z단위면 
			for(int i = 0; i < 4; i++) {
				if(x == order[i][0] && y == order[i][1]) return i; // 순서 반환
			} 
		}
		int std = n/2; // 영역 구분선
		int area = std * std; // 한 영역의 칸수
		if(x < std) { // 찾는 칸의 위치가 
			if(y < std) { // 좌상단이면
				return z(std,x,y);
			}
			else { // 우상단이면
				return z(std,x,y-std) + area;
			}
		}
		else { 
			if(y < std) { // 좌하단이면
				return z(std,x-std,y) + area*2;
			}
			else { // 우하단이면
				return z(std,x-std,y-std) + area*3;
			}
		}
	}
}
