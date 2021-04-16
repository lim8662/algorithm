package BOJ;

import java.io.*;
import java.util.*;

public class boj_15961_rotatingSushi {
	
	static int N, D, K, C, max;
	static int[] sushi, picked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sushi = new int[N]; 
		picked = new int[D+1];
		
		for (int n = 0; n < N; n++) {
			sushi[n] = Integer.parseInt(br.readLine());
		}
		
		slide();
		
		System.out.println(max);
	}

	private static void slide() {
		int cur = 0, cnt = 0;
		
		//초기화
		for (int i = 0; i < K; i++) {
			cur = sushi[i];
			if(picked[cur] == 0) cnt++;
			picked[cur]++;
		}
		max = cnt;	
		
		for (int s = 0; s < N - 1; s++) { // 1접시씩 오른쪽으로 이동 
			if(max <= cnt) {
				if(picked[C] == 0) max = cnt + 1; 
				else max = cnt;
			}
			
			if(max > K) { // 최댓값이 나오면 종료
				 return;
			}
			
			// 첫 초밥을 빼고
			if(--picked[sushi[s]] == 0) cnt--;
			
			// 다음 1개 초밥을 더함
			if(picked[sushi[(K+s) % N]]++ == 0) cnt++;
		}	
	}
}

