package com.niluogege.lib.leetcode.华为;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 参考：https://www.google.com/search?q=Excel%E5%8D%95%E5%85%83%E6%A0%BC%E6%95%B0%E5%80%BC%E7%BB%9F%E8%AE%A1+%E7%AE%97%E6%B3%95%E9%A2%98&hl=zh-CN&ei=LkARZIyeN8P_hwPDx4iIDg&ved=0ahUKEwiM7e7Mg939AhXD_2EKHcMjAuEQ4dUDCA8&uact=5&oq=Excel%E5%8D%95%E5%85%83%E6%A0%BC%E6%95%B0%E5%80%BC%E7%BB%9F%E8%AE%A1+%E7%AE%97%E6%B3%95%E9%A2%98&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQogQyBQgAEKIEMgUIABCiBDoKCAAQRxDWBBCwA0oECEEYAFDoBlj9NGCONmgGcAF4AYABzwKIAesTkgEHMS4wLjcuMpgBAKABAcgBCsABAQ&sclient=gws-wiz-serp
 */
public class Excel单元格数值统计 {

    private static HashMap<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("A", 0);
            put("B", 1);
            put("C", 2);
            put("D", 3);
            put("E", 4);
            put("F", 5);
            put("G", 6);
        }
    };

    private static int res = 0;

    public static void main(String[] args) {

        String aa = "5 3 \n" +
                "10 12 =C5 \n" +
                "15 5 6 \n" +
                "7 8 =3+C2 \n" +
                "6 =B2-A1 =C2 \n" +
                "7 5 3 \n" +
                "B2:C4";
        Scanner in = new Scanner(aa);

        String rowCol = in.nextLine();
        String[] s = rowCol.split(" ");
        int row = Integer.parseInt(s[0]);//行
        int col = Integer.parseInt(s[1]);//列


        //所有数据填入二维数组
        String[][] arr = new String[row][col];
        for (int i = 0; i < row; i++) {
            String[] cArr = new String[col];
            String r = in.nextLine();
            String[] split = r.split(" ");
            for (int j = 0; j < col; j++) {
                cArr[j] = split[j];
            }
            arr[i] = cArr;
        }

        String region = in.nextLine();
        String[] regions = region.split(":");
        String first = regions[0].substring(0, 1);
        int colLeft = map.get(first);
        int rowTop = Integer.parseInt(regions[0].substring(1)) - 1;
        String second = regions[1].substring(0, 1);
        int colRight = map.get(second);
        int rowBottom = Integer.parseInt(regions[1].substring(1)) - 1;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i >= rowTop && i <= rowBottom && j >= colLeft && j <= colRight) {
                    String str = arr[i][j];
                    calculation(arr, str, false);
                }
            }
        }


        System.out.println(res);
    }

    private static void calculation(String[][] arr, String str, boolean isSub) {
        if (str != null && str.length() > 0) {
            char firstC = str.charAt(0);
            if (isNumber(firstC)) {
                System.out.println("calculation  str=" + str + " isSub=" + isSub);
                if (isSub) {
                    res -= Integer.parseInt(str);
                } else {
                    res += Integer.parseInt(str);
                }
            } else if (firstC == '=') {//说明是表达式
                handleExpression(arr, str);
            } else {
                // 目前没有这种情况
            }
        }
    }

    //处理表达式
    private static void handleExpression(String[][] arr, String str) {
        str = str.replace("=", "");//去掉等于号
        if (str.contains("-")) {//说明是减法
            String[] split = str.split("-");
            String first = split[0];
            handle(arr, first, false);
            String second = split[1];
            handle(arr, second, true);
        } else if (str.contains("+")) {//说明是加法
            String[] split = str.split("\\+");
            String first = split[0];
            handle(arr, first, false);
            String second = split[1];
            handle(arr, second, false);
        } else {//说明只是引用
            handle(arr, str, false);
        }
    }

    private static void handle(String[][] arr, String str, boolean isSub) {
        if (str != null && str.length() > 0) {
            char firstC = str.charAt(0);
            if (isNumber(firstC)) {

                if (isSub) {
                    res -= Integer.parseInt(str);
                } else {
                    res += Integer.parseInt(str);
                }
            } else if (isEnglish(firstC) && str.length() >= 2) {//说明是引用其他位置的值
                int colIndex = map.get(firstC + "");
                int rowIndex = Integer.parseInt(str.substring(1)) - 1;
                String targetStr = arr[rowIndex][colIndex];
                calculation(arr, targetStr, isSub);
            } else {
                //目前没有这种情况
            }
        }
    }

    private static boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isEnglish(char c) {
        return 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z';
    }


}
