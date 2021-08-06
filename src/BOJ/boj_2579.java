package BOJ;

import java.io.*;
import java.util.*;

public class boj_2579 {
	// 계단 오르기 
	// 얻을 수 있는 총 점수의 최댓값 리턴
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 계단의 수(1~300개)
		int ans = 0;
		Integer[][] dp = new Integer[N][2];
		int[] stairs = new int[N+1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine()); // 계단 점수 입력(1~1만)
		}
		// 1~2칸 이동 가능, 3연속 1칸은 불가능, 마지막 계단에 도착
		
		
		
		
		System.out.println(ans);
	}
	
	
}
