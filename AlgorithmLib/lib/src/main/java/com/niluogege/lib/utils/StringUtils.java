package com.niluogege.lib.utils;



/**
 * Created by buffert on 17/1/20.
 * StringUitls
 */

public class StringUtils {


    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (isEmpty(str1) && isEmpty(str2)) {
            return true;
        } else if (isEmpty(str1)) {
            return false;
        } else if (isEmpty(str2)) {
            return false;
        }
        return str1.equals(str2);
    }

}
