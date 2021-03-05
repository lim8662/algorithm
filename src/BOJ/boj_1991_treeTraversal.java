package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1991_treeTraversal {
	
	public static class Node {
		int left;
		int right;
		
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	static Node[] tree = new Node[26];
	static int N, c, l, r;
	static StringBuilder out = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			c = st.nextToken().charAt(0) - 'A';
			l = st.nextToken().charAt(0) - 'A';
			r = st.nextToken().charAt(0) - 'A';
			Node node = new Node(l, r);
			tree[c] = node;
		}
		
		preorder(0);
		out.append('\n');
		inorder(0);
		out.append('\n');
		postorder(0);
		
		System.out.println(out.toString());
	}
	
	public static void preorder(int cur) { // 전위 순회
		out.append((char)('A' + cur)); // 현 노드 방문
		Node curNode = tree[cur];
		if(curNode.left > 0) preorder(curNode.left); // 왼쪽 노드 방문
		if(curNode.right > 0) preorder(curNode.right); // 오른쪽 노드 방문
	}
	
	public static void inorder(int cur) { // 중위 순회
		Node curNode = tree[cur];
		if(curNode.left > 0) inorder(curNode.left); // 왼쪽 노드 방문
		out.append((char)('A' + cur)); // 현 노드 방문
		if(curNode.right > 0) inorder(curNode.right); // 오른쪽 노드 방문
	}

	public static void postorder(int cur) { // 후위 순회
		Node curNode = tree[cur];
		if(curNode.left > 0) postorder(curNode.left); // 왼쪽 노드 방문
		if(curNode.right > 0) postorder(curNode.right); // 오른쪽 노드 방문
		out.append((char)('A' + cur)); // 현 노드 방문
	}
}
