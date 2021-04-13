package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_4014_활주로건설_임영찬 {

	static int[][] map; 
	static int N, X, ans;
	
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	int T = Integer.parseInt(br.readLine());
        
    	for(int tc=1; tc<=T; tc++) {
    		st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken()); // 맵크기
        	X = Integer.parseInt(st.nextToken()); // 맵크기
            map = new int[N][N];
            ans = 0;
                
            for (int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
            	for (int j = 0; j < N; j++) {
            		map[i][j] = Integer.parseInt(st.nextToken());
            	}
			}
            
            for (int r = 0; r < N; r++) { // 수평 활주로 건설
                int flat = 1, ramp = 0, h = map[r][0];
                boolean isRamp = false;
                boolean valid = true;
                
                for (int c = 1; c < N; c++) {
                	//System.out.printf("col=%d row=%d h=%d, flat=%d valid=%s\n", c, r, h, flat, valid );
					if(map[r][c] == h) { // 같은 높이면
						if(isRamp) { // 경사 구간이고
							ramp++;
							if(ramp == X) { // 경사로 설치 가능하면
								ramp = 0; isRamp = false; valid = true;
							}
						} else flat++;
					}
					else if(map[r][c] == h + 1) { // 높이가 1 증가하면
						if(flat < X)  {
							valid = false; break; 
						}
						h++; flat = 1;
					} else if(!isRamp && map[r][c] == h - 1) { // 높이가 1 감소하면
						h--; isRamp = true; ramp = 1; flat = 0; valid = false;
					} else {	
						valid = false; break;
					}
				}               
                if(valid) {
                	//System.out.println(r);
                	ans++;   
                }
			}
            
            for (int c = 0; c < N; c++) { // 수직 활주로 건설
                int flat = 1, ramp = 0, h = map[0][c];
                boolean isRamp = false;
                boolean valid = true;
                
                for (int r = 1; r < N; r++) { 
                	//System.out.printf("col=%d row=%d h=%d, flat=%d valid=%s\n", c, r, h, flat, valid );
					if(map[r][c] == h) { // 같은 높이면
						if(isRamp) { // 경사 구간이고
							ramp++;
							if(ramp == X) { // 경사로 설치 가능하면
								ramp = 0; isRamp = false; valid = true;
							}
						} else flat++;
					}
					else if(map[r][c] == h + 1) { // 높이가 1 증가하면
						if(flat < X)  {
							valid = false; break; 
						}
						h++; flat = 1;
					} else if(!isRamp && map[r][c] == h - 1) { // 높이가 1 감소하면
						h--; isRamp = true; ramp = 1; flat = 0; valid = false;
					} else {	
						valid = false; break;
					}
				}             
                if(valid) {
                	//System.out.println(c);
                	ans++;   
                }        
			}          
            System.out.println("#" + tc + " " + ans);
        }
    }
}