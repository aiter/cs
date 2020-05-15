/**
 * 26. Remove Duplicates from Sorted Array
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray26 {

	public static int removeDuplicates(int[] nums) {
		int idx = 0;
		int len = 0;
		int end = nums.length-1;
		while(idx<=end) {
			// 找到不同的值
			while(idx < end && nums[idx]==nums[idx+1]) {
				idx++;
			}
			//将不同的值，依次放一个。只有唯一值了，才移动数据
			nums[len++] = nums[idx++];
		}

		return len;
    }

	public static void main(String [] args) {
		int[][] nums = new int[][]{{0,1,2,2,3,4,5},{1,2,2,3},{4,5},{2},{2,2,2,2,3}};
		for(int j = 0 ; j < nums.length ;j++){
			int len = removeDuplicates(nums[j]);
			System.out.println("len="+len);
			for(int i = 0; i< len;i++) {
				System.out.println(i+":"+nums[j][i]);
			}
		}
	}
}
