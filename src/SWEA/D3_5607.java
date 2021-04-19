package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_5607 {
	
		static int N, R, ans;
		static final int p = 1234567891;
		static long[] fac = new long[1000000 + 1]; // N! mod p 
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
			
			fac[0] = 1;
			for (int i = 1; i <= 1000000; i++) {
				fac[i] = (fac[i-1] * i) % p ;
			}

			
			for(int tc = 1; tc <= T; tc++)
			{	
				st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
	            R = Integer.parseInt(st.nextToken());
	                 
	            ; //nCr % p
	            System.out.printf("#%d %d\n",tc, comb());		  
			}
		}
	
		private static long comb() {
			long top = fac[N];
			long bottom = ( fac[N-R] * fac[R] ) % p;
			
			return (top * pow(bottom, p-2) ) % p;
			
		}
	
		private static long pow(long n, int i) {
			if(i == 0) return 1;
			
			long half = pow(n, i/2);
			
			if( i % 2 == 0) {
				return ( (half % p) * (half % p) ) % p; 
			} else {
				return ( ((half * n) % p) * (half % p) ) % p;
			}
		}
}
