

import java.util.*;

public class leetcode_1048 {
	// 배열에 없는 가장 작은 양의 정수 출력
	// A : 1~10만
	// N : -100만 - 100만
	
	public static void main(String[] args) {
		String[] A = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
    	System.out.println(new leetcode_1048().longestStrChain(A) );
	}
	
	public int longestStrChain(String[] words) {
		int max = 1;
		Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for(int i = 0; i < words.length; i++) {
        	map.put(words[i], 1);
        }
        
        for(int i = 1; i < words.length; i++) {
        	String cur = words[i];
        	StringBuilder sb;
        	
        	for(int j = 0; j < cur.length(); j++) {
        		sb = new StringBuilder(cur);
        		String sub = sb.deleteCharAt(j).toString();
        		
        		if(map.containsKey(sub)) {
        			map.put(cur, Math.max(map.get(cur), map.get(sub) + 1));
        			max = Math.max(max, map.get(sub) + 1);
        		}
        	}
        }
        return max;
    }
}



