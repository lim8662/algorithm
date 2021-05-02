package BOJ;

import java.io.*;
import java.util.*;

public class boj_2846_uphill {
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] h = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			h[i]= Integer.parseInt(st.nextToken());
		}
		
		Deque<Integer> dq = new ArrayDeque<>();
		dq.add(h[0]);
		int max = 0;
		for (int i = 1; i < N; i++) {
			if(dq.peekLast() < h[i]) {
				dq.addLast(h[i]);
			} else {
				int size = dq.peekLast() - dq.peekFirst();
				max = (max < size) ? size : max;
				dq.clear(); dq.add(h[i]);
			}
		}
		if(dq.size() >= 2) {
			int size = dq.peekLast() - dq.peekFirst();
			max = (max < size) ? size : max;
		}
		System.out.println(max);
	}
}
