package com.niluogege.lib.leetcode.华为;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 参考：https://blog.csdn.net/qfc_128220/article/details/128274181
 *
 * 核心思想：两个队列一个队列记录父节点 ，一个队列记录子节点
 */
public class 查找树中元素 {
    public static void main(String[] args) {
        Integer[][] arr = new Integer[][]{{10, 1, 2}, {-21, 3, 4}, {23, 5}, {14}, {35}, {66}};

        int[] param = {2, 1};

        int deep = param[0];
        int index = param[1];

        //用于存放当前层的根节点
        LinkedList<Integer[]> parentQueue = new LinkedList<>();
        //用于存放当前层的子节点
        LinkedList<Integer[]> childQueue = new LinkedList<>();
        parentQueue.add(arr[0]);
        while (deep > 0) {
            childQueue.clear();

            while (parentQueue.size() > 0) {
                Integer[] parent = parentQueue.pollFirst();
                if (parent.length == 3) {//说明既有左节点也有又节点
                    childQueue.add(arr[parent[1]]);
                    childQueue.add(arr[parent[2]]);
                } else if (parent.length == 2) {//只有子节点
                    childQueue.add(arr[parent[1]]);
                } else {//没有子节点

                }
            }
            parentQueue.clear();
            parentQueue.addAll(childQueue);
            deep--;
        }


        System.out.println(childQueue.get(index)[0]);
    }


}
