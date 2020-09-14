package com.niluogege.lib.test;

import java.util.HashMap;

public class ZhierEnter {

    /**
     * 只二 签名算法
     *
     * 例：
     * java -jar E:\111work\code\code_me\myGitHub\AlgorithmLib\AlgorithmLib\lib\build\libs\lib.jar version=6.0.1 platform=test plat=1 fmt=1 T=1599824332 page=1 pageSize=30
     *
     */
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        if (args != null) {
            for (String arg : args) {
                if (arg != null && arg.length() > 0) {
                    if (arg.contains("=")) {
                        String[] split = arg.split("=");
                        if (split.length == 2) {
                            map.put(split[0], split[1]);
                        } else {
                            System.out.println("arg " + arg + " Contains more than two = ");
                        }
                    } else {
                        System.out.println("arg " + arg + " is not a pair");
                    }
                }
            }
        }

        System.out.println("sign map = " + map.toString());


        i.a(map, true);

        System.out.println("sn=" + map.get("sn"));
    }
}
