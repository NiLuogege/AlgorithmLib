package com.niluogege.lib.leetcode.双指针法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * 你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 链接：https://leetcode.cn/problems/3sum
 */
public class 三数之和 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum_me(nums));
        System.out.println(threeSum(nums));
    }

    /**
     *
     * 核心思想：
     * - 先进行数组排序，排序以后数据有了规律，就可以进行去重，去重的话 三个数组都要进行去重
     * - 三个指针
     *
     * 参考：https://leetcode.cn/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len < 3) return res;

        //进行排序，排序以后可以通过数组的顺序做优化
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            //nums[i] 已经大于0了 ，加上其他的更不可能大于0了 所以结束循环
            if (nums[i] > 0) break;

            //去重
            if (i>0 && nums[i]==nums[i-1])continue;

            //创建双指针
            int l = i + 1;
            int r = len - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //说明数字重复要去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;

                    l++;
                    r--;

                } else if (sum < 0) {//说明数都太小了，要将左指针右移
                    l++;
                } else if (sum > 0) {//说明数太大了，要将右指针左移
                    r--;
                }

            }

        }
        return res;
    }

    /**
     * 我自己的解法，可以解决问题但是 LeetCode说超出时间限制，也就是效率不高
     */
    public static List<List<Integer>> threeSum_me(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int ivalue = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int jvalue = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int kvalue = nums[k];

                    if (ivalue + jvalue + kvalue == 0) {
                        ArrayList<Integer> list = new ArrayList();
                        list.add(ivalue);
                        list.add(jvalue);
                        list.add(kvalue);
                        Collections.sort(list);
                        if (!res.contains(list)) {
                            res.add(list);
                        }
                    }

                }
            }
        }
        return res;
    }
}
