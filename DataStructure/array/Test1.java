/**
 *
 * 1. 输入："hello  world  java11"，输出 "java11 world hello"
 * 要求：空间复杂度O(1),时间复杂度O(n)
 *
 */
public class Test1 {
    public static void main(String[] args) {

        String test = "hello world java11 isokkkkkkkk";
        test.substring(2);
        String tmp = test1(test);
        //test1("hello world java11");

        char[] chars = test.toCharArray();
        test2(chars);
    }

    private static char[] test2(char[] chars){
        int s = 0;
        int e = 0;
        int len = chars.length;

        for (int i = 0; i < len; i++) {
            if(chars[i]==' '){
                e = i;

                reverse(chars, s, e-1);
                s = i+1;
            }
        }
        reverse(chars, s, len-1);

        System.out.println(chars);

        reverse(chars,0,len-1);
        System.out.println(chars);

        return chars;

    }

    private static void reverse(char[] chars, int s, int e) {
        int count = (e+1-s)/2;
        //System.out.print(s);
        //System.out.print(" ");
        //System.out.print(e);
        //System.out.print(" ");
        //System.out.print(count);
        //System.out.println();
        int i = 0;
        while (i< count){
            char tmp = chars[s+i];
            chars[s+i] = chars[e-i];
            chars[e-i] = tmp;
            i++;
        }
    }

    /**
     * 不能用substring，整个string复制 new String(value, beginIndex, subLen);
     * @param test
     * @return
     */
    private static String test1(String test) {
        //时间O(n)
        if (!test.contains(" ")){
            System.out.print(test);
        }
        int len = test.length();

        //时间O(n),空间O(1)
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (int i = len - 1; i >= 0; i--) {

            if(test.charAt(i)==' '){
                while (stack.size()>0){
                    System.out.print(stack.pop());
                }
                System.out.print(' ');
            }else {
                stack.push(test.charAt(i));
            }
        }
        while (stack.size()>0){
            System.out.print(stack.pop());
        }
        System.out.println();
        return null;
    }
}
