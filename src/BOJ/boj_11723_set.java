package BOJ;

import java.io.*;
import java.util.*;

public class boj_11723_set {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = null;
		int N = Integer.parseInt(br.readLine()); // 연산 수
		StringBuilder out = new StringBuilder();
		boolean[] set = new boolean[21];

		for (int i = 0; i < N; i++) { 
			in = new StringTokenizer(br.readLine());
			String cmd = in.nextToken();
			int x = 0;
			if(in.countTokens() > 0) {
				x = Integer.parseInt(in.nextToken());
			}
			switch (cmd) {
			case "add":
				if (!set[x])
					set[x] = true;
				break;
			case "remove":
				if (set[x])
					set[x] = false;
				break;
			case "check":
				if (set[x])
					out.append(1).append('\n');
				else
					out.append(0).append('\n');
				break;
			case "toggle":
				if (!set[x])
					set[x] = true;
				else
					set[x] = false;
				break;
			case "all":
				Arrays.fill(set, true);
				break;
			case "empty":
				Arrays.fill(set, false);
				break;

			}
		}
		System.out.println(out.toString());

	}
}
