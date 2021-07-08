package BOJ;

import java.io.*;
import java.util.*;

public class boj_1620_pokemon {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder out = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 도감 수
		int M = Integer.parseInt(st.nextToken()); // 문제 수
		String[] names =  new String[N+1];
		HashMap<String, Integer> map = new HashMap<>();
		String name = "";
		
		for (int i = 1; i <= N; i++) { // 도감 입력 받기
			name = br.readLine();
			names[i] = name;
			map.put(name, i);
		}
		
		for (int i = 0; i < M; i++) { // 문제 입력 받기
			String quiz =  br.readLine();
			System.out.println(quiz.charAt(0) - 0);
			if(quiz.charAt(0) < 65) { // 숫자면(유니코드 48~57숫자 65~90대문자 97~소문자) 
				int num = Integer.parseInt(quiz);
				out.append(names[num]).append("\n"); // 이름 출력
			} else { // 문자면
				out.append(map.get(quiz)).append("\n"); // 번호 출력
			}
		}
		System.out.println(out.toString());
	}
}
