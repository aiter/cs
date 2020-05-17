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
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray53 {

	public static int maxSubArray(int[] nums) {

    }

	public static void main(String [] args) {
		int[][] nums = new int[][]{{1,3,5,6},{1,3,5,6},{1,3,5,6},{1,3,5,6}};
		for(int j = 0 ; j < nums.length ;j++){
			int len = maxSubArray(nums[j]);
			System.out.println("len="+len);
			System.out.println(Arrays.toString(nums[j]));
		}
	}
}
