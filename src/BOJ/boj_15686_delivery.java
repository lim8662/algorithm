package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15686_delivery {
	static int N, M, H, B, cityDx = Integer.MAX_VALUE; // 도시의 치킨 거리
	static ArrayList<int[]> home = new ArrayList<>(); // 집의 위치
	static ArrayList<int[]> branch = new ArrayList<>(); // 치킨집 위치
	static int[] picked; // 선택된 치킨집
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 남길 치킨집 수
		picked = new int[M]; 
		
		char[] row;
		for (int i = 0; i < N; i++) { // 도시 입력 받기
			row = br.readLine().replaceAll(" ", "").toCharArray();
			for (int j = 0; j < N; j++) {		
				if(row[j] == '1') { // 집이면
					home.add(new int[] {i,j});
				}
				else if(row[j] == '2') { // 치킨집이면
					branch.add(new int[] {i,j});
				}
			}
		}
		H = home.size(); // 집 수
		B = branch.size(); // 치킨집 수
		
		// 치킨집 M개 뽑기
		combination(0, 0);
		
		System.out.println(cityDx);
	}
	// B개의 치킨집 중에 M개 뽑기
	static void combination(int cnt, int start) {
		if (cnt == M) { // 다뽑으면
			CDX(picked); // 치킨 거리 구하기
			return;
		}
		for (int i = start; i < B; i++) {
			picked[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}
	
	// 치킨 거리 구하기
	public static void CDX(int[] blist) { // 뽑힌 치킨집 인덱스 
		int[] dx = new int[H]; // 집들의 치킨 거리
		
		for(int h = 0; h < H; h++) { 
			int[] curH = home.get(h); // 현재 집 
			int min = Integer.MAX_VALUE; // 최소 거리
			
			for(int b = 0; b < M; b++) { // 뽑힌 치킨집들과의 치킨 거리 구하기 
				int[] curB = branch.get(blist[b]); // 현재 치킨집
				int dist = Math.abs(curH[0] - curB[0]) + Math.abs(curH[1] - curB[1]);
				min = (min > dist) ? dist : min; 
			}
			dx[h] = min;
		}
		// 도시의 치킨 거리 구하기
		int total = 0;
		for(int d : dx) total += d;
		cityDx = (cityDx > total) ? total : cityDx;
	}
}
