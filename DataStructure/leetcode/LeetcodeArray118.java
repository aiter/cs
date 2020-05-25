import java.util.*;
/**
 * 
 */
public class LeetcodeArray118 {
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		System.out.println(numRows);
		for(int i = 1; i <= numRows; i++) {
			List<Integer> tmpList = new ArrayList<Integer>();
			List<Integer> preList = null;
			if(i>1) {
				preList = list.get(i-2);
			}	
			for(int j = 1; j <= i; j++) {
				if(i<3){
					tmpList.add(1);
				}else {
					if(preList != null) {
						if(j==1 || j==i) {
							tmpList.add(1);
						}else {
							tmpList.add(preList.get(j-2)+preList.get(j-1));
						}
					}else {
						tmpList.add(1);
					}
				}
			}
			list.add(tmpList);
		}
		return list;
	}	

	public static void main(String [] args) {
		int[] nums = new int[]{1,3,6};
		for(int j = 0 ; j < nums.length ;j++){
			List<List<Integer>> list = generate(nums[j]);
			for(List<Integer> item : list) {
				System.out.println(item);
			}
		}
	}
}
