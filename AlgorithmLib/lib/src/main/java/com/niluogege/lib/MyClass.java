package com.niluogege.lib;

public class MyClass {
    public static void main(String[] args) {
        String[] list = new String[]{"红", "白", "蓝", "红", "白", "蓝", "红", "蓝", "白", "红", "红", "白", "蓝", "红"};


        String temp = "";

        for (int n = 0; n < list.length; n++) {
            temp = list[n];
            for (int i = n + 1; i < list.length; i++) {
                //拿到第一个
                String first = list[i];
                //当两个不相等的时候 向后寻找相等的进行替换
                if (!temp.equals(first)) {
                    for (int j = i + 1; j < list.length; j++) {
                        String second = list[j];
                        if (temp.equals(second)) {
                            //替换两个
                            list[i] = second;
                            list[j] = first;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }

    }
}
