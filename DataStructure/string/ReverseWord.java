public class ReverseWord{
    public static void main(String[] args){
        String str="hello    World java14";
        char[] chars=str.toCharArray();
        int left=0;
        int right=chars.length-1;
		//O(n)  n/2
        swapDic(chars,left,right);
        left=0;
        right=0;
		//O(n) 
        for(int i=0;i<chars.length;i++){
            if(chars[i]==' '){
                swapDic(chars,left,right-1);
                left=right+1;
            }else if(i==chars.length-1){
                swapDic(chars,left,right);
            }
            right++;
        }

        System.out.println(chars);
    }

    /*翻转字符串*/
    public static void swapDic(char[] chars,int left,int right){
        while(left<right){
            swap(chars,left,right);
            left++;
            right--;
        }
    }

    /*字母对调*/
    public static void swap(char[] chars,int n,int m){
        if(n==m){
            return;
        }
        char temp=chars[n];
        chars[n]=chars[m];
        chars[m]=temp;
    }
}
