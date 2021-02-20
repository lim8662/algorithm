package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17135_castleDefence {
	static int N, M, D;
	static int[][] map;
	static int enemyCnt; // 적의 수
	static int max = 0; // 최대 킬수
	static int[] position = new int[3];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행 크기
		M = Integer.parseInt(st.nextToken()); // 열 크기
		D = Integer.parseInt(st.nextToken()); // 궁수 사정 거리
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) { // 적 위치 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				char cur = st.nextToken().charAt(0);
				if(cur == '1') { // 적이면
					enemyCnt++;
					map[i][j] = 1;
				} 
			}
		}
		comb(0,0); // 궁수 배치
		
		System.out.println(max);
	}
	// 궁수 열 위치 정하기
	public static void comb(int cnt, int start) {
		
		if (cnt == 3) { // 세 궁수의 위치가 정해지면
//			System.out.println(Arrays.toString(position));
			game(position); // 게임 시작
			return;
		}
		for (int i = start; i < M; i++) { 
			position[cnt] = i;
			comb(cnt + 1, i + 1);
		}
		
	}
	
	public static void game(int[] position) {
		int[][] tmap = new int[N][M];
		
		for (int c = 0; c < M; c++) { // 맵 복사
			for (int r = 0; r < N; r++) {
				tmap[r][c] = map[r][c]; 
			}
		}
		
		int[][] archer = new int[3][]; // 궁수 위치 저장
		for (int i = 0; i < 3; i++) {
			archer[i] = new int[] {N, position[i]};
		}
		
		int kill = 0, invade = 0;
		while(kill + invade < enemyCnt) { // 모든 적이 제외될 때까지 턴 반복
			
			for(int a = 0; a < 3; a++) { // 궁수들 공격 대상 선정
				int min = 100; // 타겟 과의 거리
				int[] target = new int[2]; // 타겟 위치
				int range = (D < N) ? N-D : 0; // 탐색 행 범위
				
				for (int c = 0; c < M; c++)  // 왼쪽 열 우선 탐색하며
				for (int r = range; r < N; r++) {
					if(tmap[r][c] > 0) { // 적(다른 궁수의 타겟 포함)이고
						int dist = Math.abs(archer[a][0] - r) + Math.abs(archer[a][1] - c);
						if(dist <= D && min > dist) { // 사거리 안에서 가장 가깝다면
							min = dist; // 최소거리 갱신
							target[0] = r; target[1] = c; // 타겟 갱신
						}
					}
				}
				if(min < 100) // 타겟 선정 되었다면
					tmap[target[0]][target[1]] = 2; // 맵에 타겟 표시
			}
			
			// 궁수 동시 공격
			for (int c = 0; c < M; c++)  
			for (int r = 0; r < N; r++) {
				if(tmap[r][c] == 2) { // 타겟이면
					tmap[r][c] = 0; kill++;	// 제외 후 킬 수 증가
				}
			}

			// 적 이동
			for (int c = 0; c < M; c++) { // N-1 행은 적이 있다면 침입자 수 증가
				if(tmap[N-1][c] == 1) invade++;
			}
			for (int r = N-2; r >= 0; r--) { // N-2 행부터 0행 까지 아래 행으로 복사
				tmap[r+1] = tmap[r];
			}
			tmap[0] = new int[M]; // 0행은 전부 0	
		}
		max = (max < kill) ? kill : max; // 최대 킬수 갱신
	}
}
