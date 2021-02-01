import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_D3_1289 {
	
	static char[] target, init;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		for(int tc = 1; tc <= N; tc++) {
			//문자열 입력
			target = br.readLine().toCharArray();
			int l = target.length;
			
			//초기화
			init = new char[l];
			for(int i=0; i < l; i++) init[i] = '0';
			
			// 탐색하면서 검사를 따로 안하고 다를때만 cnt++ 
			cnt = 0;
			for(int i = 0; i < l; i++) {
				if(init[i] == target[i]) continue;
				
				//다르면 바꿔주기
				conversion(i);
				cnt++;
			}
			System.out.printf("#%d %d\n", tc, cnt);
		}	
	}
	
	static void conversion(int idx) {
		int cnt = 0;
		for(int i = idx; i < init.length; i++) {
			init[i] = target[idx];
		}
	}
}
