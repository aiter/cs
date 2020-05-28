import java.util.*;
/**
 * 
 */
public class LeetcodeArray121 {
	public int maxProfit(int[] prices) {
		int max = 0;
		int len = prices.length;
		for(int i = 0; i < len; i++) {
			if(i < len-1) {
				
			}
		}
		return max;
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{7,1,5,3,6,4},{1,3,5,6},{1,3,5,6},{7,6,4,3,1}};
		for(int j = 0 ; j < nums.length ;j++){
			int profit = maxProfit(nums[j]);
			System.out.println(profit);
		}
	}
}
