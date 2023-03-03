package com.niluogege.lib;

public class MyClass {

    public static void main(String[] args) {
        String[] list = new String[]{"红", "白", "蓝", "红", "白", "蓝", "红", "蓝", "白", "红", "红", "白", "蓝", "红"};


        int lastW = 0;
        int lastB = 0;
        int firstR = list.length - 1;

        while (lastW <= firstR) {

            String w = list[lastW];

            if (w.equals("白")) {
                lastW++;
            } else if (w.equals("蓝")) {

                swip(list, lastW, lastB);
                lastW++;
                lastB++;
            } else {
                while (lastW < firstR && list[firstR].equals("红")) firstR--;

                swip(list, lastW, firstR);
                firstR--;
            }

        }

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }

    }

    public static void swip(String[] list, int a, int b) {
        String temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }
}
