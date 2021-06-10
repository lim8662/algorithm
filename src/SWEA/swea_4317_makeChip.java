package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_4317_makeChip {

	static int H, W;
	static int[][] map;
	static int[][] used;
	static int[][] memo;
	
	static void setNemo(int dy, int dx, int val) {
		used[dy][dx] = used[dy][dx + 1] = used[dy + 1][dx] = used[dy+1][dx+1] = val;
		if(val == 1) used[dy][dx] = 2;
	}
	
	static boolean isClean(int dy, int dx) { // 이미 사용중이거나 불량이 있다면 false반환 / 사용 가능하면 true반환
		if(dx >= W - 1) return false;
		if( used[dy][dx] + used[dy][dx + 1] + used[dy + 1][dx] + used[dy+1][dx+1] > 0) return false;
		if( map[dy][dx] + map[dy][dx + 1] + map[dy + 1][dx] + map[dy+1][dx+1] > 0) return false;
		return true;
	}
	 
	static int getState(int dy) {
		int state = 0;
		
		for (int i = 0; i < W-1; i++) {
			state <<= 1;
			if(used[dy][i] == 2) state |= 0x1;
		}
		
		return state;
	}
	
	private static int getMaxChipCnt(int stage) {
		int dy = stage / W;
		int dx = stage % W;
		int max = 0;
		int state = 0;
		
		if(dy == H - 2 && dx == W - 1) return 0;
		
		if(dx == W -1) {
			state = getState(dy);
			if( memo[dy][state] != -1) return memo[dy][state];
		}
		
		// 네모 설치
		if(isClean(dy, dx)) {
			setNemo(dy,dx, 1);
			int ret = getMaxChipCnt(stage + 1) + 1;
			if(ret > max) max = ret;
			setNemo(dy,dx, 0); // 백트래킹
		}
		
		// 네모 설치 안함
		int ret = getMaxChipCnt(stage + 1);
		if(ret > max) max = ret;
		
		if( dx == W - 1) { // 끝에 도달했을 경우만 메모
			memo[dy][state] = max;
		}
		return max;
	}
	
	static void init() {
		for (int y = 0; y < H - 1; y++) {
			Arrays.fill(memo[y], -1) ;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/SWEA/4335.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new int[W+1][H+1];
			used = new int[W+1][H+1];
			memo = new int[W+1][(1 << H+1) + 1];
			
			for (int h = 0; h < H; h++) { // 각 상자의 변의 길이 입력받기 x, y, z
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[w][h] = Integer.parseInt(st.nextToken());
				}				
			}
			
			int temp = H;
			H = W;
			W = temp;
			
			init(); // memo 초기화
			int ret = getMaxChipCnt(0);
			System.out.printf("#%d %d\n", tc, ret);
		}
	}
}
