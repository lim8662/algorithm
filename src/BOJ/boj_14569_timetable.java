package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14569_timetable {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine()); // 과목 수
		long[] subject = new long[N]; // 과목 시간표
		StringTokenizer st;
		StringBuilder out = new StringBuilder();
		int T = 0;
		long sjt = 0;
		
		for ( int i = 0; i < N; i++) { // 과목 시간표 입력
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			sjt = 0;
			for (int j = 0; j < T; j++) {
				sjt |= 1L << Integer.parseInt(st.nextToken()); // long형 범위까지 shift가 됨
			}
			subject[i] = sjt;
		}
		
		int M = Integer.parseInt(br.readLine()); // 학생 수
		int B = 0, cnt = 0;
		long blank = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			B = Integer.parseInt(st.nextToken());
			blank = 0; cnt = 0;
			
			for (int j = 0; j < B; j++) {
				blank |= 1L << Integer.parseInt(st.nextToken());
				
			}
			
			for (int n = 0; n < N; n++) { // 모든 과목에 대해 수강 가능한지 확인
				if((blank | subject[n]) == blank) {
					cnt++;
				}
			}
			out.append(cnt).append('\n');
		}
		System.out.println(out.toString());
	}
}
