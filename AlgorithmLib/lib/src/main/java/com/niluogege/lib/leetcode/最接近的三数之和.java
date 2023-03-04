package com.niluogege.lib.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * <p>
 * 链接：https://leetcode.cn/problems/3sum-closest
 */
public class 最接近的三数之和 {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
//        System.out.println(threeSumClosest_me(nums, 1));
        System.out.println(threeSumClosest(nums, 1));

    }

    /**
     *参考：https://leetcode.cn/problems/3sum-closest/solution/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //预制一个 和
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }

    public static int threeSumClosest_me(int[] nums, int target) {
        int diff = -1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int ivalue = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int jvalue = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int kvalue = nums[k];

                    int temp = ivalue + jvalue + kvalue;
                    if (diff == -1 || (Math.abs(temp - target)) < diff) {
                        res = temp;
                        diff = Math.abs(temp - target);
                    }

                }
            }
        }

        return res;
    }

}
