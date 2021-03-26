package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17472_MakeBridge2 {
	static int N, M, landCnt, total;
	static int[] parents;
	static int[][] map, bridge;
	static boolean[][] visited;
	private static final int[] dx = {0, 0, 1, -1}; 
	private static final int[] dy = {1, -1, 0, 0};
	final static int INF = Integer.MAX_VALUE; // 없는 다리
	
	static class Edge {
		int v1, v2, w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		map = new int[N][M];
		
		// 입력
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken()) - 1; // 바다는 -1, 땅은 0 
			}
		}
		
		// 섬들 고유 번호 지정
		landCnt = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) { // 미등록 섬 발견
					landCnt++;
					bfs(i,j); // 섬 인덱싱	
				}
			}
		}
		
		bridge = new int[landCnt+1][landCnt+1]; // 각 섬간의 다리 길이 저장
		for (int i = 1; i <= landCnt; i++) { // 다리 길이 초기화
			for (int j = 1; j <= landCnt; j++) {
				bridge[i][j] = INF;
			}
		}
		
		// 각 섬들을 연결하는 가능한 모든 최소 다리길이를 구함
		visited = new boolean[N][M];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				
				if(map[x][y] > 0) { // 섬 발견
					for(int d = 0; d < 4; d++) { // 4방 탐색
						connect(x,y,d);  // 그 방향으로 다리 뻗기
					}
				}
			}
		}

		// 모든 다리를  ArrayList에 저장
		List<Edge> edges = new ArrayList<>(); 
		for (int v1 = 1; v1 <= landCnt ; v1++) { 
			for (int v2 = v1+1; v2 <= landCnt; v2++) {
				if(bridge[v1][v2] != INF) // 연결된 다리가 있다면
					edges.add( new Edge(v1, v2, bridge[v1][v2]) ); 
			}
		}
		Collections.sort(edges, (a,b) -> a.w - b.w); // 다리들 길이로 오름차순 정렬
		
		// MST
		parents = new int[landCnt + 1]; 
		for (int n = 1; n <= landCnt; n++) { // 단위 집합 생성
			parents[n] = n * -1;
		}	
		int cnt = 0; // 선택된 다리 수
		
		for (int e = 0; e < edges.size(); e++) {		
			if(union(edges.get(e).v1, edges.get(e).v2)) {
				total += edges.get(e).w;
				if(++cnt == landCnt-1) break; // 모든 섬이 연결되면 종료
			}
		}
		
		if(cnt < landCnt - 1) total = -1; // 경로가 없다면 
		System.out.println(total);
	}
	
	private static void bfs(int x, int y) {
		
		Queue<int[]> q = new ArrayDeque<int[]>(); // 맵의 좌표를 담음
		visited[x][y] = true;	
		q.add(new int[] {x, y});
		
		int nx = 0, ny = 0;
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			x = cur[0]; y = cur[1];
			map[x][y] = landCnt;
			
			for(int d = 0; d < 4; d++) { // 4방 탐색
				nx = x + dx[d]; ny = y + dy[d];
				// 다음 구역이 지도 내이고, 방문하지 않았다면 
				if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {		
					if (map[nx][ny] == 0) { // 미등록 땅이면 인덱싱
						visited[nx][ny] = true;	
						q.add(new int[] {nx, ny});
					}
					
				}
			}
		}
	}
	
	private static void connect(int r, int c, int d) {
		int nr = r, nc = c, len = 0, end = 0; // 다리 끝 섬 번호
		int start = map[r][c]; // 다리 시작 섬 번호
		while(true) {
			nr += dx[d];
			nc += dy[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == start ) break; // 맵을 넘거나, 시작 섬이 닿으면 종료	
			end = map[nr][nc];
			if(end > 0) { // 다른 섬에 닿으면
				if(len >= 2) //다리 길이가 2이상이면
					bridge[start][end] = Math.min(bridge[start][end], len); // 두 섬 사이의 다리 길이 최신화
				break; // 다리 연결 종료
			}
			len++;
		}
		
	}

	private static boolean union(int a, int b) { // a집합에게 b집합을 결합
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot; 
		return true;
	}
	
	private static int findSet(int s) { //대표를 찾음
		if(parents[s] < 0) return s;
		return parents[s] = findSet(parents[s]); // 경로 압축
	}
}
