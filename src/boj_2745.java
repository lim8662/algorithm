import java.io.*;
import java.util.*;

class boj_2745 {

/*
 *
������ȯ
N(��) B(���� 2~36)
A(10) .... Z(35)
B������ N��  10�������� ���
*/
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		int power = N.length()-1;
		
		int B = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N.length(); i++) {
			char num = N.charAt(i);
			if(num >= 'A' && num <= 'Z') {
				ans += Math.pow(B, power--) * (num - 55);
			} 
			else {
				ans += Math.pow(B, power--) * (num - 0);
			}
		}
		System.out.println(ans);
	}
	
}
