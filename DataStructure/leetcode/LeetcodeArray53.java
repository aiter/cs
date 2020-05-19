import java.util.Arrays;
/**
 * 53. Maximum Subarray
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 *
 */
public class LeetcodeArray53 {

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
