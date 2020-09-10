package com.niluogege.lib.number;

import java.util.PriorityQueue;
import java.util.Queue;

public class NumberText {
    public static void main(String[] args) {

//        System.out.println(hammingWeight(-9));
        System.out.println(power(10, -2));

    }


    /**
     * 题目：输入一个 32 位整数，输出该数二进制表示中1的个数。
     * <p>
     * 参考：https://liweiwei1419.github.io/sword-for-offer/15-%E4%BA%8C%E8%BF%9B%E5%88%B6%E4%B8%AD1%E7%9A%84%E4%B8%AA%E6%95%B0/
     * <p>
     * 分析：数分为正数和负数，正数的最高位是0，负数的最高位是1，为了避免 负数时 二进制数右移后 高位补的是1 导致死循环
     * 所以这里要使用 无符号右移
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    /**
     * 题目：实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * <p>
     * 参考：https://leetcode-cn.com/problems/powx-n/solution/50-powx-n-kuai-su-mi-qing-xi-tu-jie-by-jyd/ 中的快速幂方法
     * <p>
     * 这里引入 b 是为了解决 越界问题 具体看参考链接中的解释
     */
    private static double power(double x, int n) {
        if (x == 0) return 0;

        long b = n;
        double res = 1;

        if (b < 0) {
            b = -b;
            x = 1 / x;
        }

        while (b > 0) {

            if ((b & 1) == 1) res *= x; //当 b 是奇数时 需要进行  res *= x 操作
            x *= x;
            b >>= 1;//相当于 b /2 取整

        }
        return res;
    }

    /**
     * 题目： 数据流中的中位数
     * 参考：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/mian-shi-ti-41-shu-ju-liu-zhong-de-zhong-wei-shu-y/
     */
    class MedianFinder {

        Queue<Integer> A = new PriorityQueue<>();//小顶堆
        Queue<Integer> B = new PriorityQueue<>((x, y) -> (y - x));//实现 Comparator 接口进行 大顶堆的实现

        public void addNum(int num) {

            if (A.size() != B.size()) {
                A.add(num);
                B.add(A.poll());
            } else {
                B.add(num);
                A.add(B.poll());
            }
        }

        public double findMedian() {
            return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
        }
    }
}

