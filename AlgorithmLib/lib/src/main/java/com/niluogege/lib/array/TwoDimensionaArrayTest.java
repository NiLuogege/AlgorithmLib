package com.niluogege.lib.array;

import java.util.Arrays;

/**
 * 二维数组
 */
public class TwoDimensionaArrayTest {
    public static void main(String[] args) {

        int[][] orderlyTDArray = createOrderlyTDArray();
//        String search = searchByNormel(orderlyTDArray, 7);
//        System.out.println("7= " + search);
//
//        String search5 = searchByNormel(orderlyTDArray, 5);
//        System.out.println("5= " + search5);


        boolean b20 = searchConformRulesArray(orderlyTDArray, 20);
        System.out.println("b20= " + b20);

    }


    /**
     * 创建 规则为
     * <p>
     * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序
     * <p>
     * 的二维数组 ,内容如下
     * <p>
     *
     * @return
     */
    private static int[][] createOrderlyTDArray() {
        return new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };
    }


    /**
     * 查找 符合 {@link #createOrderlyTDArray()} 生成的 二维数组
     * <p>
     * 参考 : https://liweiwei1419.github.io/sword-for-offer/04-%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E6%9F%A5%E6%89%BE/
     *
     * @return
     */
    private static boolean searchConformRulesArray(int[][] array, int target) {

        if (array != null && array.length > 0) {

            //从右上开始查找
            int col = array[0].length - 1;//列
            int row = 0;//行

            while (col >= 0 && col < array[0].length && row >= 0 && row < array.length) {
                int v = array[row][col];
                if (v == target) {
                    return true;
                } else if (v > target) {
                    col -= 1;
                } else {
                    row++;
                }
            }

        }

        return false;
    }


    /**
     * 通用 有序二维数组查找
     * <p>
     * 时间复杂度为 O(n^2) 比较浪费时间，
     *
     * @param array
     * @param target
     * @return
     */
    private static String searchByNormel(int[][] array, int target) {

        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                int[] innerArray = array[i];
                if (innerArray != null) {
                    for (int j = 0; j < innerArray.length; j++) {
                        int v = innerArray[j];
                        if (v == target) {
                            return "i= " + i + " j= " + j;
                        }
                    }
                }
            }
        }

        return "not fond";

    }
}
