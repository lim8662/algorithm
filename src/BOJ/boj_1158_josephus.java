package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1158_josephus {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder out = new StringBuilder("<");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		for ( int i = 1; i <= N; i++) {
			q.add(i);
		}
		// 요세푸스 순열 만들기
		int turn = 1;
		while(!q.isEmpty()) {
			if(q.size() == 1) { // 혼자 남았다면 순열 완성 후 종료
				out.append(q.poll() + ">"); break;
			}
			else if(turn == K) { // 차례가 되면  
				out.append(q.poll() + ", "); // 제거 후 순열에 추가
				turn = 1;
			}
			else { // 아직 차례가 아니면
				q.add(q.poll()); // 큐의 뒷순서로 재배치
				turn++;
			}
		}
		System.out.println(out.toString());
	}
}
