package BOJ;

import java.io.*;
import java.util.*;

public class boj_17219_map {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder out = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 메모된 사이트 수
		int M = Integer.parseInt(st.nextToken()); // 찾는 사이트 수
		HashMap<String, String> map = new HashMap<>();

		for (int i = 0; i < N; i++) { // 사이트 맵에 저장
			st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), st.nextToken());
		}
		
		String site = null;
		for (int i = 0; i < M; i++) { // 비밀번호 찾기
			site =  br.readLine();
			if( map.containsKey(site) ) {
				out.append(map.get(site) + "\n");
			}
		}	
		System.out.println(out.toString());
	}
}
