package BOJ;

import java.io.IOException;
import java.util.Scanner;

public class boj_2011_passcode {
	static int N, r ,c;
	static final int[][] order = {{0,0}, {0,1}, {1,0}, {1,1}}; // Z 방문 순서
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		if(s.charAt(0)=='0') { // 시작이 0이면 잘못된 암호
			System.out.println("0");
			return;
		}
		
		long[] dp=new long[s.length()+1];
		dp[0]=dp[1]=1;
		for(int i=2;i<=s.length();i++) {
			char ch=s.charAt(i-1); // 현재 체크하는 문자
			char prev=s.charAt(i-2); // 앞 문자
			if(ch=='0') {
				if(prev=='1' || prev=='2') dp[i]=dp[i-2]%1000000; 
				 // 현재 문자 0을 앞과 연결할 수 없다면 잘못된 문자열이므로 종료
				else break;
			}
			else {
				// 앞 문자가 0이면 경우의 수 변화 없음
				if(prev=='0') dp[i]=dp[i-1]%1000000;
				else { // 앞 문자와 연결할 수 있는지 체크
				 	// 앞 문자와 연결했을 때 숫자를 int형으로 출력
					int temp=(prev-'0')*10+(ch-'0');
					// 암호 코드안에 들어오면 경우의 수 갱신
					if(1<=temp && temp<=26) dp[i]=(dp[i-2]+dp[i-1])%1000000;
					else dp[i]=dp[i-1]%1000000;
				}
			}
		}
		
		System.out.println(dp[s.length()]%1000000);
	}
}
