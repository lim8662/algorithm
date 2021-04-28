package BOJ;

import java.io.*;
import java.util.*;

public class boj_21611_blizzard2 {
	static int N, M, centerN, result;
    static int[][] map;
    static boolean[][] visit;
    static int[][] attackDir;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 공격 방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        centerN = N / 2;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        attackDir = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            attackDir[i][0] = Integer.parseInt(st.nextToken()) - 1;
            attackDir[i][1] = Integer.parseInt(st.nextToken());
        }

        // 처리
        solution();

        // 출력
        System.out.println(result);
    }

    private static void solution() {
        for (int m = 0; m < M; m++) { // 마법 진행 회수
            // 타워 -> 바이러스 공격
            attackTower(m);

            // 달팽이 배열 돌면서 ArrayList로 뽑아내기
            ArrayList<Integer> mapList = getArrayList();

            // 0 공간 없애기
            mapList = removeZero(mapList);

            // 4개 이상 연속한 바이러스라면 제거 후 점수 획득 및 반복
            getScore4th(mapList);

            // 군집합 형성
            mapList = spreadVirus(mapList);

            // map에 삽입
            insertMap(mapList);
        }
    }

    private static void insertMap(ArrayList<Integer> mapList) {
        visit = new boolean[N][N];
        visit[centerN][centerN] = true;
        int i = 0;
        int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int row = centerN, col = centerN, curD = 1;
        while (row != 0 || col != 0) {
            int nr = row + d[curD][0];
            int nc = col + d[curD][1];
            row = nr;
            col = nc;

            if(i >= mapList.size()){
                break;
            }
            map[nr][nc] = mapList.get(i++);
            visit[nr][nc] = true;

            int nextD = (curD + 1) % 4;
            nr = row + d[nextD][0];
            nc = col + d[nextD][1];
            if (!visit[nr][nc]) {
                curD = nextD;
            }
        }
    }

    private static ArrayList<Integer> spreadVirus(ArrayList<Integer> mapList) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            int pivot = mapList.get(i);
            int count = 0;
            int j;
            for (j = i; j < mapList.size(); j++) {
                if(pivot == mapList.get(j)){
                    count++;
                }else{
                    break;
                }
            }
            arrayList.add(count);
            arrayList.add(mapList.get(i));
            i = --j;
        }
        return arrayList;
    }

    private static void getScore4th(ArrayList<Integer> mapList) {
        while(true){
            boolean flag = false;
            for (int i = 0; i < mapList.size(); i++) {
                // 기준
                int pivot = mapList.get(i);
                int count = 0;
                int j, k = mapList.size();
                for (j = i; j < mapList.size(); j++) {
                    if(pivot == mapList.get(j)){
                        count++;
                    }else{
                        k = j;
                        break;
                    }
                }
                if(count >= 4){
                    flag = true;
                    for (int l = i; l < k; l++) {
                        result += mapList.remove(i);
                    }
                    i = --j;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    private static ArrayList<Integer> removeZero(ArrayList<Integer> mapList) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : mapList) {
            if (i != 0) {
                arrayList.add(i);
            }
        }
        return arrayList;
    }

    private static ArrayList<Integer> getArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        visit = new boolean[N][N];
        visit[centerN][centerN] = true;
        int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int row = centerN, col = centerN, curD = 1;
        while (row != 0 || col != 0) {
            int nr = row + d[curD][0];
            int nc = col + d[curD][1];
            row = nr;
            col = nc;

            arrayList.add(map[nr][nc]);
            map[nr][nc] = 0;
            visit[nr][nc] = true;

            int nextD = (curD + 1) % 4;
            nr = row + d[nextD][0];
            nc = col + d[nextD][1];
            if (!visit[nr][nc]) {
                curD = nextD;
            }
        }

        return arrayList;
    }

    private static void attackTower(int m) {
        for (int size = 1; size <= attackDir[m][1]; size++) {
            int nr = centerN + dir[attackDir[m][0]][0] * size;
            int nc = centerN + dir[attackDir[m][0]][1] * size;

            map[nr][nc] = 0;
        }
    }
}
