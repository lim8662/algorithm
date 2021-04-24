package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20055_conveyorBelt {
	static class Belt { 
		int durability; // 내구도
		boolean occupied = false; // 로봇 적재 여부
		
		public Belt(int durability) {
			this.durability = durability;
		}
	}
	
	static int N, K;
	static Belt[] belt;
	
	public static void main(String[] args) throws IOException {		
		input();	
		rotate();
	}

	private static void rotate() { // 컨베이어 벨트 회전
		int step = 0; // 수행 단계
		int lastIdx = N*2-1; // 벨트 마지막 위치
		int broken = 0; // 내구도가 닳은 벨트의 수
		while(true) {
			step++;
			
			// 1. 벨트 회전
			Belt last = belt[lastIdx];
			for (int i = lastIdx - 1; i >= 0 ; i--) {
				belt[i+1] = belt[i];
			}
			belt[0] = last;
			
			if(belt[N-1].occupied) belt[N-1].occupied = false; // 하역 위치에 로봇이 있으면 내림
			
			// 2. 로봇 이동
			for (int i = N - 2; i >= 0 ; i--) { // 출하 전 위치 부터 역순으로 이동
				if(belt[i].occupied && // 로봇이 있고 
						!belt[i+1].occupied && belt[i+1].durability > 0) { // 다음 위치가 비어있으며 내구도가 0 이상이면
					belt[i].occupied = false; // 로봇 이동
					belt[i+1].occupied = true;
					belt[i+1].durability--;
					if(belt[i+1].durability == 0) broken++; // 내구도가 0이 되었다면 개수 증가
					
				}
			}
			
			if(belt[N-1].occupied) belt[N-1].occupied = false; // 하역 위치에 로봇이 있으면 내림
			
			// 3. 로봇 적재
			if(!belt[0].occupied && belt[0].durability > 0) {
				belt[0].occupied = true;
				belt[0].durability--;
				if(belt[0].durability == 0) broken++; // 내구도가 0이 되었다면 개수 증가
				
			}
			// 4. 종료 
			if(broken >= K) break;		
		}
		System.out.println(step);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new Belt[N * 2];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N * 2; i++) {
			belt[i] = new Belt(Integer.parseInt(st.nextToken()));
		}
	}
}
