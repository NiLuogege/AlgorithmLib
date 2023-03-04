package com.niluogege.lib.leetcode;

/**
 * https://leetcode.cn/problems/string-to-integer-atoi/?favorite=ex0k24j
 */
public class 字符串转换整数 {

    public static void main(String[] args) {

        String s = "101";

        System.out.println(myAtoi(s));

        String s1 = "-5202";
        System.out.println(myAtoi(s1));


    }

    /**
     * 我这个只是实现了核心方法,就是 区分正负数，然后将 String 转为 int ，
     * 主要的核心点有两个就是
     * - 通过 char[i]='0' 计算出 数字的具体是，因为阿斯科码表是有序的
     * - 通过 sign 来记录正负数 参与到计算中
     */
    public static int myAtoi(String s) {

        //用来表示正负数
        int sign = 1;
        if (s.startsWith("-")) {
            s = s.replace("-", "");
            sign = -1;
        }


        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res = res * 10 + (c - '0') * sign;
        }

        return res;

    }

}
