
import java.util.*;

public class leetcode_1048 {
	// String chain
	// return max chain length

	public static void main(String[] args) {
		String[] A = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };
		System.out.println(new leetcode_1048().longestStrChain(A));
	}

	public int longestStrChain(String[] words) {
		int max = 1;
		Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
		Map<String, Integer> map = new HashMap<>();

		for (String cur : words) {
			map.put(cur, 1);
			int best = 0;

			for (int j = 0; j < cur.length(); j++) {
				StringBuilder sb = new StringBuilder(cur);
				String sub = sb.deleteCharAt(j).toString();
				best = Math.max(best, map.getOrDefault(sub, 0) + 1);
			}
			map.put(cur, best);
			max = Math.max(max, best);
		}
		return max;
	}
}
