package com.niluogege.lib.leetcode;

/**
 * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
 *
 * 这个太难了 先放弃
 */
public class 正则表达式匹配 {

    public static void main(String[] args) {
//        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","a*"));
    }

    /**
     * 这个太难了 先放弃
     *
     * 使用的核心思想是动态规划
     *
     * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-s3jgn/
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //拿到前面的字符串，如果是* 说明 *后面的字符串可以出现多次
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

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
