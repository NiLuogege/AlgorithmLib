package com.niluogege.lib.string;


import com.niluogege.lib.utils.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringTest {

    public static void main(String[] args) {
//        String space = replaceSpace("We Are Happy");
//        System.out.println("space= " + space);

//        boolean match = isMatch("aa", "a*");
//        System.out.println("match= " + match);


//        boolean number = isNumber("+100");
//        System.out.println("number= " + number);

        //字符串的排列
        String[] permutation = permutation("abc");
        for (String s : permutation) {
        System.out.println(s);
        }
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


    enum State {
        STATE_INITIAL,//初始状态
        STATE_INT_SIGN,// 符号位
        STATE_INTEGER,// 整数部分
        STATE_POINT,// 左侧有整数的小数点
        STATE_POINT_WITHOUT_INT, // 左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
        STATE_FRACTION, // 小数部分
        STATE_EXP, // 字符 e
        STATE_EXP_SIGN, // 指数部分的符号位
        STATE_EXP_NUMBER,  // 指数部分的整数部分
        STATE_END,// 末尾的空格
    }

    enum CharType {
        CHAR_NUMBER, // 数值
        CHAR_EXP, // e
        CHAR_POINT, // 小数点
        CHAR_SIGN, // 符号
        CHAR_SPACE, // 空格
        CHAR_ILLEGAL,// 非法字符
    }

    /**
     * 题目：请实现一个函数用来判断字符串是否表示数值（包括整数和小数）
     * 参考： https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/biao-shi-shu-zhi-de-zi-fu-chuan-by-leetcode-soluti/
     * <p>
     * 思路：使用有限状态自动机 这种计算模型来实现， 有限状态自动机 也是 正则表达式的 核心算法原理
     * 具体思路就是 穷举所有的可能性， 然后在所有可能性中查找， 其实难点是在 穷举所有可能性中
     */
    public static boolean isNumber(String s) {

        //转移
        Map<State, Map<CharType, State>> transfer = new HashMap<>();

        //初始状态 的可能性
        Map<CharType, State> initMap = new HashMap<CharType, State>() {
            {
                put(CharType.CHAR_SPACE, State.STATE_INITIAL);
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
                put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
            }
        };
        transfer.put(State.STATE_INITIAL, initMap);

        //当前要是符号位 那么 下个字符的可能性
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);

        //当前要是数字 那么 下个字符的可能性
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);

        //当前要是 小数 那么 下个字符的可能性
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_POINT, pointMap);

        //当前要是 . 开头的小数 那么 下个字符的可能性
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType charType = toCharType(s.charAt(i));
            if (transfer.get(state).containsKey(charType)) {
                state = transfer.get(state).get(charType);
            } else {
                return false;
            }
        }

        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION
                || state == State.STATE_EXP_NUMBER || state == State.STATE_END;

    }

    public static CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else if (ch == ' ') {
            return CharType.CHAR_SPACE;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }


    static List<String> res = new LinkedList<>();
    static char[] c;

    /**
     * 题目：字符串的排列
     * 参考：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/solution/mian-shi-ti-38-zi-fu-chuan-de-pai-lie-hui-su-fa-by/
     * 思路：深度优先算法 + 剪枝
     */
    private static String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    private static void dfs(int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c));//最后一位
            return;
        }

        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue;//重复，所以要剪枝
            set.add(c[i]);
            swip(i, x);
            dfs(x + 1);//开启固定第 x+1位字符
            swip(i, x);//恢复交换
        }
    }

    private static void swip(int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }
}
