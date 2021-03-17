package BOJ;
import java.io.*;
import java.util.*;

class boj_16236_babyShark {
	
	static int N, level = 2, exp, sx, sy, ans;
	static int[][] map;
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static List<Integer> food;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 맵 크기
		map = new int[N][N];
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 9) {
					sx = i; sy = j;
				}
				else map[i][j] = cur;
			}
		}
		while(true) {
			//먹이 찾기
			food = new ArrayList<Integer>(); // 찾은 먹이 리스트
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			q.add(new int[] {sx, sy});
			visited[sx][sy] = true;
			
			int step = 0; // 이동한 칸수
			while(!q.isEmpty()) {
				
				int size = q.size();
				for (int i = 0; i < size; i++) { // 현 단계의 모든 칸을 탐색
					int[] cur = q.poll();
					int cx = cur[0];
					int cy = cur[1];
					int target = map[cx][cy];
					
					if(target != 0 && target < level) { // 먹이라면
						food.add((cx * 100) + cy ); // 리스트에 저장
					}
					
					if(food.isEmpty()) { // 먹이를 찾지 못했다면
						for (int d = 0; d < 4; d++) { // 4방 탐색하여 다음 방문할 칸 추가
							int nx = cx + dx[d];
							int ny = cy + dy[d];
							if(ny >= N || nx < 0 || nx >= N || ny < 0 // 경계를 넘거나
									|| visited[nx][ny]) continue; // 방문했다면 넘김
							
							if(map[nx][ny] > level) continue; // 자신보다 큰 물고기면 피함
							
							q.add(new int[] {nx, ny});
							visited[nx][ny] = true;
						}
					}
				}
				if(food.isEmpty()) step++; // 먹이를 찾지 못했다면 다음 단계로
				else break; // 찾았다면 탐색 중지
			}
			
			if(food.size() == 0) break; // 먹이 못찾았으면 종료
			
			//타겟팅
			Collections.sort(food);
			int target = food.get(0); // 목표의 좌표
			
			//이동
			ans += step;
			sx = target / 100;
			sy = target % 100;
			
			//식사
			map[sx][sy] = 0;
			if(++exp == level) { // 레벨업
				level++;
				exp = 0;
			}
			
		}
		System.out.println(ans);
	}
}
