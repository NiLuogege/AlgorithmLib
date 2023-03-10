package com.niluogege.lib.leetcode;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class 最长递增子序列 {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{9, 2, 5, 3, 7, 101, 18}));
//        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
//        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
//        System.out.println(lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
//        System.out.println(lengthOfLIS(new int[]{4,10,4,3,8,9}));
    }

    /**
     * 参考链接中的动态规划
     * <p>
     * https://leetcode.cn/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        //dp数组中的每一项用于存储 当前index上最大递增串长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            //默认为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                System.out.println("i=" + i + " nums[j]=" + nums[j] + " nums[i]=" + nums[i] + " dp[i]=" + dp[i]);
                if (nums[i] > nums[j]) {
                    //这里会对 dp[j]  进行 =1 操作
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    System.out.println("------》i=" + i + " nums[j]=" + nums[j] + " nums[i]=" + nums[i] + " dp[i]=" + dp[i]);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 我感觉我的是正确的 就是暴力解法
     * 从第一个开始 ，然后向后查找最大的递增序列 ，然后从第二个开始
     * <p>
     * 他娘的 递增子串 他妈的可以不是联系的，草。。。。。 ，我的解法是必须是连续的。
     */
    public static int lengthOfLIS_me(int[] nums) {

        if (nums == null) return 0;

        int maxLen = 1;

        int firstIndex = 0;
        int lastIndex = 1;
        while (firstIndex < nums.length && lastIndex < nums.length) {
            int first = nums[lastIndex - 1];
            int last = nums[lastIndex];

            //说明是递增的
            if (last > first) {
                if ((lastIndex - firstIndex) + 1 > maxLen) {
                    maxLen = lastIndex - firstIndex + 1;
                }
                lastIndex++;
            } else {
                firstIndex++;
                lastIndex = firstIndex + 1;
            }
        }


        return maxLen;
    }
}
