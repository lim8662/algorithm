package SWEA;

import java.util.*;
import java.io.*;

public class D2_1208 {
	//Flatten
    public static void main(String args[]) throws Exception {
    	System.setIn(new FileInputStream("src/SWEA/1208.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int tc=1; tc<=10; tc++) {
        	int dump = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h[] = new int[100];
            
            for(int i=0; i<100; i++) {
            	h[i] = Integer.parseInt(st.nextToken()); 
            }
            //dump
            Arrays.sort(h);
            for(int i=0; i<dump; i++) {
            	h[0]++; h[99]--;
                Arrays.sort(h);
    			if(h[99] - h[0] <= 1) return;
            }
            
            System.out.printf("#%d %d\n", tc, h[99]-h[0]); 
        }    
    }
}