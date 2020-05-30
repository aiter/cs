import java.util.*;
/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 
 */
public class LeetcodeArray121 {
	public static int maxProfit(int[] prices) {
		int len = prices.length;
		if(len <=1){
			return 0;
		}
		int max = 0;
		int min = prices[0];
		//维护最小的值
		//维护最大的差异（当前的值减去min和max比较，存更大的一个值）
		for(int i = 1; i < len; i++) {
			if(prices[i] > prices[i-1]) {
				max = Math.max(max,prices[i]-min);
			}
			min = Math.min(min,prices[i]);
		}
		return max;
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{7,1,5,3,6,4},{1,3,5,6},{1,3,5,6},{7,6,4,3,1}};
		for(int j = 0 ; j < nums.length ;j++){
			int profit = maxProfit(nums[j]);
			System.out.println(Arrays.toString(nums[j])+"->"+profit);
		}
	}
}
