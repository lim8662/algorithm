package PRG;

import java.util.Arrays;

public class pg_21_DevMat_BE_2 {

	public static void main(String[] args) {
		int[][] q = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		System.out.println(Arrays.toString(new pg_21_DevMat_BE_2().solution(6, 6, q)));
	}

	public int[] solution(int r, int c, int[][] q) {
		int[] answer = new int[q.length];
		int[][] map = new int[r][c];
		int[][] tmap = null;
		int num = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = ++num;
			}
		}

		for (int i = 0; i < q.length; i++) {
			int min = Integer.MAX_VALUE;
			tmap = new int[r][c];
			for (int d = 0; d < r; d++) {
				for (int j = 0; j < c; j++) {
					tmap[d][j] = map[d][j];
				}
			}
			int sr = q[i][0] - 1, sc = q[i][1] - 1;
			int er = q[i][2] - 1, ec = q[i][3] - 1;
			// 현 위치
			int row = sr;
			int col = sc;
			
			while(col < ec) {
				min = Math.min(min, map[row][col]);
				tmap[row][col+1] = map[row][col];
				col++;
			}
			
			while(row < er) {
				min = Math.min(min, map[row][col]);
				tmap[row+1][col] = map[row][col];
				row++;
			}
			
			while(col > sc) {
				min = Math.min(min, map[row][col]);
				tmap[row][col-1] = map[row][col];
				col--;
			}
			
			while(row > sr) {
				min = Math.min(min, map[row][col]);
				tmap[row-1][col] = map[row][col];
				row--;
			}		
			answer[i] = min;
			map = tmap;
		}
		return answer;
	}
}
