package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_4335_escapeIsland {

	static int H, W;
	static int[][] map = new int[11][26];
	static int[][] used = new int[11][26];
	static int[][] memo = new int[9][1 << 24];
	
	
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
	 
	private static int getMaxChipCnt(int stage) {
		int dy = stage / W;
		int dx = stage % W;
		int max = 0;
		
		if(dy == H - 2 && dx == W - 1) return 0;
		
		if(dx == W -1) {
			 
		}
		
		// 네모 설치
		if(isClean(dy, dx)) {
			setNemo(dy,dx, 1);
			int ret = getMaxChipCnt(stage + 1) + 1;
			if(ret > max) max = ret;
			setNemo(dy,dx, 0); // 백트
		}
		
		// 네모 설치 안함
		int ret = getMaxChipCnt(stage + 1);
		if(ret > max) max = ret;
		
		return max;
	}
	
	static void init() {
		for (int y = 0; y < H; y++) {
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
			
			for (int h = 0; h < H; h++) { // 각 상자의 변의 길이 입력받기 x, y, z
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}				
			}
			init(); // memo 초기화
			int ret = getMaxChipCnt(0);
			System.out.printf("#%d %d\n", tc, ret);
		}
	}
	
	
	

	
}
