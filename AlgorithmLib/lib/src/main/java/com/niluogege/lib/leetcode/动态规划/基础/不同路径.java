package com.niluogege.lib.leetcode.动态规划.基础;

/**
 * https://leetcode.cn/problems/unique-paths/
 */
public class 不同路径 {
    public static void main(String[] args) {

        System.out.println(uniquePaths(1, 1));
//        System.out.println(uniquePaths(3, 3));
//        System.out.println(uniquePaths(3, 7));
    }

    /**
     * 动态规划五板斧
     * 1. 确认dp数组及其下标含义
     * 2. 确认递推公式
     * 3. 确认dp数组初始值，及遍历遍历初始值
     * 4. 确认遍历顺序
     * 5. 有问题打印dp数组进行调试
     */
    public static int uniquePaths(int m, int n) {

        //1. dp[i][j] 的含义为到达第 i,j 坐标有多少条路径
        int[][] dp = new int[m][n];

        //2. 递推公式 dp[i][j] = dp[i-1][j] + dp[i][j-1]

        //3. 确定dp初始值
        //4. 顺序遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j]);
//            }
//            System.out.println("");
//        }
//
//        System.out.println("\n");

        //
        return dp[m - 1][n - 1];
    }
}
