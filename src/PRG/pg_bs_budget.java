package PRG;


import java.util.Arrays;

public class pg_bs_budget {
	// 이분탐색 예산
	public static void main(String[] args) {
		int[] numbers = {120, 110, 140, 150};
    	int hand = 485;
    	System.out.println(new pg_bs_budget().solution(numbers, hand) );
	}
	
	public int solution(int[] budgets, int M)
    {
        Arrays.sort(budgets);
        int start = 0, end = budgets[budgets.length - 1];
        
        while(start <= end)
        {
            int sum = 0;
            int mid = (start + end) / 2;
            
            for(int element : budgets)
                sum = element > mid ? sum + mid : sum + element;
            
            if(sum > M) end = mid - 1;
            
            else
                start = mid + 1;
        }
        return end;
    }
	
}



