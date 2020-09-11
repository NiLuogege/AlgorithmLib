package com.niluogege.lib.array;

import java.util.HashMap;
import java.util.Map;

public class ArrayTest {
    public static void main(String[] args) {
//        int[] nums = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2};
//        int i = minNumberInRotateArray(nums);
//        System.out.println("i= " + i);


//        int[] nums = new int[]{1, 2, 3, 4, 5};
//        reOrderArray(nums);
//        for (int num : nums) {
//
//            System.out.print("reOrderArray= " + num);
//        }


//        int i = majorityElement(new int[]{1, 2, 3, 2, 2, 2, 2, 5, 4, 2});
//        int i = majorityElement2(new int[]{3, 2, 3});
//        System.out.println("zhiwei = " + i);

        //连续子数组的最大和
        int aaaa = maxSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5});
        System.out.println("aaaa= " + aaaa);
    }


    /**
     * 查找 旋转数组的最小数字
     * <p>
     * 参考：https://liweiwei1419.github.io/sword-for-offer/11-%E6%97%8B%E8%BD%AC%E6%95%B0%E7%BB%84%E7%9A%84%E6%9C%80%E5%B0%8F%E6%95%B0%E5%AD%97/
     * <p>
     * 分析：其实还是 数组中查找元素，这里使用二分法
     */
    private static int minNumberInRotateArray(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (array[mid] > array[end]) {
                start = mid + 1;
            } else if (array[mid] == array[end]) {
                end = end - 1;
            } else {
                end = mid;
            }

        }
        return array[start];
    }


    /**
     * 题目：调整数组顺序使奇数位于偶数前面
     * 参考： https://liweiwei1419.github.io/sword-for-offer/21-%E8%B0%83%E6%95%B4%E6%95%B0%E7%BB%84%E9%A1%BA%E5%BA%8F%E4%BD%BF%E5%A5%87%E6%95%B0%E4%BD%8D%E4%BA%8E%E5%81%B6%E6%95%B0%E5%89%8D%E9%9D%A2/
     * 思路：使用双指针法，一个从前到后遍历 ，一个从后到前遍历， 后面找到偶数 ，前面找到奇数 然后交换。
     * <p>
     * 其实这个题目使用集合来作更加简单 不过，通过数组作，我们能了解一种解题思路
     */
    private static void reOrderArray(int[] array) {
        int length = array.length;
        if (length == 0) {
            return;
        }

        //奇数都暂存到 buff 中
        int[] buff = new int[length];


        int j = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                j++;
                buff[j] = array[i];
            }
        }

        if (j == -1) {//说明都是偶数
            return;
        }


        int k = length;
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] % 2 == 0) {
                k--;
                array[k] = array[i];
            }
        }


        while (j >= 0) {
            array[j] = buff[j];
            j--;
        }
    }


    static HashMap<Integer, Integer> maps = new HashMap<>();

    /**
     * 题目：数组中出现次数超过一半的数字 - 我自己的解法
     * 思路：最简单的思路，下面有 比人家的解法
     */
    private static int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (maps.containsKey(num)) {
                maps.put(num, maps.get(num) + 1);
            } else {
                maps.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            Integer value = entry.getValue();
            if (value > nums.length / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }

    /**
     * 题目：数组中出现次数超过一半的数字 - 别人家的解法
     * 参考：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/solution/mian-shi-ti-39-shu-zu-zhong-chu-xian-ci-shu-chao-3/
     * 思路：摩尔投票法
     */
    private static int majorityElement2(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }


    /**
     * 题目：连续子数组的最大和-自己的解法
     * 但是时间复杂度是 O(nLogn),下面是标准答案
     */
    private static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int big = nums[0];

        int step = 1;
        while (step <= nums.length) {
            for (int i = 0; i < nums.length; i++) {
                int sum = recur(nums, i, i + step);
                if (big < sum) {
                    big = sum;
                }
            }
            step++;
        }

        return big;

    }

    private static int recur(int[] arr, int start, int end) {
        if (end > arr.length) end = arr.length;
        int temp = 0;
        for (int i = start; i < end; i++) {
            temp += arr[i];
        }
        return temp;
    }

    /**
     * 题目：连续子数组的最大和-自己的解法
     * 参考：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/mian-shi-ti-42-lian-xu-zi-shu-zu-de-zui-da-he-do-2/
     * 思路：使用 动态规划可以大大降低 算法的时间复杂度
     * 上面是自己的 算法
     */
    private static int maxSubArray2(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
