package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class boj_3040_snowWhite {
	static int[] nums = new int[9];
	static int[] pick = new int[7];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		for ( int i = 0; i < 9; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		comb(0, 0);
	}

	public static void comb(int cnt, int flag) {
		if (cnt == 7) { // 7명의 난쟁이를 뽑았으면
			if(sum() == 100) { // 합쳐서 100이면 출력하고 종료
				for(int n: pick) System.out.println(n);
				System.exit(0);
			}
			return; 
		}

		for (int i = 0; i < 9; i++) {
			if((flag & 1<<i) != 0) continue;
			
			pick[cnt] = nums[i];
			comb(cnt+1, flag | 1<<i);
		}
	}
	
	public static int sum() {
		int ans = 0;
		for(int n : pick) {
			ans += n;
		}
		return ans;
	}
}

