package com.niluogege.lib.leetcode;

/**
 * https://leetcode.cn/problems/valid-sudoku/
 */
public class 有效的数独 {
    public static void main(String[] args) {

    }

    /**
     * 可以分别使用hashmap 记录 下面的情况
     * 同一个数字在每一行只能出现一次；
     * 同一个数字在每一列只能出现一次；
     * 同一个数字在每一个小九宫格只能出现一次。
     * <p>
     * 核心思想就是记录元素出现的哥说
     *
     * 不过官方解法用数组替代了
     * <p>
     * https://leetcode.cn/problems/valid-sudoku/solution/you-xiao-de-shu-du-by-leetcode-solution-50m6/
     */
    public static boolean isValidSudoku(char[][] board) {

        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //第 i行 第 j 列的元素
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    //用于记录第i行的 某个元素的 出现的个数
                    rows[i][index]++;
                    //用于记录第 j 列的 某个元素的 出现的个数
                    columns[j][index]++;
                    //用于记录三宫格内 某个元素出现的个数
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

}
