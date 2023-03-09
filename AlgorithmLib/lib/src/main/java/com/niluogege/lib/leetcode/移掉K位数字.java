package com.niluogege.lib.leetcode;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/remove-k-digits/
 */
public class 移掉K位数字 {

    public static void main(String[] args) {
//        System.out.println(removeKdigits("1432219", 3));
//        System.out.println(removeKdigits("10200", 1));
//        System.out.println(removeKdigits("10", 2));
//        System.out.println(removeKdigits("9", 1));
//        System.out.println(removeKdigits("112", 1));
        System.out.println(removeKdigits_me("10001", 4));
    }

    /**
     * 核心思想：
     * 从高位到低位 ，如果高位的数字大于和它相邻的数字则要干掉， 这样剩下来的就是最小的
     * <p>
     * <p>
     * https://leetcode.cn/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
     */
    public static String removeKdigits(String num, int k) {
        return "";
    }

    /**
     * 我的写法，我的写法能走过大部分用例，但是在 "10001", 4 的时候有问题了 正确答案为0 ，但是我的输出1
     */
    public static String removeKdigits_me(String num, int k) {
        if (num == null || num.length() == 0) {
            return "0";
        }

        if (num.length() <= k) return "0";

        LinkedList<Character> queue = new LinkedList<>();

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            //按顺序取出一个 ,然后判断当前的和队列中最后一个的大小关系，如果 当前的大（低位）没问题，前面的大（高位）则需要把前面的干掉
            while (k != 0 && !queue.isEmpty() && queue.peekLast() > c) {
                //说明前面的大 要把前面的干掉
                queue.pollLast();
                k--;
            }


            //为了拿出来的时候按顺序我们这里从 尾部开始添加
            queue.offerLast(c);
        }

        if (k != 0) {
            for (int i = 0; i < k; i++) {
                queue.pollLast();
            }
        }
        if (queue.isEmpty()) return "0";
        if (queue.size() == num.length()) return num.substring(0, num.length() - 1);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character c = queue.pollFirst();
            if (sb.length() == 0 && c == '0') {
                continue;
            }
            sb.append(c);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
