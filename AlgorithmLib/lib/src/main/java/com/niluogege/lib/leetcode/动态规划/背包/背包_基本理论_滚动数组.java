package com.niluogege.lib.leetcode.动态规划.背包;

/**
 * https://www.programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html
 * <p>
 * 滚动数组的核心是复用一维数组来存储 j 容量是 ”最大" 的价值
 */
public class 背包_基本理论_滚动数组 {
    public static void main(String[] args) {
        //1. dp 数组 及 下标的含义
        //dp[j] 标识的含义为 j 容量时 所能包含的 ”最大“ 价值

        //2. 确定 递推公式
        //dp[j] 标识价值最大也有两种情况
        //  1. 当前容量放不下其他物品了，又因为dp[j] 是复用的所以，所以那就还是 dp[j]
        //  2. 当前容量还能放下其他物品 那就是 dp[j-weight[i]]+value[i]
        //所以最终的公式就是 max(dp[j],dp[j-weight[i]]+value[i])

        //3. 确定初始值
        //  1.初始值如果价值都为正数的话那默认都为0
        //  2.如果有赋值的话 那就是 负无穷大 Float.NEGATIVE_INFINITY

        //4. 确认遍历顺序
        //  1.物品还是正序遍历
        //  2.容量要逆序遍历 ，如果容量正序遍历的话会导致物品被放入容器中两次，也就是被使用两次而导致结果出现问题
        //      比如 dp[1] = dp[1 - weight[0]] + value[0] = 15
        //          dp[2] = dp[2 - weight[0]] + value[0] = 30
        //所以最终的遍历公式为
//        for(int i = 0; i < weight.size(); i++) { // 遍历物品
//            for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
//                dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
//            }
//        }


        //例子
        //物品所占容量
        int[] parcel = new int[]{1, 3, 4};
        //物品价值
        int[] value = new int[]{15, 20, 30};

        //物品的个数
        int parcelLen = parcel.length;

        //背包容量
        int bagWeight = 4;

        //dp[j] 背包容量为j的时候 所装的最大容量
        int[] dp = new int[bagWeight+1];

        //遍历物品
        for (int i = 0; i < parcelLen; i++) {
            //逆序遍历 容量大小
            for (int j = bagWeight; j >= parcel[i] ; j--) {
                dp[j] = Math.max(dp[j],dp[j-parcel[i]]+value[i]);

                System.out.println("物品= "+i + "背包容量= "+j +" 最大价值= "+dp[j]);
            }
        }

        System.out.println("");

        System.out.println(dp[bagWeight]);

    }
}
