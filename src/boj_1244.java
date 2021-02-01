
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1244 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchCnt = Integer.parseInt(br.readLine()); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean[] state = new boolean[switchCnt + 1];
		// 스위치 상태 boolean으로 변환
		for(int i = 1; i <= switchCnt; i++) {
			state[i] = (st.nextToken().equals("1")) ? true : false;
		}
		int studentCnt = Integer.parseInt(br.readLine());
		int currentIdx = 0;
		for(int i = 0; i < studentCnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			if(st.nextToken().equals("1")) { // 남학생
				currentIdx = Integer.parseInt(st.nextToken());
				// 현 스위치의 배수가 되는 번호의 NOT연산
				for(int j = currentIdx; j <= switchCnt; j += currentIdx) {
					state[j] = !state[j];
				}
			}
			else { // 여학생은 현 스위치 기준 최대로 좌우 대칭된 상태의 스위치 까지 NOT연산
				currentIdx = Integer.parseInt(st.nextToken());
				
				// 최대 대칭 범위 구하기
				int leftIdx = currentIdx - 1;
				int rightIdx = currentIdx + 1;
				while(true) {
					if(leftIdx < 1 || rightIdx > switchCnt) break;
					if(state[leftIdx] != state[rightIdx]) break;
					leftIdx--; rightIdx++;
				}
				// 범위 스위치 전환
				for(int j = leftIdx + 1; j < rightIdx; j++)
					state[j] = !state[j];			
			}		
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= switchCnt; i++) {
			sb.append( (state[i]) ? "1" : "0" ).append(" ");
			if(i % 20 == 0) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
