package com.niluogege.lib.string;

public class StringTest {

    public static void main(String[] args) {
        String space = replaceSpace("We Are Happy");
        System.out.println("space= "+space);
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

}
