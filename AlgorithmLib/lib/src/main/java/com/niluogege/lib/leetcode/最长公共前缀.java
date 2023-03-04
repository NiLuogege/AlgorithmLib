
package com.niluogege.lib.leetcode;

import java.util.ArrayList;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * https://leetcode.cn/problems/longest-common-prefix/?favorite=ex0k24j
 */
public class 最长公共前缀 {

    public static void main(String[] args) {
//        String[] strs = new String[]{"a"};
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    /**
     * 这是我自己的解法 ，参考答案的解法和我的差不多，这里就没写
     */
    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }

        String res = "";
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            String pre = first.substring(0, i+1);

            int trueCount=0;

            for (int j = 0; j < strs.length; j++) {
                String str = strs[ j ];
                if (str.startsWith(pre)) {
                    trueCount++;
                }
            }
            if (strs.length == trueCount) {
                res = pre;
            }
        }
        return res;
    }

}
