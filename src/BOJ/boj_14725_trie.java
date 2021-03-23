package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_14725_trie {
	static class Node {
		String str;
		ArrayList<Node> child;
		
		public Node(String str) {
			this.str = str;
			this.child = new ArrayList<>();
		}
		
		public void print(int i) {
			child.sort((a, b) -> a.str.compareTo(b.str));
			
			if(i > 0) {				
				for(int d = 0; d < i-1; d++) out.append("--");		
				out.append(str).append('\n');
			}			
			for(Node n : child) n.print(i+1);
		}
	}
	
	static int N, M;
	static String data;
	static StringBuilder out = new StringBuilder();
	static Node root = new Node("");
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 입력 개수
		
		for (int i = 0; i < N; i++) {
			ArrayList<Node> floor = root.child; // 현재 층의 방
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 현 입력의 정보 수
			
			for (int j = 0; j < M; j++) { // 정보 입력
				data = st.nextToken();
				
				boolean contains = false;
				for (Node n : floor) {
					if(n.str.equals(data)) { // 이미 있는 정보면
						contains = true;
						floor = n.child; // 아랫층으로 이동
						break;
					}
				}
				if(!contains) { // 없는 정보면
					floor.add(new Node(data)); // 추가하고
					floor = floor.get(floor.size()-1).child; // 아랫층으로 이동
				}		
			}
		}
		root.print(0); // 출력
		System.out.println(out.toString());
	}
}
