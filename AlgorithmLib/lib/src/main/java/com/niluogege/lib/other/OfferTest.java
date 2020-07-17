package com.niluogege.lib.other;

public class OfferTest {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 2};
        int duplicate = findDuplicate(arr);
        System.out.println("duplicate= " + duplicate);
    }


    /**
     * 题目链接：https://leetcode-cn.com/problems/find-the-duplicate-number/
     * <p>
     * 题目：
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
     * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * <p>
     * 要求：
     * 1. 不能更改原数组（假设数组是只读的）。
     * 2. 只能使用额外的 O(1) 的空间。
     * 3. 时间复杂度小于 O(n2) 。
     * 4. 数组中只有一个重复的数字，但它可能不止重复出现一次。
     * <p>
     * 分析：
     * 1. 数据范围固定
     * 2. 只有一个重复的数
     * 3. 需要满足 4 项要求
     * <p>
     * 综上使用 二分法的变种来做
     *
     * @return
     */
    private static int findDuplicate(int[] nums) {
        if (nums != null && nums.length > 0) {


            int length = nums.length;
            int left = 1;
            int right = length + 1;

            while (left < right) {

                //这里加一 主要是为了找到 更大一点的中位数
                int mid = (left + right + 1) / 2;

                int count = 0;

                for (int num : nums) {
                    if (num < mid) {
                        count++;
                    }
                }


                if (count >= mid) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }

            return left;
        }
        return -1;
    }

}
