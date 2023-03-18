package com.niluogege.lib.leetcode.动态规划.单调栈;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.programmercarl.com/0739.%E6%AF%8F%E6%97%A5%E6%B8%A9%E5%BA%A6.html#%E6%80%9D%E8%B7%AF
 * <p>
 * 单调栈一般主要找 左边或者右边 比当前大或者小的元素
 */
public class 每日温度 {

    public static void main(String[] args) {

        int[] arr = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = dailyTemperatures(arr);
        for (int re : res) {
            System.out.println(re);
        }
    }

    public static int[] dailyTemperatures(int[] temperatures) {

        if (temperatures == null || temperatures.length <= 0) return null;

        int len = temperatures.length;
        int[] result = new int[len];

        //创建单调栈 ,用于装递增元素的下标
        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.push(0);

        //遍历原序列

        for (int i = 1; i < len; i++) {
            int t = temperatures[i];

            if (temperatures[queue.peek()] < t) {
                //说明 后面的比前面的大
                while (!queue.isEmpty() && t > temperatures[queue.peek()]) {
                    int index = queue.pop();
                    result[index] = i - index;
                }
                queue.push(i);
            } else {
                queue.push(i);
            }


        }

        return result;
    }
}
