package com.niluogege.lib.leetcode;

import java.util.HashSet;

/**
 * https://leetcode.cn/problems/distribute-candies/
 */
public class 分糖果 {

    public static void main(String[] args) {

//        System.out.println(distributeCandies(new int[]{1,1,2,2,3,3}));
//        System.out.println(distributeCandies(new int[]{1, 1, 2, 3}));
        System.out.println(distributeCandies(new int[]{6,6,6,6}));

    }

    /**
     * 分两种情况
     * 1。 糖果种类大于 糖果个数/2 那么结果就是 糖果个数/2
     * 2. 糖果种类小于 糖果个数/2 那么结果就是 糖果种类
     */
    public static int distributeCandies(int[] candyType) {
        if (candyType == null) return 0;

        int len = candyType.length;

        //种类数
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            set.add(candyType[i]);
        }

        int typeNum = set.size();
        int res = len / 2;

        return Math.min(res, typeNum);
    }
}
