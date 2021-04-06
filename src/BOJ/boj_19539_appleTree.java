package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_19539_appleTree {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		int total = 0, h = 0, cnt = 0, cnt2 = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			h = Integer.parseInt(st.nextToken());
			total += h; // 높이의 합
			cnt2 += (h / 2); // 2를 줄수있는 횟수 
		}
		
		if(total % 3 == 0) {
			cnt = total / 3; // 물주는 총 횟수
			if(cnt <= cnt2 ) { // 2를 충분히 줄수있다면 가능 (1로 쪼갤 수 있음) 
				System.out.println("YES");
			} else
				System.out.println("NO");
			
		} else {
			System.out.println("NO");
		}		
	}	
}
