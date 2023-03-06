package com.niluogege.lib.leetcode;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/?favorite=ex0k24j
 */
public class 搜索旋转排序数组 {
    public static void main(String[] args) {

    }


    /**
     * 使用二分法，
     *
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
     */
    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;

    }

    /**
     * 这是我的做法
     * 就是直接查找就行了，不过这种方法浪费了 他还是有序数组旋转后的这个特性，更好的应该是二分查找吧
     */
    public static int search_me(int[] nums, int target) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res = i;
                break;
            }
        }
        return res;
    }
}
