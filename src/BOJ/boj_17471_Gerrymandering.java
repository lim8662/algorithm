package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17471_Gerrymandering {
	
	static int N;
	static int[] population, ward;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
	
		ward = new int[N + 1]; 
		for (int n = 1; n <= N; n++) { // 단위 선거구 생성
			ward[n] = population[n] * -1;	
		}	
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int adj = Integer.parseInt(st.nextToken()); // 인접 구역 수
			for (int n = 0; n < adj; n++) {
				union(i, Integer.parseInt(st.nextToken()));
			}
		}
		int cnt = 0; // 연결된 지역집합 수
		int[] total = new int[N]; // 각 지역집합의 총 인구
		for (int i = 1; i <= N; i++) {
			if(ward[i] < 0) {
				total[cnt++] = ward[i];  	
			}
		}
		int ans = 0;
		if(cnt > 2) ans = -1; // 선거구를 나눌 수 없음
		else if(cnt == 2) { // 구역 집합이 2개일 때
			ans = Math.abs(total[0] - total[1]);
		} else { // 1개일 때
			
		}
		System.out.println(ans);
	}

	private static boolean union(int a, int b) { // a집합에게 b집합을 결합
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		ward[aRoot] += ward[bRoot];
		ward[bRoot] = aRoot; 
		return true;
	}
	
	private static int findSet(int s) { //대표를 찾음
		if(ward[s] < 0) return s;
		return findSet(ward[s]); 
	}

}
