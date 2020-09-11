package com.niluogege.lib.test;

import java.security.MessageDigest;

public class t {
    public static final String a(String var0) {
        char[] var6 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        Exception var10000;
        label32: {
            int var3;
            byte[] var10;
            char[] var12;
            boolean var10001;
            try {
                var10 = var0.getBytes();
                MessageDigest var7 = MessageDigest.getInstance("MD5");
                var7.update(var10);
                var10 = var7.digest();
                var3 = var10.length;
                var12 = new char[var3 * 2];
            } catch (Exception var9) {
                var10000 = var9;
                var10001 = false;
                break label32;
            }

            int var1 = 0;

            for(int var2 = 0; var1 < var3; ++var1) {
                byte var4 = var10[var1];
                int var5 = var2 + 1;
                var12[var2] = var6[var4 >>> 4 & 15];
                var2 = var5 + 1;
                var12[var5] = var6[var4 & 15];
            }

            try {
                var0 = new String(var12);
                return var0;
            } catch (Exception var8) {
                var10000 = var8;
                var10001 = false;
            }
        }

        Exception var11 = var10000;
        var11.printStackTrace();
        return null;
    }
}