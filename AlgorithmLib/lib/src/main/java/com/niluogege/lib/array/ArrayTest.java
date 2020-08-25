package com.niluogege.lib.array;

public class ArrayTest {
    public static void main(String[] args) {
//        int[] nums = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2};
//        int i = minNumberInRotateArray(nums);
//        System.out.println("i= " + i);


        int[] nums = new int[]{1, 2, 3, 4, 5};
        reOrderArray(nums);
        for (int num : nums) {

            System.out.print("reOrderArray= " + num);
        }
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
}
