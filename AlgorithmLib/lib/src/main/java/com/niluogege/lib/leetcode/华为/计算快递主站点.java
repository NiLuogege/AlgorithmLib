package com.niluogege.lib.leetcode.华为;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.nowcoder.com/discuss/458770860959076352?sourceSSR=home
 */
public class 计算快递主站点 {

    public static void main(String[] args) {
        //这个有点儿看不懂
//        firstMethod();

        secondMethod();
    }

    private static void secondMethod() {
        //        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner("4\n" +
                "\n" +
                "1 1 0 0\n" +
                "\n" +
                "1 1 0 0\n" +
                "\n" +
                "0 0 1 0\n" +
                "\n" +
                "0 0 0 1");
        int n = in.nextInt();
        int[][] vals = new int[n][n];
        in.nextLine();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vals[i][j] = in.nextInt();
            }
        }
        int res = 0;
        Set<Integer> resSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (resSet.contains(i)) {
                continue;
            }
            Set<Integer> temp = new HashSet<>();
            temp.add(i);
            f(temp, i, vals);
            resSet.addAll(temp);
            res++;
        }
        System.out.println(res);
    }

    static void f(Set<Integer> temp, int i, int[][] vals) {
        for (int j = 0; j < vals.length; j++) {
            if (temp.contains(j)) {
                continue;
            }
            if (i != j && vals[i][j] == 1) {
                temp.add(j);//纳入与i点联通的合计
                f(temp, j, vals);
            }
        }


    }

    private static void firstMethod() {
        //        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner("4\n" +
                "\n" +
                "1 1 0 0\n" +
                "\n" +
                "1 1 0 0\n" +
                "\n" +
                "0 0 1 0\n" +
                "\n" +
                "0 0 0 1");
        //几行几列的矩阵
        int n = in.nextInt();
        int[][] vals = new int[n][n];
        in.nextLine();

        //用于记录第几行
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            for (int j = 0; j < n; j++) {
                vals[i][j] = in.nextInt();
            }
        }

        for (int i : root) {
            System.out.print(i + "，");
        }
        System.out.println("");


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vals[i][j] == 1) {
                    union(root, i, j);
                }
            }
        }

        for (int i : root) {
            System.out.print(i + "，");
        }

        Set<Integer> resSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            resSet.add(find(root, i));
        }
        System.out.println(resSet.size());
    }

    static int find(int[] root, int i) {
        if (root[i] == i) {
            return i;
        } else {
            return find(root, root[i]);
        }
    }

    static void union(int[] root, int i, int j) {
        int rooti = find(root, i);
        int rootj = find(root, j);
        root[rooti] = rootj;
        System.out.println("i=" + i + " j=" + j + " rooti=" + rooti + " rootj=" + rootj);
    }
}
