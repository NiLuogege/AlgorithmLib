package com.niluogege.lib.leetcode;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * https://leetcode.cn/problems/multiply-strings/?favorite=ex0k24j
 */
public class 字符串相乘 {

    public static void main(String[] args) {
        System.out.println(multiply("123","456"));
//        System.out.println(multiply("6913259244","71103343"));
    }


    /**
     * 参考方法2 ，做乘法
     *
     * 核心思想，想列竖式一样计算每一个的值存起来然后 ，放结果相加 每一位都放到数组里，这样就不会出现 long表达不了的情况
     * 最终字符串拼接返回
     *
     *https://leetcode.cn/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                //将对应位置的数进行相加在存储
                ansArr[i + j + 1] += x * y;
            }
        }

        //每一位的结果都放到数组里
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        //字符串拼接返回
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }


    /**
     * 这种方式必能避免 结果超出 long值 的最大值的情况
     */
    public static String multiply_me(String num1, String num2) {

        return (str2Num(num1) * str2Num(num2)) + "";
    }

    private static long str2Num(String str) {
        int len = str.length();
        int res = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);

            int x = 1;
            for (int j = 0; j < len - i -1; j++) {
                x *= 10;
            }

            res = (c - '0') * x + res;
        }

        return res;
    }
}
