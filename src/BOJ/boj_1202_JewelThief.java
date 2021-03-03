package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1202_JewelThief {
	
	public static class Jewel {
		private int weight; // 보석 무게
		private int value; // 보석 가격
		
		public Jewel(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public int getWeight() {
			return weight;
		}

		public int getValue() {
			return value;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 보석 수
		int K = Integer.parseInt(st.nextToken()); // 가방 수
		int[] bags = new int[K]; // 가방 무게 한도
		// 보석을 저장하는 무게 Min Heap
		PriorityQueue<Jewel> jewels = new PriorityQueue<>((a,b) -> a.getWeight() - b.getWeight());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 보석 무게
			int v = Integer.parseInt(st.nextToken()); // 보석 가격
			jewels.add(new Jewel(w, v));
		}
		
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine()); 
		}
		Arrays.sort(bags); // 가방을 무게 한도로 오름차순 정렬
		
		// 무게 한도 내의 보석들의 가격을 담는 Max Heap 
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
		long total = 0; // 훔친 보석 가치의 합
		int limit = 0; // 가방 무게 한도 
		
		for (int i = 0; i < K; i++) {
			limit = bags[i];
			while(!jewels.isEmpty() && jewels.peek().getWeight() <= limit) { // 무게 한도 내의 보석이면
				pq.add(jewels.poll().getValue()); // 그 가격을 Max Heap에 넣는다
				
				if(jewels.isEmpty()) break;
			}
			
			if(!pq.isEmpty()) { // 가방 무게 이하의 보석이 있다면
				total += pq.poll(); // 그중 가장 비싼 보석을 담는다.
			}
		}
		System.out.println(total);
	}
}
