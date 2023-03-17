package com.niluogege.lib.leetcode.华为;

import java.util.Scanner;

/**
 * 参考：https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tqId=21239&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 */
public class 购物单 {
    public static void main(String[] args) {


//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner("1000 5\n" +
                "800 2 0\n" +
                "400 5 1\n" +
                "300 5 1\n" +
                "400 3 0\n" +
                "500 2 0");

        //读取一共多少钱
        int N = sc.nextInt();
        //多去一共多少商品
        int m = sc.nextInt();

        //将商品转为对象
        Goods[] goods = new Goods[m];
        for (int i = 0; i < m; i++) {
            goods[i] = new Goods();
        }
        for (int i = 0; i < m; i++) {
            //价格
            int v = sc.nextInt();
            //重要程度
            int p = sc.nextInt();
            //是否是主件 0 是，非0 否
            int q = sc.nextInt();
            goods[i].v = v;
            //直接标识满意度
            goods[i].p = p * v;  // 直接用p*v，方便后面计算

            System.out.println("q="+q);

            if (q == 0) {
                goods[i].main = true;
            } else if (goods[q - 1].a1 == -1) {
                goods[q - 1].a1 = i;
            } else {
                goods[q - 1].a2 = i;
            }
        }

        int[][] dp = new int[m + 1][N + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = dp[i - 1][j];
                if (!goods[i - 1].main) {
                    continue;
                }
                if (j >= goods[i - 1].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v] + goods[i - 1].p);
                }
                if (goods[i - 1].a1 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a1].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v - goods[goods[i - 1].a1].v] + goods[i - 1].p + goods[goods[i - 1].a1].p);
                }
                if (goods[i - 1].a2 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a2].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v - goods[goods[i - 1].a2].v] + goods[i - 1].p + goods[goods[i - 1].a2].p);
                }
                if (goods[i - 1].a1 != -1 && goods[i - 1].a2 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a1].v + goods[goods[i - 1].a2].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v - goods[goods[i - 1].a1].v - goods[goods[i - 1].a2].v] + goods[i - 1].p + goods[goods[i - 1].a1].p + goods[goods[i - 1].a2].p);
                }
            }
        }
        System.out.println(dp[m][N]);
    }
}

class Goods {
    int v;
    int p;
    boolean main = false;

    int a1 = -1;  //定义附件1的编号
    int a2 = -1;  //定义附件2的编号
}
