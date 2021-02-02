package SWEA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class D2_1954 {
    //달팽이 숫자
    public static void main(String[] args) throws FileNotFoundException {
    	System.setIn(new FileInputStream("src/SWEA/1954.txt"));
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        int arr[][];
       
        for (int tc = 1; tc <= testcase; tc++) {
            int size = sc.nextInt();
            arr = new int[size][size];
            
            int result = 1;
            int r = 0;
            int c = -1;
            int d = 1;
            int move = size;
    
            while (true) {
                if(result>size*size) {
                    break;
                }
                //열 이동
                for (int n = 0; n < move; n++) {
                    c += d;
                    arr[r][c] = result++;     
                }
                //행 이동은 거리 1 감소
                move--;
                
                //행 이동
                for (int n = 0; n < move; n++) {
                    r += d;
                    arr[r][c] = result++;
                }
                
                //열 이동은 이동 방향 변경
                d *= -1 ;
            }
        
            System.out.println("#" + tc);
            for (int p = 0; p < size; p++) {
                for (int q = 0; q < size; q++) {
                    System.out.print(arr[p][q]+" ");
                }
                System.out.println();
            }
        }
    }
}
