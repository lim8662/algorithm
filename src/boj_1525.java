

import java.io.*;
import java.util.*;

class boj_1525 {

/*
���� ����

1 0 3
4 2 5
7 8 6
��
1 2 3
4 5 6
7 8 0
�̵Ǵ� �ּ� �̵� Ƚ�� ��ȯ
*/
	
	static HashMap<Integer, Integer> map;
	static Queue<Integer> q;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 2���� ������ 1�������� �ٲ��ش�. 
		int start = 0;
		for(int i = 0 ; i < 3 ; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; ++j) {
				int temp = stoi(st.nextToken());
				// 0�� �� 9�� �ٲ��ش�. 
				if(temp == 0) temp = 9;
				start = (start * 10) + temp;
			}
		}
		
		q = new LinkedList<>();
		map = new HashMap<>();
		
		q.offer(start);
		map.put(start, 0);
		
		System.out.println(bfs());
		
	}
	
	private static int bfs() {
		while(!q.isEmpty()) {
			// ť���� ���� ������ ���¸� ���ڿ��� �ٲ� 9�� ��ġ�� ã�´�.
			int cur_int = q.poll();
			String cur = String.valueOf(cur_int);
			
			// ������ ������ ������ ��� �ʿ��� �ð��� ������ �����Ѵ�. 
			if(cur.equals("123456789")) {
				return map.get(cur_int);
			}
			
			int nine = cur.indexOf('9');
			
			// ���� 9�� ��ǥ�� �˾Ƴ���. 
			// �� ������ ��, ������ ������ ���� ��Ÿ����.  2 -> (0, 2) 3x3��ǥ
			//1���� �迭 �ε��� ���� 0~8 -> 2���� 0~2�� ����
			int r = nine / 3;
			int c = nine % 3;
			
			for(int i = 0 ; i < 4 ; ++i) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				if(nr >= 3 || nr < 0 || nc >= 3 || nc < 0) continue;
				
				StringBuilder next_str = new StringBuilder(cur);
				// r * 3 + c �� ��ǥ�� 1���� �迭������ ��ġ��.
				// ���� 9�� ��ġ�� ���� ��ġ�� ���ڸ� �ٲ۴�. 
				char temp = next_str.charAt(nr * 3 + nc);
				next_str.setCharAt(nr * 3 + nc, '9');
				next_str.setCharAt(nine, temp);
				
				int next = stoi(next_str.toString());
				
				// ������ ���� ������ ������ ��쿡�� �ʿ� �߰��Ѵ�. 
				if(!map.containsKey(next)){
					map.put(next, map.get(cur_int) + 1);
					q.offer(next);
				}
			}
		}
		return -1;
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
