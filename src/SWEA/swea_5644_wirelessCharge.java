package SWEA;

import java.util.*;
import java.io.*;

public class swea_5644_wirelessCharge {
	static class BC {
		int x;
		int y;
		int c;
		int p;
		
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
	static int[][] map; 
	static boolean[] visited;
	static int ans;
	static int N, M;
	static int[] A, B;
	static BC[] bc;
	static final int[] dx = {0, -1, 0, 1, 0};
	static final int[] dy = {0, 0, 1, 0, -1};
	
    public static void main(String args[]) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer ma, mb;
    	
    	int T = Integer.parseInt(br.readLine());
        
    	for(int tc=1; tc<=T; tc++) {
    		// 입력
    		ma = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(ma.nextToken()); // 이동 회수
        	M = Integer.parseInt(ma.nextToken()); // BC수
            map = new int[N][N];
            A = new int[N+1]; // 시작점부터 충전
            B = new int[N+1];
            bc = new BC[M];
            ans = 0;
            
            ma = new StringTokenizer(br.readLine());
            mb = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {  
				A[i] = Integer.parseInt(ma.nextToken());
				B[i] = Integer.parseInt(mb.nextToken());
			}
            
            int x, y, c, p;
            for (int i = 0; i < M; i++) {
            	 ma = new StringTokenizer(br.readLine());
            	 y = Integer.parseInt(ma.nextToken()) - 1;
            	 x = Integer.parseInt(ma.nextToken()) - 1;
            	 c = Integer.parseInt(ma.nextToken());
            	 p = Integer.parseInt(ma.nextToken());
            	 bc[i] = new BC(x, y, c, p);
			}
            
            
            int[] curA = {0, 0}, curB = {9, 9};
            int total = 0, max = 0, chargeA = 0, chargeB = 0;
            for (int i = 0; i <= N; i++) { // 이동하는 위치마다 충전
            	//이동
				curA[0] += dx[A[i]]; curA[1] += dy[A[i]];
				curB[0] += dx[B[i]]; curB[1] += dy[B[i]];
				
				max = 0;
				for (int n = 0; n < M; n++) {
					for (int m = 0; m < M; m++) {
						chargeA = charge(n, curA[0], curA[1]);
						chargeB = charge(m, curB[0], curB[1]);
						
						if(n != m)  total = chargeA + chargeB;
						else total = Math.max(chargeA, chargeB);
						
						if(max < total) max = total;
					}
				}
				ans += max;
			}
            System.out.println("#" + tc + " " + ans);
        }
    }

	private static int charge(int n, int x, int y) { // 배터리 번호, 현재 좌표
		BC b = bc[n];	
		return  Math.abs(b.x - x) + Math.abs(b.y - y) <= b.c  ? b.p : 0; // 범위 내라면 충전량을 반환하고 아니면 0반환
	}
}