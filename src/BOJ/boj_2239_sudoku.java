package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj_2239_sudoku {

	static List<Integer> blank = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[9][9];
		int num = 0;

		for (int i = 0; i < 9; i++) { // 문제 입력
			String in = br.readLine();
			for (int j = 0; j < 9; j++) {
				num = Integer.parseInt(in.substring(j, j + 1));
				if (num == 0) { // 빈칸이면 위치 저장
					blank.add(i * 100 + j);
				}
				map[i][j] = num;
			}
		}
		sudoku(map, 0, 0);
	}

	private static boolean sudoku(int[][] map, int b, int cnt) {
		if (cnt == blank.size()) { // 스도쿠 완성
			for (int i = 0; i < 9; i++) { // 출력
				for (int j = 0; j < 9; j++) {
					System.out.printf("%d", map[i][j]);
				}
				System.out.println();
			}
			return true;
		}

		int cur = blank.get(b);
		int x = cur / 100;
		int y = cur % 100;

		for (int n = 1; n <= 9; n++) { // 현재 빈칸에 1~9를 대입해보기
			if (ispossible(map, x, y, n)) { // 가능하면
				map[x][y] = n;
				if (sudoku(map, b + 1, cnt + 1))
					return true; // 다음 빈칸 채우기
				map[x][y] = 0;
			}
		}
		return false;
	}

	// xy 위치의 수평, 수직, 사각형에서 n이 중복되는지 체크
	private static boolean ispossible(int[][] map, int x, int y, int n) {
		int[] numCnt = new int[10];

		for (int i = 0; i < 9; i++) { // 수평 , 수직 체크
			numCnt[map[x][i]]++;
			numCnt[map[i][y]]++;
		}
		if (numCnt[n] > 0)
			return false;

		numCnt = new int[10];
		int r = x / 3, c = y / 3; // 3*3 사각형 체크
		for (int i = r * 3; i < r * 3 + 3; i++)
			for (int j = c * 3; j < c * 3 + 3; j++) {
				numCnt[map[i][j]]++;
			}
		if (numCnt[n] > 0)
			return false;

		return true;
	}
}
