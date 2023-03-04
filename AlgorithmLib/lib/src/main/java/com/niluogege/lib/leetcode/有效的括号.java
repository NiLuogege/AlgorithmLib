package com.niluogege.lib.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 链接：https://leetcode.cn/problems/valid-parentheses
 */
public class 有效的括号 {
    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

    /**
     * 思路：
     * - 遇到左括号入栈 ，读到右括号然后从栈顶拿到左括号配对，如果栈中没有，或者不是配对的 这说明不是有效的字符串
     * - 遍历到最终如果栈中还有说明 不是有效字符串
     * <p>
     * 参考：
     * https://leetcode.cn/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/
     */
    public static boolean isValid(String s) {
        int len = s.length();
        //要是有效括号必须是 2的倍数
        if (len % 2 != 0) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> satck = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            //判断是否是右括号
            if (pairs.containsKey(c)) {
                //栈是空的 或者 括号不配对返回false
                if (satck.isEmpty() || pairs.get(c) != satck.peek()) {
                    return false;
                }
                satck.pop();
            } else {
                //左括号入栈
                satck.push(c);
            }
        }
        return satck.isEmpty();
    }
}
