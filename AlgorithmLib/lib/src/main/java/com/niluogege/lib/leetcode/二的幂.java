package com.niluogege.lib.leetcode;

public class 二的幂 {

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(4));
        System.out.println(isPowerOfTwo(12));
    }

    /**
     * 核心思想(方法一)
     * - 首先约定2的幂 指的是 2的²  这种二次方，三次方 也就是 2,4,8 ，所以要和2 的倍数区分开
     * - 如果是2的幂（n）的话二进制的话他有一个特点就是 最高位是1 剩下全是0 ， 那么如果是 2的幂次方 -1 (n-1)的话，就是次高位以后全是1
     * 那么 n&n-1 ==0
     * <p>
     * https://leetcode.cn/problems/power-of-two/solution/2de-mi-by-leetcode-solution-rny3/
     */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & n - 1) == 0);
    }

}
