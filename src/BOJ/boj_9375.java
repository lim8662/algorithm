package BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_9375 {
	// 패션왕 신해빈
	// T <= 100, 의상 수 n : 1~30
	// 의상 착용 수 1 ~ n
	// 의상의 조합이 중복되지 않는 경우의 수 리턴

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> map;
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String type = st.nextToken();

				if(map.containsKey(type)) {
					int cnt = map.get(type);
					map.put(type, cnt + 1);
				} else {
					map.put(type, 1);
				}
			}
			int count = 1;
			for(String type : map.keySet()) {
				count *= map.get(type) + 1; // 착용하지 않는 경우의 수도 포함
			}
			
			System.out.println(count - 1); // 알몸인 경우 제외
		}
		
	}

}
