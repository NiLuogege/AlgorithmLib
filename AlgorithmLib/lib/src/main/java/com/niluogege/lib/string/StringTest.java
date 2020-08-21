package com.niluogege.lib.string;


import com.niluogege.lib.utils.StringUtils;

public class StringTest {

    public static void main(String[] args) {
//        String space = replaceSpace("We Are Happy");
//        System.out.println("space= " + space);

        boolean match = isMatch("aa", "a*");
        System.out.println("match= " + match);
    }


    /**
     * 题目：将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * <p>
     * 思路：
     * 1. 直接使用 java 提供的 replaceAll api进行替换
     * 2. 自己编写函数替换
     * <p>
     * 我们选择自己编写函数，以增强我们对 替换字符串原理的理解
     */
    private static String replaceSpace(String str) {
        if (str != null && str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == ' ') {
                    sb.append("%20");
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }


    /**
     * 题目：给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * * : 可以有0个，1个或是多个
     * . : 任意一个
     *
     * <p>
     * 参考：
     * https://leetcode-cn.com/problems/regular-expression-matching/
     */
    private static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }

                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }

        }
        return f[m][n];


    }

    private static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }

        if (p.charAt(j - 1) == '.') {
            return true;
        }

        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
