import java.util.*;
/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray167 {
	public static int[] twoSum(int[] numbers, int target) {
		int len  = numbers.length;
		if(len==0){
			return new int[2];
		}
		int [] idx = new int[2];
		
		return idx;
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{1,3,5,6},{1,3,5,6},{1,3,5,6},{1,3,5,6}};
		int[] ns = new int[]{6,4,7,8};
		for(int j = 0 ; j < ns.length ;j++){
			int []idxs = twoSum(nums[j],ns[j]);
			System.out.println(ns[j]+":"+Arrays.toString(nums[j])+":"+Arrays.toString(idxs));
		}
	}
}
