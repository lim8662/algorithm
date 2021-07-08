package BOJ;

import java.io.*;
import java.util.*;

public class boj_11723_set2 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer in = null;
		int N = Integer.parseInt(br.readLine()); // 연산 수
		StringBuilder out = new StringBuilder();
		int bit = 0;

		for (int i = 0; i < N; i++) { 
			in = new StringTokenizer(br.readLine());
			String cmd = in.nextToken();
			int x = 0;
			if(in.countTokens() > 0) {
				x = Integer.parseInt(in.nextToken());
			}
			switch (cmd) {
			case "add":
				bit |= (1 << x);
				break;
			case "remove":
				bit &= ~(1 << x);
				break;
			case "check":			 
				if ((bit & (1 << x)) != 0)
					out.append(1).append('\n');
				else
					out.append(0).append('\n');
				break;
			case "toggle":
				bit ^= (1 << x);
				break;
			case "all":
				bit = (1 << 21) - 1;
				break;
			case "empty":
				bit = 0;
				break;

			}
		}
		System.out.println(out.toString());

	}
}
