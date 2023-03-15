package com.niluogege.lib.leetcode.华为;

import java.util.Scanner;

/**
 * 参考 ：https://blog.csdn.net/xiao_pengjy/article/details/128876364
 *
 * 就是计算最大的 有关联的发行版的个数
 */
public class Linux发行版的数量 {

    public static void main(String[] args) {
        String param = "4\n" +
                "1 1 0 0\n" +
                "1 1 1 0\n" +
                "0 1 1 0\n" +
                "0 0 0 1";
        Scanner in = new Scanner(param);
        int total = Integer.parseInt(in.nextLine());
        int[][] arr = new int[total][total];
        for (int i = 0; i < total; i++) {
            int[] subArr= new int[total];
            String[] split = in.nextLine().split(" ");
            for (int j = 0; j < split.length; j++) {
                subArr[j]=Integer.parseInt(split[j]);
            }
            arr[i]=subArr;
        }

        //默认就有一个，就是自己和自己是关联的
        int res=0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i!=j){
                    if (arr[i][j]==1){
                        res++;
                    }
                }
            }
        }

        System.out.println(res/2+1);
    }

}
