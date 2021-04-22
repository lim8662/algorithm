package SWEA;

import java.util.*;
import java.io.*;

public class swea_2115_collectHoney {
	
	static class Bucket implements Comparable<Bucket>{
		int r; int c;
		int profit;
		
		public Bucket(int r, int c, int profit) {
			this.r = r;
			this.c = c;
			this.profit = profit;
		}

		@Override
		public int compareTo(Bucket o) {
			return o.profit - this.profit;
		}	
	}
	static int N, M, C, ans, max;
	static int[][] honey;
	static boolean[][] isEmpty;
	static PriorityQueue<Bucket> pq = new PriorityQueue<>(); 
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	StringTokenizer st = null;
    	
    	for(int tc=1; tc<=T; tc++) {    	
            // 입력
    		st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            honey = new int[N][N];
            isEmpty = new boolean[N][N];
            ans = 0;
            pq.clear();
            
            for (int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}
            
            collect();
            
            System.out.printf("#%d %d\n", tc, ans); 
        }    
    }

	private static void collect() {
		for (int r = 0; r < N; r++) // 가능한 모든 채집 경우의 수를 확인
		for (int c = 0; c <= N-M; c++) {
			
			int[] honeys = new int[M];
			for (int i = 0; i < M; i++) {
				honeys[i] = honey[r][c+i]; 
			}
			
			max = 0; // 해당 채집구간에서 가능한 최대 수익
			boolean[] visited = new boolean[M];
			selectHoney(honeys, visited, 0); // 채집통 내에서 최대 수익이 되도록 꿀 선택
			
			pq.add(new Bucket(r, c, max)); // 가능한 채집통을 수익 오름차순 정렬
		}
		
		Bucket first = pq.poll(); // 가장 수익이 큰 구간 채취
		ans += first.profit;
		for (int i = 0; i < M; i++) { 
			isEmpty[first.r][first.c + i] = true;
		}
		
		// 두번째로 수익이 큰 구간 채취
		while(true) {
			Bucket second = pq.poll();
			if(isvalid(second)) { // 첫번째와 중복되지 않는다면 채취 후 종료 
				ans += second.profit;
				return;
			}
		}
	}
	
	static boolean isvalid(Bucket second) {
		for (int i = 0; i < M; i++) {
			if(isEmpty[second.r][second.c + i]) return false; // 꿀이 이미 채집 되었다면 무효
		}	
		return true;
	}
	
	static void selectHoney(int[] honeys, boolean[] visited, int idx) { // 얻은 M개의 꿀의 부분 집합
	    if(idx == M) { // 뽑은 꿀의 합이 C 이하면 수익 최댓값 갱신
	        int sum = 0, profit = 0;
	        
	    	for (int i = 0; i < M; i++) {
				if(visited[i]) {
					sum += honeys[i];
					profit += honeys[i] * honeys[i];
				}
			}
	    	if(sum <= C && max < profit) {
	    		max = profit;
	    	}
	        return;
	    }
	 
	    visited[idx] = false;
	    selectHoney(honeys, visited, idx + 1);
	 
	    visited[idx] = true;
	    selectHoney(honeys, visited, idx + 1);
	}
}