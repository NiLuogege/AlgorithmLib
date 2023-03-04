package com.niluogege.lib.leetcode;


/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * <p>
 * 链接：https://leetcode.cn/problems/container-with-most-water
 */
public class 盛最多水的容器 {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        System.out.println(maxArea_me(height));
        System.out.println(maxArea(height));
    }

    /**
     * 双指针法 ,核心思想为 左右两边双向挪动，
     */
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            //面积  = 最小边 * 步长
            int temp = Math.min(height[l], height[r]) * (r - l);
            //记录最大值
            res = Math.max(temp, res);

            //如果左边的大 就挪右边的，反之挪左边的
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return res;


    }

    /**
     * 这种称为暴力解法，计算出所有可能的容积大小，取出最大值
     */
    public static int maxArea_me(int[] height) {

        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[i] * j > max) {
                    max = height[i] * j;
                    System.out.println("height=" + height[i] + " index=" + j);
                }
            }
        }

        return max;
    }

}
