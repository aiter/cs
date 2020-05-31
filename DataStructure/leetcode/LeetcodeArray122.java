import java.util.*;
/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray122 {
	public static int maxProfit(int[] prices) {
		int len = prices.length;
		if(len <=1){
			return 0;
		}
		int profit = 0;
		int max = 0;
		int min = prices[0];
		for(int i = 1; i < len; i++) {
			//获取单次的最大收益
			if(prices[i] > prices[i-1]) {
				max = prices[i]-min;
			}else {
				//重新低点买入
				min = prices[i]; 
				profit += max;
				max = 0;
			}
		}
		if(max > 0) {
		// 最后一次的收益累计	
			profit += max;
		}
		return profit;
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{7,1,5,3,6,4},{7,1,5,3,6},{1,3,5,6},{7,6,4,3,1}};
		for(int j = 0 ; j < nums.length ;j++){
			int profit = maxProfit(nums[j]);
			System.out.println(Arrays.toString(nums[j])+"->"+profit);
		}
	}
}
