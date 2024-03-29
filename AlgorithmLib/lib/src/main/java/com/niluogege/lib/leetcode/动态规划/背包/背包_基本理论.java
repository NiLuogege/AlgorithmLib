package com.niluogege.lib.leetcode.动态规划.背包;

/**
 * 挺重要的，包含了最基本的思路
 *
 *  背包理论基础1 ：https://www.programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html#%E4%BA%8C%E7%BB%B4dp%E6%95%B0%E7%BB%8401%E8%83%8C%E5%8C%85
 */
public class 背包_基本理论 {


    public static void main(String[] args) {

        //1. dp[i][j]  标识 0-i 物品中任意选放入到 j 容量的背包中 的 最大价值  ，
        // 最后在强调一下 i 是物品，j是背包容量， dp[i][j] 是 0-i 个任意物品 放到j容量中最大的价值

        //2. 递推公式理解如下：（ dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);）
        //dp[i][j]也就是 0-i的物品中在当前容量能放的最大价值有两种情况
        //  1. 当前容量出去已经放的物品，再放不下 i 代表的物品了 那么 dp[i][j] 就等于 dp[i-1][j]
        //  2. 当前容量出去已经放的物品，还能放下 i 代表的物品 ，那么 就需要 去查找在 物品区间为 [i-1] 且 容量为[j-weight[i]]  的时候
        //      记录的价值的最大值，再加上物品i 的价值 ，这种情况下的最终表达式为 dp[i - 1][j - weight[i]] + value[i])
        //所以最终的表达式是在 这两种情况中找到最大值，所以最终表达式为 dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);


        //3. dp的初始化
        //  1. 当背包容量为0是那么最大价值肯定也都是0， 也就是 dp[i][0] =0
        //  2. 当物品只有一个的时候 也就是 i = 0 的时候，背包的最大价值那都是 第0个物品的价值，也就是 dp[0][j]=value[0]

        //4. 遍历顺序
        //  其实这里先遍历物品和先遍历背包都可以，但是先遍历物品比较好理解，因为是物品放到背包里
        //最终的公式为
//        weight数组的大小 就是物品个数
//        for(int i = 1; i < weight.size(); i++) { // 遍历物品
//            for(int j = 0; j <= bagweight; j++) { // 遍历背包容量
//                if (j < weight[i]) dp[i][j] = dp[i - 1][j];
//                else dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
//
//            }
//        }
    }
}
