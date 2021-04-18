package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_17471_Gerrymandering {
	
	static int N; // 구역 수
	static int[] picked, population; // 구역별 인구
	static List<Integer>[] adjArea; // 구역 인접 정보
	static boolean[] visited;
	static int minGap = Integer.MAX_VALUE;
	static ArrayList<Integer> A, B; // 선거구
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		input(); // 입력	
		
		picked = new int[N+1];
        for (int i = 1; i <= N/2; i++) { // 구역 나누기
        	selectArea(i, 0, 0, 0);
        }
		System.out.println((minGap == Integer.MAX_VALUE) ? -1 : minGap);
	}
	
	// 조합으로 size만큼 구역 선택
	private static void selectArea(int size, int cnt, int start, int pick) {
		if(size == cnt) {
			getGap(pick); return;
		}
		
		for(int i = start; i < N; i++) {
			if( (pick & 1<<i) != 0) continue;
			selectArea(size, cnt+1, i+1, pick | 1<<i);
		}
		
	}
	
	private static void getGap(int pick) {
		A = new ArrayList<>(); // 선거구의 지역리스트
		B = new ArrayList<>();
		
		for (int i = 0; i < N; i++) { // 선택된 구역은 A선거구로 지정
			if((pick & 1<<i) != 0) A.add(i+1);
			else B.add(i+1); // 미선택 구역은 B선거구로 지정
		}
		
		int a = 0, b = 0; // 각각 연결된 선거구면 인구차 계산 
		if(isConnected(A) && isConnected(B)) {
			for(int area : A) a += population[area]; 
			for(int area : B) b += population[area]; 
			minGap = Math.min(minGap, Math.abs(a-b));
		}
	}

	private static boolean isConnected(ArrayList<Integer> ward) {
		visited = new boolean[N+1];
		if(ward.size() == 1) return true;
		
		dfs(ward.get(0), ward); // 연결된 구역 방문하기
		
		for(int area : ward) { // 미방문 구역이 있다면 연결 x
			if(!visited[area]) {
				return false;
			}
		}
		return true;
	}

	private static void dfs(int cur, ArrayList<Integer> ward) { // 선거구의 구역들을 모두 방문
		visited[cur] = true;
		
		for (int i = 1; i < ward.size(); i++) {
			int next = ward.get(i); // 선거구 다른 구역
			
			if(!visited[next] && adjArea[cur].contains(next)) // 미방문이고 연결되어있다면 방문
				dfs(next, ward);
		}
		
	}

	private static void input() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjArea = new ArrayList[N+1];
		population = new int[N+1];
		visited = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int adj = Integer.parseInt(st.nextToken()); // 인접 구역 수
			adjArea[i] = new ArrayList<>();
			for (int n = 0; n < adj; n++) {
				adjArea[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}
}

	
