package BOJ;

import java.io.*;
import java.util.*;

public class boj_12015_LISubsequence {

	// NlogN 해결방법 = 2천만정도
	static int[] lcs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		lcs = new int[N]; // LCS 최대 길이는 백만
		// index 0부터 사용
		// LCS 길이는 index
		StringTokenizer st = new StringTokenizer(br.readLine());
		int temp, index = 0;
		lcs[0] = Integer.parseInt(st.nextToken()); // 초기값 세팅
		for (int i = 1; i < N; i++) {
			temp = Integer.parseInt(st.nextToken());
			if (temp > lcs[index])
				lcs[++index] = temp;
			else
				lcs[bins(index, temp)] = temp;
		}
		System.out.println(index + 1);
	}

	static int bins(int hi, int tar) { // tar 찾으면 index 반환 or 못 찾으면 lo반환
		// lcs[]는 순증가 수열
		int lo = 0, mid;
		while (lo <= hi) {
			mid = (lo + hi) / 2;
			if (tar == lcs[mid])
				return mid;
			if (tar > lcs[mid])
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

}
