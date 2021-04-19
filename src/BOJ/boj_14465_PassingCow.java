package BOJ;

import java.io.*;
import java.util.*;

public class boj_14465_PassingCow {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		boolean[] broken = new boolean[N+1];
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < B; i++) {
			broken[Integer.parseInt(br.readLine())] = true;
		}
		
		int repair = 0; // 초기화
		for (int i = 1; i <= K ; i++) {
			if(broken[i]) repair++;
		}
		
		for (int s = 2; s <= N-K+1; s++) { // 스위핑
			if(broken[s-1]) repair--; // 첫부분 제외	
			if(broken[s+K-1]) repair++; // 마지막 추가 	
			min = (min > repair) ? repair : min;
		}
		System.out.println(min);
	}
}
