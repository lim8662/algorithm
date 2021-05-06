package BOJ;

import java.io.*;
import java.util.*;

public class boj_18870_locationCompression {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine());
		int[] loc = new int[N];
		int[] tmp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			loc[i] = Integer.parseInt(st.nextToken());
			tmp[i] = loc[i];
		}
		Arrays.sort(tmp);
		
		Map<Integer, Integer> map = new HashMap<>();
		
		int cnt = 0;
		int pre = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if(pre < tmp[i]) {
				map.put(tmp[i], cnt++);
				pre = tmp[i];
			}
		}
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < N; i++) {
			out.append(map.get(loc[i])).append(" ");
		}
		System.out.println(out.toString());		
	}
	
	// 이진탐색 
	static int N;
	static int sorted[];
	
	static int upperBound(int key) {
	    int left = 0;
	    int right = N-1;
	    int res = N; // default 값 중요
	    while (left <= right) {
	        int mid = (left + right) / 2;
	        if (key < sorted[mid]) {
	            right = mid - 1;
	            res = mid;
	        } else {
	            left = mid + 1;
	        }
	    }
	    return res;
	}
	static int lowerBound(int key) {
	    int left = 0;
	    int right = N-1;
	    int res = N; // default 값 중요
	    while (left <= right) {
	        int mid = (left + right) / 2;
	        if (key <= sorted[mid]) {
	            right = mid - 1;
	            res = mid;
	        } else {
	            left = mid + 1;
	        }
	    }
	    return res;
	}
}
