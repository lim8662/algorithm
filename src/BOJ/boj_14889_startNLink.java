package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14889_startNLink {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] stats;
	static int[] startTeam;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		stats = new int[N][N];
		M = N/2; // 팀원 수
		startTeam = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	private static void comb(int cnt, int start) {
		if(cnt == M) {
			analysis();
			return;
		}
		
		for (int i = start; i < N; i++) {
			startTeam[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	private static void analysis() {
		int[] opponents = new int[M]; // 상대팀
		int idx = 0;
		boolean[] picked = new boolean[N];
		for (int i = 0; i < M; i++) 
			picked[startTeam[i]] = true;
		
		int Spoint = 0, Lpoint = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(picked[i] && picked[j]) Spoint += stats[i][j];
				if(!picked[i] && !picked[j]) Lpoint += stats[i][j];
			}
		}
		int diff = Math.abs(Spoint - Lpoint);
		
		ans = (diff < ans) ? diff : ans;
	}
}
