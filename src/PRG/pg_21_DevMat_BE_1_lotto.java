package PRG;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class pg_21_DevMat_BE_1_lotto {

	public static void main(String[] args) {
		int[] lottos = { 0, 0, 0, 0, 0, 0 };
		int[] win_nums = {38, 19, 20, 40, 15, 25};
		System.out.println(Arrays.toString(new pg_21_DevMat_BE_1_lotto().solution(lottos, win_nums)));
	}
	
	static int[] rank = {6, 6, 5, 4, 3, 2, 1}; // 맞는 숫자별 순위
	
	public int[] solution(int[] lottos, int[] win_nums) {
		int blank = 0; // 빈 칸의 수
		int match = 0; // 맞춘 번호의 수
		Map<Integer, Boolean> map = new HashMap<>();
		
		for (int lotto : lottos) {
			if(lotto == 0) {
				blank++; continue;
			}
			map.put(lotto, true);
		}
		
		for (int win : win_nums) {
			if(map.containsKey(win)) match++;
		}
		return new int[] {rank[match + blank], rank[match]};
	}
}
