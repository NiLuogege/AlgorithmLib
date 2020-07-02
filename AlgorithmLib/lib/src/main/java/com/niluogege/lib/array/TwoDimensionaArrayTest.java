package com.niluogege.lib.array;

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


        boolean b7 = searchConformRulesArray(orderlyTDArray, 1);
        System.out.println("7= " + b7);

        boolean b5 = searchConformRulesArray(orderlyTDArray, 9);
        System.out.println("5= " + b5);

        boolean b9 = searchConformRulesArray(orderlyTDArray, 9);
        System.out.println("9= " + b9);

        boolean b15 = searchConformRulesArray(orderlyTDArray, 15);
        System.out.println("15= " + b15);

        boolean b0 = searchConformRulesArray(orderlyTDArray, 0);
        System.out.println("0= " + b0);
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
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};
    }


    /**
     * 查找 符合 {@link #createOrderlyTDArray()} 生成的 二维数组
     * <p>
     * 参考 : https://liweiwei1419.github.io/sword-for-offer/04-%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E6%9F%A5%E6%89%BE/
     *
     * @return
     */
    private static boolean searchConformRulesArray(int[][] array, int target) {

        if (array != null) {

            int col = array[0].length - 1;//列
            int row = 0;//行

            while (col >= 0) {
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
