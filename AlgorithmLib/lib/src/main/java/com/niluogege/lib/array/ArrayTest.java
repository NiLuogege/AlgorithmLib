package com.niluogege.lib.array;

public class ArrayTest {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2};
        int i = minNumberInRotateArray(nums);
        System.out.println("i= " + i);
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
        int end = array.length-1;

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
}
