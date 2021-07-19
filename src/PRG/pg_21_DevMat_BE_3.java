package PRG;

import java.util.Arrays;
import java.util.HashMap;

public class pg_21_DevMat_BE_3 {

	public static void main(String[] args) {
		String[] e = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] r = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] s = {"young", "john", "tod", "emily", "mary"};
		int[] a= {12, 4, 2, 5, 10};
		System.out.println(Arrays.toString(new pg_21_DevMat_BE_3().solution(e,r,s,a)));
	}

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] total = new int[enroll.length];
        HashMap<String, String> pname = new HashMap<>(); // 판매원의 부모명
        HashMap<String, Integer> id = new HashMap<>(); // 판매원의 ID
        
        for (int i = 0; i < enroll.length; i++) {
			pname.put(enroll[i], referral[i]);
			id.put(enroll[i], i);
		}
        
        for (int i = 0; i < seller.length; i++) {
			int profit = amount[i] * 100;
			int tax = 0, mine = 0;
			String cname = seller[i]; // 현재 판매원 이름
        	
			while(!cname.equals("-")) {
        		tax = profit / 10;
    			mine = profit - tax;
    			total[id.get(cname)] += mine; // 현재 판매원 수익 더함
    		
    			cname = pname.get(cname); // 추천인으로 이동
    			profit = tax;
        	}
			
		}
        return total;
    }
}
