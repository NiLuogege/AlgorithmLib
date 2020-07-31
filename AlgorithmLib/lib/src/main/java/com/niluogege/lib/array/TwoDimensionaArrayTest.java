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


//        boolean b20 = searchConformRulesArray(orderlyTDArray, 20);
//        System.out.println("b20= " + b20);


        //字符串路径搜索
//        char[][] board =
//                {
//                        {'A', 'B', 'C', 'E'},
//                        {'S', 'F', 'C', 'S'},
//                        {'A', 'D', 'E', 'E'}
//                };
//
//        String word = "ABCCEF";
//        boolean hasPath = new TwoDimensionaArrayTest().hasPath(board, word);
//        System.out.println(hasPath);

        // 机器人路径
        int count = new TwoDimensionaArrayTest().movingCount(2, 3, 1);
        System.out.println("count= "+count);
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


    //标记
    private boolean[][] marked;

    //        x-1,y
    // x,y-1  x,y    x,y+1
    //        x+1,y
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    // 盘面上有多少行
    private int m;
    // 盘面上有多少列
    private int n;
    private String word;
    private char[][] board;

    /**
     * 题目： 单词搜索
     * 参考： https://leetcode-cn.com/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
     * <p>
     * 知识点：其实就是 Flood fill 算法，本质上是一种递归回溯算法
     */

    public boolean hasPath(char[][] board, String word) {
        m = board.length;
        if (m == 0) {
            return false;
        }
        n = board[0].length;
        marked = new boolean[m][n];
        this.word = word;
        this.board = board;

        //二维数组遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param i     列数
     * @param j     行数
     * @param start 目标字符串的起始位置
     * @return
     */
    private boolean dfs(int i, int j, int start) {
        //如果起始位置 是 字符串的最后一个 字节，则进行特殊判断
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }

        if (board[i][j] == word.charAt(start)) {//寻找开始点位
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (inArea(newX, newY) && !marked[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;
    }

    /**
     * 保证 没有 越界
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    int k;

    /**
     * 题目： 机器人的运动范围
     * <p>
     * 参加：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
     */
    private int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        //用于记录是否走过，防止重复计算
        this.marked = new boolean[m][n];
        return dfs(0, 0, 0, 0);
    }

    /**
     *
     * @param i 横向索引
     * @param j 纵向索引
     * @param si 横向数位和
     * @param sj 纵向数位和
     * @return
     */
    private int dfs(int i, int j, int si, int sj) {
        if (i >= m || j >= n || k < si + sj || marked[i][j]) {
            return 0;
        }

        marked[i][j] = true;

        int iNext = (i + 1) % 10 != 0 ? si + 1 : si - 8;
        int jNext = (j + 1) % 10 != 0 ? sj + 1 : sj - 8;

        return 1 + dfs(i + 1, j, iNext, sj) + dfs(i, j + 1, si, jNext);
    }


}
