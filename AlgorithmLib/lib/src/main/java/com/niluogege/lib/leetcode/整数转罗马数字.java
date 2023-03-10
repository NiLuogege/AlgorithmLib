package com.niluogege.lib.leetcode;

/**
 * https://leetcode.cn/problems/integer-to-roman/
 */
public class 整数转罗马数字 {

    public static void main(String[] args) {

//        System.out.println(intToRoman(3));
        System.out.println(intToRoman(9));

    }

    /**
     * 这个我自己没有动脑子
     * <p>
     * 参考这个链接的 第一种解法 模拟
     * <p>
     * https://leetcode.cn/problems/integer-to-roman/solution/zheng-shu-zhuan-luo-ma-shu-zi-by-leetcod-75rs/
     */

    //这里穷举出所有 可能的表达方式 和其对应的 数值
    private static int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            int v = value[i];
            String s = symbols[i];

            while (num >= v) {
                sb.append(s);
                num -= v;
            }

            if (num <= 0) {
                break;
            }
        }

        return sb.toString();

    }
}
