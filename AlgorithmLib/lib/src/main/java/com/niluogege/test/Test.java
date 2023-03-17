package com.niluogege.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {


        int n = 3;
        int res = 0;
        while (n > 2) {
            res += n / 3;

            n = (n / 3) +( n % 3);
        }

        if (n==2){
            res+=1;
        }

        System.out.println(res);


    }
//
//    private static void fun (num){
//
//    }


}
