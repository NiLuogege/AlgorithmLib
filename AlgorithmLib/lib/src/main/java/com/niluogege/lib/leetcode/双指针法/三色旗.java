package com.niluogege.lib.leetcode.双指针法;

/**
 * 一条绳子挂红白蓝三种颜色的旗子，且排列无序，现用程序把三种旗子同色归类，顺序为蓝-白-红，每次只能交换2面旗子，采用最少步骤完成。
 * 算法描述：只需把红色和蓝色的旗子进行交换，红旗和篮旗都就位后，白旗自然就位。
 * 参考：https://www.cnblogs.com/hoojjack/p/5208075.html#:~:text=%E7%AE%97%E6%B3%95%E5%88%86%E6%9E%90%E4%B9%8B%E4%B8%89%E8%89%B2%E6%97%97%E7%AE%97%E6%B3%95,%E4%B8%80%E6%9D%A1%E7%BB%B3%E5%AD%90%E6%8C%82%E7%BA%A2%E7%99%BD%E8%93%9D%E4%B8%89%E7%A7%8D%E9%A2%9C%E8%89%B2%E7%9A%84%E6%97%97%E5%AD%90%EF%BC%8C%E4%B8%94%E6%8E%92%E5%88%97%E6%97%A0%E5%BA%8F%EF%BC%8C%E7%8E%B0%E7%94%A8%E7%A8%8B%E5%BA%8F%E6%8A%8A%E4%B8%89%E7%A7%8D%E6%97%97%E5%AD%90%E5%90%8C%E8%89%B2%E5%BD%92%E7%B1%BB%EF%BC%8C%E9%A1%BA%E5%BA%8F%E4%B8%BA%E8%93%9D-%E7%99%BD-%E7%BA%A2%EF%BC%8C%E6%AF%8F%E6%AC%A1%E5%8F%AA%E8%83%BD%E4%BA%A4%E6%8D%A22%E9%9D%A2%E6%97%97%E5%AD%90%EF%BC%8C%E9%87%87%E7%94%A8%E6%9C%80%E5%B0%91%E6%AD%A5%E9%AA%A4%E5%AE%8C%E6%88%90%E3%80%82
 */
public class 三色旗 {

    public static void main(String[] args) {
        String[] list = new String[]{"红", "白", "蓝", "红", "白", "蓝", "红", "蓝", "白", "红", "红", "白", "蓝", "红"};
//我的方法
//        myMethod(list);

        doMove(list);
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }

    }


    static int count = 0;


    /**
     * 核心思想是 ：不管白色，只保证前面的 蓝色和后面的红色 是正确的难么必然 白色就是正确的，这就将 复杂度从3的纬度降到的 2的纬度
     * <p>
     * 操作
     * - 每次都从头检索白色（当然检索过的就不检索了）然后记录下来
     * - 当下一个是蓝色就将 上面记录的白色和这个蓝色进行替换
     * - 当下一个是红色则就将上面记录的白色和最后一个不是红色的进行替换
     * - 直到白色和红色相遇这说明 顺序调完了
     */
    public static String[] doMove(String[] flags) {

        //用于记录 坐标最小的白色
        int lastWIndex = 0;
        //最后一个不是蓝色的角标
        int lastBIndex = 0;
        //记录倒数第一个不是红色的角标
        int fistRIndex = flags.length - 1;

        while (lastWIndex <= fistRIndex) {
            if (flags[lastWIndex].equals("白")) {
                //用于记录 坐标最小的白色
                lastWIndex++;
            } else if (flags[lastWIndex].equals("蓝")) {
                //将白色和蓝色换位置
                swap(flags, lastBIndex, lastWIndex);
                lastBIndex++;
                lastWIndex++;
                count++;
            } else {//走到这里肯定说明 flags[wFlag] 为红色
                //这里是在判断 最后一个是不是红的 ，是的话就不做操作，将红色指针向前挪，直到最后一个不是红色
                while (lastWIndex < fistRIndex && flags[fistRIndex].equals("红"))
                    fistRIndex--;

                //将红色挪到 倒数最后一个不是红色的位置上也就是 rFlag位置上
                //将白色和红色换位置
                swap(flags, fistRIndex, lastWIndex);
                //挪完以后 rFlag位置上就是红色的了，然后再指向下一个位置
                fistRIndex--;
                count++;
            }
        }
        return flags;
    }

    private static void swap(String[] flags, int x, int y) {
        String temp;
        temp = flags[x];
        flags[x] = flags[y];
        flags[y] = temp;
    }


    /**
     * 我的方法，可以处理问题，可以解决问题
     */
    private static void myMethod(String[] list) {
        String[] newList = new String[list.length];
        String[] sortList = new String[]{"蓝", "白", "红"};
        int curIndex = 0;
        for (int i = 0; i < sortList.length; i++) {
            String sortKey = sortList[i];
            for (int j = 0; j < list.length; j++) {
                String str = list[j];
                if (str.equals(sortKey)) {
                    newList[curIndex] = str;
                    curIndex++;
                }
            }
        }

        for (int i = 0; i < newList.length; i++) {
            System.out.println(newList[i]);
        }
    }

}
