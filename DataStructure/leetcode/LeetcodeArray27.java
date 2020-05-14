/**
 * Remove Element
 *
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray27 {

	public static int removeElement(int[] nums, int val) {
		int len = 0;
		int begin = 0;
        int end = nums.length-1;
        if(end == 0) {
            if(nums[end]==val) {
                return 0;
            }else {
                return 1;
            }
        }
        while(begin <= end){
            if(nums[begin] == val) {
                while(end > 0 && nums[end]==val){
                    end--;
                }
                if(begin < end) {
                    nums[begin] = nums[end--];
					len ++;
                }
            }else {
				len++;
			}
            begin++;
        }

        return len;
    }

	public static void main(String [] args) {
		int[][] nums = new int[][]{{0,1,2,2,3,0,2,4,2},{3,2,2,3},{4,5},{2},{2,2,2,2}};
		int[] ns = new int[]{2,3,4,2,2};
		for(int j = 0 ; j < ns.length ;j++){
			int len = removeElement(nums[j],ns[j]);
			System.out.println("len="+len);
			for(int i = 0; i< len;i++) {
				System.out.println(i+":"+nums[j][i]);
			}
		}
	}
}
