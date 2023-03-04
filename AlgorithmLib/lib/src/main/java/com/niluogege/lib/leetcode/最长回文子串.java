package com.niluogege.lib.leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * <p>
 * https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class 最长回文子串 {

    public static void main(String[] args) {
        String s = "ababad";

//        String r = longestPalindrome_me(s);
        String r = longestPalindrome(s);
        System.out.println(r);
    }

    /**
     * 具体解法：https://leetcode.cn/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/
     *
     * 核心思想：
     * 1 长的回文字符串是由短的回文字符串组成的，如果中间是回文字符串，两边也相对，那么长的肯定也是回文字符串
     * 2 用dp将 短的回文字符串记录下来，方便后面长的回文字符串的计算
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        //上面判断了 s 长度等于1 的情况，所以这里r 从1 开始
        //第一次for循环 r 标识 左侧元素 ，第二次for循环 l 标识 右侧元素
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                //  s.charAt(l) == s.charAt(r) && r - l <= 2 是为了寻找最早的最短的那个 回文串 ，也就是确定 初始状态
                // s.charAt(l) == s.charAt(r) &&  dp[l + 1][r - 1]) 的意思是 如果中间的已经是回文字符串了，那么两边相等肯定也是回文字符串
//                System.out.println("外层  l=" + l + " l元素=" + s.charAt(l) + " r=" + r + " r元素=" + s.charAt(r) + " 子串=" + s.substring(l, r + 1));
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
//                    System.out.println("l=" + l + " l元素=" + s.charAt(l) + " r=" + r + " r元素=" + s.charAt(r) + " 子串=" + s.substring(l, r + 1));
                    //用于记录是回文字符串的位置，方便计算更长的回文字符串
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }

            }

        }
        return s.substring(maxStart, maxEnd + 1);

    }

    /**
     * 我的解法就是 双层遍历 拿到字段 然后翻转 进行对比，记录最大长度的起始位置和结束位置，
     * <p>
     * 这个虽然可以解决问题，但是在提交LeetCode的时候 说时间超出限制，所以说效率不高
     */
    public static String longestPalindrome_me(String s) {

        if (s.length() == 1) {
            return s;
        }

        int first = 0, last = 0, maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                //substring 是包左不包右，所以上面的if要为 j <= s.length()
                String a = s.substring(i, j);
                StringBuilder sb = new StringBuilder(a);
                String b = sb.reverse().toString();
                if (a.equals(b) && maxLength < (j - i)) {
                    first = i;
                    last = j;
                    maxLength = j - i;
                }
            }
        }
        return s.substring(first, last);


    }

}
