package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class boj_8911_turtle {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder out = new StringBuilder();
	static int minX, maxX, minY, maxY, cx, cy, cd;
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			char[] cmd = br.readLine().toCharArray();
			solution(cmd);

		}
		System.out.println(out.toString());
	}

	public static void solution(char[] cmd) {
		cx = 0; cy = 0; cd = 0;
		minX = 0; maxX = 0; minY = 0; maxY = 0;
		for (int c = 0; c < cmd.length; c++) {

			switch (cmd[c]) {
			case 'F':
				cx += dx[cd]; cy += dy[cd];
				updateRange(); break;
			case 'B':
				int d = (cd + 2) % 4;
				cx += dx[d]; cy += dy[d];
				updateRange(); break;
			case 'R':
				cd = (cd + 1) % 4;
				break;
			case 'L':
				cd--;
				if(cd < 0) cd = 3;
				break;
			}
		}
		int area = (maxX - minX) * (maxY - minY);
		out.append(area).append('\n');
	}
	
	public static void updateRange() {
		minX = (minX > cx) ? cx : minX;
		maxX = (maxX < cx) ? cx : maxX;
		minY = (minY > cy) ? cy : minY;
		maxY = (maxY < cy) ? cy : maxY;
	}

}
