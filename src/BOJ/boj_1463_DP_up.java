package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1463_DP_up {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int X = Integer.parseInt(br.readLine());
		
		int cnt[] = new int[X+1];
		int temp = 0, min = Integer.MAX_VALUE;
		
		for (int i = 2; i <= X; i++) {
			min = Integer.MAX_VALUE;
			
			if(i % 3 == 0) {
				temp = i / 3;
				if(min > cnt[temp] + 1) min = cnt[temp]+1;
			}
			if(i % 2 == 0) {
				temp = i / 2;
				if(min > cnt[temp] + 1) min = cnt[temp]+1;
			}
			temp = i-1;
			if(min > cnt[temp] + 1) min = cnt[temp]+1;
			
			cnt[i] = min;
		}
				
		System.out.println(cnt[X]);
	}
}
