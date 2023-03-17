package com.niluogege.lib.leetcode.动态规划.基础;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 */
public class 爬楼梯 {
    public static void main(String[] args) {

        //动态规划五部曲
        //1、确定dp数组及下标含义
        //2、确定递推公式
        //3、确定 dp数组初始值 和 循环初始值
        //4、确定遍历顺序
        //5、有问题的话打印dp数组，进行调试


        int n = 4;


        System.out.println(climbStairs(n));;
    }

    private static int climbStairs(int n) {

        if (n <= 2) return n;

        //1 . dp[i] 表示有i个台阶的 走法个数
        int[] dp = new int[n + 1];

        //2. 递推公式 dp[i] = dp[i-1]+dp[i-2]

        //3. 初始化值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        //4. 后面依赖前面所以 正向遍历

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

//            for (int i = 0; i < dp.length; i++) {
//                System.out.println(dp[i]);
//
//            }

        return dp[n];
    }
}
