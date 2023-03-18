package com.niluogege.lib;

import java.util.Arrays;
import java.util.Hashtable;

public class MyClass {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 5, 19, 8, 4, 1, 20, 13, 16};
        冒泡(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void 冒泡(int[] arr) {

        if (arr == null) return;

        //减1是因为后面判断会+1
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-j; j++) {
                if (j+1<arr.length){
                    if (arr[j]>arr[j+1]){
                        int temp = arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
            }
        }
    }
}
