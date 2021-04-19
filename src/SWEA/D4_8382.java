package SWEA;

import java.util.Scanner;

public class D4_8382 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int x1,y1,x2,y2,x,y,t;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
			x = Math.abs(x1-x2);
            y = Math.abs(y1-y2);
            t = (x + y) / 2;
            System.out.printf("#%d %d\n",test_case, 2 * t + Math.abs(x - t) + Math.abs(y - t));		  
		}
	}

}
