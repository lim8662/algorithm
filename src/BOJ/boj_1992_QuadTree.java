package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1992_QuadTree {
	static int N;
	static char[][] map;
	static StringBuilder out = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		compression(N, 0, 0);
		System.out.println(out.toString());
	}
	// 압축 가능 여부 검사 및 압축
	public static boolean isSame(int n, int x, int y) {
		char data = map[x][y];
		for (int i = x; i < x + n; i++) // 검사
		for (int j = y; j < y + n; j++) { // for문 조건문 x ,y 체크..
			if (data != map[i][j]) return false;	
		}
		out.append(data); // 압축
		return true;
	}
	
	// 분할 정복
	public static void compression(int n, int x, int y) { // n은 현 영역 크기 / x,y는 영역 좌상단 꼭짓점
		if(!isSame(n, x, y)) {// 현 영역 데이터가 모두 같다면 압축	
			// 다르다면 4분할
			out.append("(");
			int M = n / 2; // 영역 구분선
			for (int i = 0; i <= 1; i++)  // 검사
			for (int j = 0; j <= 1; j++) {
				compression(M, x+M*i, y+M*j);
			}
			out.append(")");
		}
	}
}
	