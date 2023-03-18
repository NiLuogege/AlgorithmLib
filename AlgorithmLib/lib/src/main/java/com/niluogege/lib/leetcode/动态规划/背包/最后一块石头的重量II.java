package com.niluogege.lib.leetcode.动态规划.背包;

/**
 * https://leetcode.cn/problems/last-stone-weight-ii/
 */
public class 最后一块石头的重量II {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 7, 4, 1, 8, 1};
//        int[] arr = new int[]{31,26,33,21,40};
        System.out.println(lastStoneWeightII(arr));
    }

    /**
     * 1. 确定dp 数组及下标代表含义
     * 2. 确定递推公式
     * 3. 确定初始值
     * 4. 确定遍历顺序
     */
    public static int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length <= 0) return 0;

        int len = stones.length;


        //石头总中重量
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += stones[i];
        }


        //背包大小
        int target = sum / 2;

        int dp[] = new int[target + 1];

        for (int i = 0; i < len; i++) {

            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }


        return sum - dp[target] - dp[target];
    }
}
