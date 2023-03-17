package com.niluogege.lib.leetcode.动态规划;

/**
 * https://leetcode.cn/problems/unique-paths-ii/
 */
public class 不同路径II {
    public static void main(String[] args) {
//        int[][] arr = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
//        int[][] arr = new int[][]{{0,1},{0,0}};
        int[][] arr = new int[][]{{1,0}};
        System.out.println(uniquePathsWithObstacles(arr));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        //dp[i][j] 存储 这个位置上的路径个数
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        System.out.println("m - 1="+(m - 1) +" n - 1="+(n - 1));
        return dp[m - 1][n - 1];
    }
}
