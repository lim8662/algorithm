package SWEA;

import java.util.*;
import java.io.*;

public class swea_5656_brickBreaker2 {
	
	static int N, W, H, min;
    private static int[] dr = { -1, 1, 0, 0 };
    private static int[] dc = { 0, 0, -1, 1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = gifs(in.readLine());
        for (int t = 1; t <= TC; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = gifs(st.nextToken());
            W = gifs(st.nextToken());
            H = gifs(st.nextToken());
 
            int[][] map = new int[H][W];
            int total = 0;
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int c = 0; c < W; c++) {
                    map[r][c] = gifs(st.nextToken());
                    if (map[r][c] > 0)
                        total++;
                }
            }
 
            min = Integer.MAX_VALUE;
            go(0, total, map);
            System.out.println("#" + t + " " + min);
        }
    }
 
    // i번째 구슬을 떨어뜨리기
    static int bCnt;
    private static boolean go(int cnt, int remainCount, int[][] map) { // 구슬 순서, 남은 벽돌 수, 맵
        // 벽돌을 다깨서 남아있는 벽돌이 하나도 없다.
        if (remainCount == 0) {
            min = 0;
            return true;
        }
        if (cnt == N) {
            min = Math.min(min, remainCount);
            return false;
        }
        int[][] newMap = new int[H][W];
        // 모든 열에서 떨어뜨리는 시도
        for (int c = 0; c < W; c++) {
            int r = 0;
            while (r < H && map[r][c] == 0)
                ++r;
 
            // 벽돌을 찾을 수 없다면?
            if (r == H)
                continue;
 
            // 벽돌이 있다면?
            // 이전 구슬 배열 복사
            copy(map, newMap);
            // 터뜨리기
            bCnt = 0;
            boom(newMap, r, c, map[r][c]); // map, 좌표, power
            // 벽돌 내리기
            down(newMap);
            // 다음 구슬 처리
            if (go(cnt + 1, remainCount - bCnt, newMap))
                return true;
        }
        return false;
    }
 
    private static void down(int[][] map) {
        int tmp[] = new int[H];
        for (int c = 0; c < W; c++) {
            Arrays.fill(tmp, 0);
            int cnt = H - 1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    tmp[cnt--] = map[r][c];
                    map[r][c] = 0;
                }
            }
            for (int r = 0; r < H; r++) {
                map[r][c] = tmp[r];
            }
        }
    }
 
    private static void boom(int[][] map, int r, int c, int cnt) { // 연쇄 폭발
        bCnt++; 
        map[r][c] = 0;
        if(cnt == 1) return;
         
        for(int d=0; d<4; d++) {
            int nr = r;
            int nc = c;
            for(int k=1; k<cnt; k++) {
                nr += dr[d];
                nc += dc[d];
                if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
                    boom(map, nr, nc, map[nr][nc]);
                }
            }
        }
    }
 
    private static void copy(int[][] map, int[][] newMap) {
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                newMap[i][j] = map[i][j];
    }
 
    private static int gifs(String s) {
        return Integer.parseInt(s);
    }
}
