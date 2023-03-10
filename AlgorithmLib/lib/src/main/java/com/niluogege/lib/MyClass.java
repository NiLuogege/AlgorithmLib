package com.niluogege.lib;

import java.util.Hashtable;

public class MyClass {

    public static void main(String[] args) {
        Hashtable<String, String> sb = new Hashtable<>();

    }

    public static void swip(String[] list, int a, int b) {
        String temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }
}
