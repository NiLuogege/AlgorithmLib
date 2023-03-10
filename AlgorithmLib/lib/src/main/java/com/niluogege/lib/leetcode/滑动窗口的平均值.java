package com.niluogege.lib.leetcode;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/qIsx9U/solution/hua-dong-chuang-kou-de-ping-jun-zhi-by-l-0rxf/
 * <p>
 * 这个题他妈就就是 计算滑块中数字的总和 / 滑块中数字的个数 的值，他妈的题目描述那么复杂干嘛呢
 */
public class 滑动窗口的平均值 {

    public static void main(String[] args) {

        MovingAverage(3);
        System.out.println(next(1));
        System.out.println(next(10));
        System.out.println(next(3));
        System.out.println(next(5));
    }


    private static LinkedList<Integer> queue = null;
    private static int size;//queue的容量大小，也就是滑块大小
    private static double sum;//后size位的总和

    public static void MovingAverage(int size) {
        //初始化 队列容量
        queue = new LinkedList<Integer>();
        滑动窗口的平均值.size = size;
        sum=0;
    }

    public static double next(int val) {
        //说明超过了 queue的容量大小，也就是滑块大小
        if (queue.size() >= size) {
            //删除前一个
            sum -= queue.pollFirst();
        }

        //计算总和
        sum += val;
        //加入到 queue中
        queue.offerLast(val);

        return sum / queue.size();
    }
}
