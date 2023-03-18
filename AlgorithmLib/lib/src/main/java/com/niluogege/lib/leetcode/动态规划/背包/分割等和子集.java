package com.niluogege.lib.leetcode.动态规划.背包;

/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 */
public class 分割等和子集 {


    public static void main(String[] args) {

        int[] arr = new int[]{1, 5, 11, 5};
//        int[] arr =new int[]{1,2,3,5};
        System.out.println(canPartition(arr));
    }

    public static boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 0) return false;

        int len = nums.length;
        int sum = 0;

        //确认背包大小
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }

        //说明不可能拆成两个和一致的数组
        if (sum % 2 != 0) return false;

        int target = sum / 2;

        int[] dp = new int[target + 1];

        for (int i = 0; i < len; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        //说明 可以分为两个数组
        if (dp[target] == target) return true;

        return false;
    }
}
