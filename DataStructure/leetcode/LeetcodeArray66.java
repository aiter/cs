import java.util.Arrays;
/**
 * 
 * https://leetcode-cn.com/problems/plus-one/
 *
 */
public class LeetcodeArray66 {

	public static int maxSubArray(int[] nums) {
		if(nums.length<=0) {
			return 0;
		}
		//dp[i]：以数组nums[i]为结尾的最大数组和。
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int res = dp[0];
		for(int i=1;i<nums.length;i++){
			dp[i] = max(nums[i],dp[i-1]+nums[i]);
			res = max(res,dp[i]);
		}
		return res;	
    }

	public int[] plusOne(int[] digits) {

	}

	public static int max(int a, int b) {
		return (a >= b) ? a : b;
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{1,-3,5,6},{1,3,-5,6},{-1,3,5,6},{1,3,5,-6}};
		for(int j = 0 ; j < nums.length ;j++){
			int len = maxSubArray(nums[j]);
			System.out.println("len="+len);
			System.out.println(Arrays.toString(nums[j]));
		}
	}
}
