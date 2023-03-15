package com.niluogege.lib.leetcode.华为;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/discuss/455427761893666816?sourceSSR=search
 */
public class 不含101的数 {

    public static void main(String[] args) {

        Scanner in = new Scanner("1 10");

        String[] split = in.nextLine().split(" ");
        int left = Integer.parseInt(split[0]);
        int right = Integer.parseInt(split[1]);

        int res=0;
        for (int i = left; i <= right; i++) {
            String str = Integer.toBinaryString(i);
            if (str.contains("101")){
                System.out.println("i="+i+" str="+str);
                continue;
            }
            res++;
        }

        System.out.println(res);
    }
}
