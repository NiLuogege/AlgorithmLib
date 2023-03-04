package com.niluogege.lib.leetcode;

public class 盛最多水的容器 {

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    /**
     * 这种称为暴力解法，计算出所有可能的容积大小，取出最大值
     */
    public static int maxArea(int[] height) {

        int max=0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[i]*j>max){
                    max=height[i]*j;
                    System.out.println("height="+height[i]+" index="+j);
                }
            }
        }

        return max;
    }

}
