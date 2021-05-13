package PRG;


import java.util.Arrays;

public class pg_wcc_s2_2 {
	// 이분탐색 예산
	public static void main(String[] args) {
		long[] a = {2, 7};
    	int[] b = {3, 11};
    	System.out.println(Arrays.toString(new pg_wcc_s2_2().solution(a)) );
	}
	
	public long[] solution(long[] nums) {
        long[] ans = new long[nums.length];
        StringBuilder cs, str;
        
        for (int i = 0; i < nums.length; i++) {
        	long cur = nums[i]; 
        	int c = 0;
        	cs = new StringBuilder(Long.toBinaryString(cur)).reverse();
        	while(true) {
        		cur++;
        		c++;
        		int mismatch = 0; 
        		str = new StringBuilder(Long.toBinaryString(cur)).reverse();
        		
        		for(int n = 0; n < str.length(); n++ ) {
        			if(n >= cs.length()) {
        				if(str.charAt(n) != '0') mismatch++;
        			} else {
        				if(str.charAt(n) != cs.charAt(n)) mismatch++;
        			}
        			
        		}
        		
        		if(mismatch <= 2) break;
        	}
        	
        	ans[i] = cur;
		}
        
        return ans;
    }
}



