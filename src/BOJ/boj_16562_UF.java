package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16562_UF {
	static int N, M, k;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계 수
		k = Integer.parseInt(st.nextToken()); // 예산
		parents = new int[N + 1]; // 상위 친구의 번호를 저장, 대표는 친구비 저장
		
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) { // 단위 집합 생성
			parents[n] = Integer.parseInt(st.nextToken()) * -1;
		}
		
		int s1 = 0, s2 = 0;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			s1 = Integer.parseInt(st.nextToken());
			s2 = Integer.parseInt(st.nextToken());
			
			union(s1, s2);
		}
		
		// 최소 친구비 계산
		int total = 0;
		for (int i = 1; i <= N; i++) {
			if(parents[i] < 0) { // 친구 집합의 대표면
				total += parents[i];
			}
		}
		//System.out.println(Arrays.toString(parents));
		if(k >= -total) { // 예산 내이면
			System.out.println(-total);
		} else {
			System.out.println("Oh no");
		}
	}
	
	private static boolean union(int a, int b) { // a집합에게 b집합을 결합
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		if(parents[aRoot] > parents[bRoot]) { // 친구비가 더 작은 학생을 대표로 함 ex) -10 > -20
			parents[bRoot] = aRoot; // b의 대표자의 대표를 a로 함
		} else {
			parents[aRoot] = bRoot; 
		}
		return true;
	}
	
	private static int findSet(int s) { //대표를 찾음
		if(parents[s] < 0) return s;
		return parents[s] = findSet(parents[s]); // 경로 압축
		
	}
}
