import java.util.Arrays;
/**
 * 
 * https://leetcode-cn.com/problems/plus-one/
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetcodeArray66 {

	public static int[] plusOne(int[] digits) {
		int len = digits.length;
		// 加速处理最后一位非9的数组
		if(digits[len-1] != 9) {
			digits[len-1] ++ ;
			return digits;
		}
		//逻辑想清楚
		for(int i = len -1 ;i >=0;i--) {
			if(digits[i]!=9){
				digits[i]++;
				return digits;
			}
			digits[i]=0;
		}	
		int [] news = new int[len+1];
		news[0]=1;
		return news;
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{8,9,9,9},{1,3,5,6},{1,3,6},{9,9,8},{9,9,9}};
		for(int j = 0 ; j < nums.length ;j++){
			int []news = plusOne(nums[j]);
			System.out.println(Arrays.toString(news));
		}
	}
}
