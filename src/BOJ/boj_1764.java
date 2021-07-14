package BOJ;

import java.io.*;
import java.util.*;

public class boj_1764 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder out = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람 수
		int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람 수
		String[] notSeen = new String[M];
		HashMap<String, Integer> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) { // 듣도 못한 사람 set 저장
			set.add(br.readLine());
		}
		
		for (int i = 0; i < M; i++) { // 보도 못한 사람 저장
			notSeen[i] = br.readLine();
		}	
		Arrays.sort(notSeen); // 보도 못한 사람 정렬
			
		// 사전순 듣보잡 찾기
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(set.contains(notSeen[i])) {
				cnt++;
				out.append(notSeen[i] + "\n");
			}
		}	
		System.out.println(cnt);
		System.out.println(out.toString());
	}
}
