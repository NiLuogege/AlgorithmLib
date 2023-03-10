package com.niluogege.lib.leetcode.动态规划;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class 买卖股票的最佳时机II {
    public static void main(String[] args) {


    }

    /**
     * 核心思想 动态规划
     *
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;

        //int[i][0] 标识第i天 不持仓的最大收益， int[i][1] 标识第i天，持仓一只股票的最大收益
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        //从1 开始
        for (int i = 1; i < n; ++i) {
            //计算 不持仓的最大收益
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //计算 持仓一只股票的最大收益
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
