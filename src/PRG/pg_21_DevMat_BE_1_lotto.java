package PRG;

import java.util.Arrays;

public class pg_21_DevMat_BE_1_lotto {

	public static void main(String[] args) {
		int[] lottos = { 0, 0, 0, 0, 0, 0 };
		int[] win_nums = {38, 19, 20, 40, 15, 25};
		System.out.println(Arrays.toString(new pg_21_DevMat_BE_1_lotto().solution(lottos, win_nums)));
	}
	
	static int[] rank = {6, 6, 5, 4, 3, 2, 1}; // �´� ���ں� ����
	
	public int[] solution(int[] lottos, int[] win_nums) {
		
		Arrays.sort(lottos);
		
		int blank = 0; // �� ĭ�� ��
		int match = 0; // ���� ��ȣ�� ��
		for (int i = 0; i < lottos.length; i++) {
			int cur = lottos[i];
			if(cur == 0) {
				blank++; continue;
			}
			for (int j = 0; j < win_nums.length; j++) {
				if(cur == win_nums[j]) {
					match++; break;
				}
			}
		}
		return new int[] {rank[match + blank], rank[match]};
	}

}
