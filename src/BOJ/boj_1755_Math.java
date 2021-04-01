package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1755_Math {
	
	public static class Number implements Comparable<Number>{
		int numI; // 숫자
		String numS; // 숫자의 영문 문자열
		
		public Number(int numI, String numS) {
			this.numI = numI;
			this.numS = numS;
		}

		@Override
		public int compareTo(Number o) { // 숫자의 영문 문자열 사전순 정렬
			return this.numS.compareTo(o.numS);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int M = Integer.parseInt(st.nextToken()); // 최소숫자
		int N = Integer.parseInt(st.nextToken()); // 최대숫자
		
		String[] spell = {"zero", "one", "two", "three", "four", "five"
				, "six", "seven", "eight", "nine"}; 
		
		Number[] nums = new Number[N-M+1]; // Number 객체 저장 배열
		int idx = 0; // nums의 인덱스
		
		for (int i = M; i <= N; i++) { // M부터 N까지 정수를 영어로 변환
			String numStr = "";
			if(i >= 10) { // 두자리수
				numStr = spell[i/10] + " " + spell[i%10];
				
			} else { // 한자리수
				numStr = spell[i];
			}	
			nums[idx++] = new Number(i, numStr); // 배열에 저장		
		}
		
		Arrays.sort(nums); // Number 객체를 문자열 사전순으로 정렬
		
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < nums.length; i++) {
			if(i != 0 && i % 10 == 0) out.append("\n"); // 한 줄에 10개씩 출력
			out.append(nums[i].numI).append(" ");
		}
		
		System.out.println(out.toString()); // 출력
	}

}
