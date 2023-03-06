package com.niluogege.lib.leetcode;

/**
 * 删除有序数组中的重复项
 * <p>
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/?favorite=ex0k24j
 */
public class 删除有序数组中的重复项 {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates(arr);
        System.out.println("len=" + len);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 核心思想 快慢指针法 ，慢指针指向不重复数据的位置+1， 快指针寻找下一个不同的值
     * 参考： https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-tudo/
     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        int fast = 1;
        int slow = 1;

        while (fast < len) {

            //快指针相邻不相等，则将 快指针位置上的值赋值给慢指针
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                //慢指针加1
                slow++;
            }
            //快指针加1
            fast++;

        }

        return slow;
    }

}
