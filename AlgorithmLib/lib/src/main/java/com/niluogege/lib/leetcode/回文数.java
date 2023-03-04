package com.niluogege.lib.leetcode;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 回文数 {

    public static void main(String[] args) {
//        int x = 12121;
        int x = 10;
        System.out.println(isPalindrome(x));
    }

    /**
     * 核心思想：通过取整和取余操作 来判断前后是否相同，如果都相同那就是 回文数
     *
     * 指的注意的是；在计算有后续x 的值得的时候 为 (x%100)/10
     *
     * 参考：https://leetcode.cn/problems/palindrome-number/solution/dong-hua-hui-wen-shu-de-san-chong-jie-fa-fa-jie-ch/
     * 中的解法2
     */
    public static boolean isPalindrome(int x) {

        if (x<0){
            return false;
        }

        //先计算最大的除数，其实也就是最大有多少位

        int chushu = 1;
        while (x / chushu >= 10) {
            chushu *= 10;
        }

        while (x > 0) {
            //计算开头的值
            int first = x / chushu;
            //计算结尾的值
            int last = x % 10;

            if (first != last) {
                return false;
            }

            //x进行两边取出操作
            x = (x % chushu) / 10;
            chushu /= 100;
        }


        return true;
    }
}
