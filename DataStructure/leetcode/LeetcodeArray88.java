import java.util.Arrays;
/**
 * 
 */
public class LeetcodeArray88 {
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		//数组nums的复制
		int [] news = new int[m];
		for(int i = 0;i < m ;i++) {
			news[i] = nums1[i];
		}
		//双指针
		int idxA = 0;
		int idxB = 0;
		int idx = 0;
		while(idx < m+n) {
			if(idxA >= m) {
				nums1[idx] = nums2[idxB];
				idxB++;
				idx ++;
				continue;
			}
			if(idxB >= n) {
				nums1[idx] = news[idxA];
				idxA ++;
				idx ++;
				continue;
			}

			if(news[idxA] <= nums2[idxB]) {
				nums1[idx] = news[idxA];
				idxA ++;
			}else {
				nums1[idx] = nums2[idxB];
				idxB++;
			}
			idx ++;
		}
	}

	public static void main(String [] args) {
		int[][] nums = new int[][]{{8,9,9,0,0,0,0},{1,3,6},{1,3,6,0,0,0},{5,6,8}};
		for(int j = 0 ; j < nums.length ;j++){
			merge(nums[j],3,nums[++j],3);
			System.out.println(Arrays.toString(nums[j-1]));
		}
	}
}
