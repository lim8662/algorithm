

import java.util.*;

public class codility_demo_1 {
	// 배열에 없는 가장 작은 양의 정수 출력
	// A : 1~10만
	// N : -100만 - 100만
	
	public static void main(String[] args) {
		int[] A = {-1, 0, 1, 2, 3};
    	System.out.println(new codility_demo_1().solution(A) );
	}
	
	public int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(A);
        
        for (int i = 0; i < A.length; i++) {
        	set.add(A[i]);
        }
    
        for (int i = 1; i <= 1000000; i++) {
        	if(!set.contains(i))
        		return i;
        }
        
        return -1;
	}
}



