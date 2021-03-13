package SWEA;

import java.util.Scanner;

public class D2_1204 {

	
    
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	int T = sc.nextInt();
        
        for(int i = 1; i <= T; i++) {
        	if( i == sc.nextInt() ) {
            	int cnt[] = new int[101];
            
            	for(int j = 1; j <= 1000; j++) {
            		int score = sc.nextInt();
            		cnt[score]++;	
                }
            	
            	int max = cnt[0];
            	int mode = 0;
            	for(int n = 1; n < cnt.length; n++ ) {
            		if(max <= cnt[n]) {
            			max = cnt[n];
            			mode = n;
            		}
            	}
            	
            	System.out.format("#%d %d\n", i, mode);
            }
        }
    }
}
