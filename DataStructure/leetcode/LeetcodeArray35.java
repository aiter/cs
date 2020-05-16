import java.util.Arrays;
/**
 * 35. Search Insert Position
 *
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray35 {

	/**
	 * 1,2,3,4,5: mid=2; 1,2,3,4,5,6: mid = 2;
	 */
	public static int searchInsert(int[] nums, int target) {
		if(target<nums[0]) {
			return 0;
		}
		int len = nums.length;
		if(target > nums[len-1]) {
			return len;
		}
		int left = 0;
		int right = nums.length - 1;
		while(left < right) {
			int mid = left + (right-left)/2;
			if(nums[mid]==target) {
				return mid;
			}else if(target < nums[mid]) {
				if(mid-1>=0 && target >nums[mid-1]){
					return mid;
				}
				right = mid-1;
			}else if(target > nums[mid]) {
				if(mid+1 <= len-1 && target < nums[mid+1]){
					return mid+1;
				}
				left = mid+1;
			}
		}
		return right;
    }

	public static void main(String [] args) {
		int[][] nums = new int[][]{{1,3,5,6},{1,3,5,6},{1,3,5,6},{1,3,5,6}};
		int[] ns = new int[]{5,2,7,0};
		for(int j = 0 ; j < ns.length ;j++){
			int len = searchInsert(nums[j],ns[j]);
			System.out.println("len="+len);
			System.out.println(ns[j]+":"+Arrays.toString(nums[j]));
		}
	}
}
