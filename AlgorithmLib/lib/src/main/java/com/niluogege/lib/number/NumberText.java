package com.niluogege.lib.number;

public class NumberText {
    public static void main(String[] args) {

        System.out.println(hammingWeight(-9));

    }


    /**
     * 题目：输入一个 32 位整数，输出该数二进制表示中1的个数。
     * <p>
     * 参考：https://liweiwei1419.github.io/sword-for-offer/15-%E4%BA%8C%E8%BF%9B%E5%88%B6%E4%B8%AD1%E7%9A%84%E4%B8%AA%E6%95%B0/
     * <p>
     * 分析：数分为正数和负数，正数的最高位是0，负数的最高位是1，为了避免 负数时 二进制数右移后 高位补的是1 导致死循环
     * 所以这里要使用 无符号右移
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }
}
