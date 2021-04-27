package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_12865_plainBackpack {
	
	static class Product{
		int w; int v;

		public Product(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	
	static int N, K;
	static int DP[][];
	
	public static void main(String[] args) throws IOException {

		input();
		System.out.println(DP[N][K]);
	}
	
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물품의 수
		K = Integer.parseInt(st.nextToken()); // 배낭 최대 무게
		DP = new int[N+1][K+1];
		
		int w = 0, v = 0;
		for (int i = 1; i <= N; i++) { // i개 까지의 물건을 이용해 최대 k의 무게의 배낭을 채운 최대 가치합을 구함 
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken()); // 물품의 무게
			v = Integer.parseInt(st.nextToken()); // 물품의 가치
			
			for (int k = 1; k <= K; k++) {
				if(w > k) { 
					DP[i][k] = DP[i-1][k];
				} else { // w(물품의 무게)가 k(최대 무게)이하인 경우
					DP[i][k] = Math.max(DP[i-1][k], v + DP[i-1][k-w]);
				}
			}
		}
	}
}
