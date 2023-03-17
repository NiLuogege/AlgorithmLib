package com.niluogege.lib.leetcode.动态规划;

/**
 * https://leetcode.cn/problems/fibonacci-number/
 */
public class 斐波那契数 {

    public static void main(String[] args) {


        //动态规划五部曲
        //1. 确定dp 数组及下标含义
        //2. 确定递推公式
        //3. dp数组如何初始化
        //4. 确定遍历顺序
        //5. 举例推导递推公式
        //如果代码有问题打印 dp 数组进行调试

        int n = 4;
        //1. 这里的 dp数组代表第 i个元素的值
        int[] dp = new int[n+1];

        //2. 递推公式为 F(n) = F(n-1)+F(n-2)
        //3. dp数组如何初始化 F(0)=0 ,F(1)=1
        dp[0] = 0;
        dp[1] = 1;

        //4. 确定遍历顺序，因为这里是后面一个依赖前面一个那就需要顺序遍历
        for (int i = 2; i <=n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }


        //5. 现在有问题 那么就打印dp数组进行调试
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(dp[i]);
//        }
        System.out.println(dp[n]);
    }
}
