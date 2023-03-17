package com.niluogege.lib.leetcode.动态规划.基础;

/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 */
public class 爬楼梯_使用最小花费爬楼梯 {

    public static void main(String[] args) {
//        int[] arr = new int[]{10, 15, 20};
        int[] arr = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};


        System.out.println(minCostClimbingStairs(arr));
    }

    /**
     * 动态规划五部曲
     * 1. 确定dp数组和下标含义
     * 2. 确定递推公式
     * 3. 确定dp数组初始值和 遍历初始值
     * 4. 确定遍历顺序
     * 5. 有问题的时候打印dp数组进行调试
     */
    public static int minCostClimbingStairs(int[] cost) {

        int n = cost.length;

        //1. dp数组的含义是到第i个台阶花费的最低费用
        int[] dp = new int[n];

        //2. 递推公式  dp[i] = min(dp[i-1],dp[i-2])+ cost[i]  注意一定要加上当前台阶的值

        //3. 确认初始值和遍历开始值
        dp[0] = cost[0];
        dp[1] = cost[1];

        //4. 确认遍历顺序，后面依赖前面的就是 从小到到
        for (int i = 2; i < n; i++) {
            dp[i] += Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(dp[i]);
        }

        System.out.println("\n");
        return dp[n - 1];
    }
}
