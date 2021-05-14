package PRG;


import java.util.Arrays;

public class pg_wcc_s2_3 {
	// 이분탐색 예산
	public static void main(String[] args) {
		String[] s = {"1110","100111100","0111111010"};
    	System.out.println(Arrays.toString(new pg_wcc_s2_3().solution(s)) );
	}
	
	public String[] solution(String[] s) {
        String[] ans = new String[s.length];
        
        System.out.println(Integer.parseInt(s[0], 2));
        return ans;
    }
}



