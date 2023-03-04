package com.niluogege.lib.leetcode;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * https://leetcode.cn/problems/reverse-integer
 * <p>
 * 其实看到 '32位的有符号整数 x' 这句话就把我带偏了，因为这就让我联想到了二进制，其实这道题跟二进制没有任何关系
 * ，以后也要注意，理解题意。
 */
public class 整数反转 {

    public static void main(String[] args) {
        int x = 123;
//        int x = 1534236469;

        int r = reverse(x);
        System.out.println(r);
    }

    /**
     * 这道题有两大主要的点：
     * 1. 通过 %10  获取数字最后一位，通过 /10 将原有数字位数降低
     * 2. 对边界进行判断
     *
     * 参考：https://leetcode.cn/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/
     */
    public static int reverse(int x) {

        int res = 0;
        while (x != 0) {

            int tmp = x % 10;

            //对边界进行判断，因为是一步步的翻转所以这里 要提前判断
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return 0;
            }

            res = res * 10 + tmp;

            x = x / 10;
        }

        return res;
    }

}
