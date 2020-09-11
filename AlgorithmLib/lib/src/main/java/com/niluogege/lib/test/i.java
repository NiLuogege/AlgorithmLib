package com.niluogege.lib.test;

import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class i {
    private static String a() {
        return "123456";
    }

    public static void a(Map<String, Object> var0, boolean var1) {
        StringBuilder var2 = new StringBuilder();
        var2.append(b(var0, var1));
        var2.append("&token=");
        var2.append(a());
        var0.put("sn", t.a(var2.toString()));
    }

    public static String b(Map<String, Object> var0, boolean var1) {
        TreeMap var2 = new TreeMap(new i.a());
        var2.putAll(var0);
        StringBuilder var3 = new StringBuilder();
        Iterator var4 = var2.keySet().iterator();

        while (var4.hasNext()) {
            String var5 = (String) var4.next();
            var3.append("&");
            var3.append(var5);
            var3.append("=");

            Exception var10000;
            label40:
            {
                boolean var10001;
                String var6;
                try {
                    var6 = URLEncoder.encode(String.valueOf(var2.get(var5)).trim(), "UTF-8");
                    var3.append(var6);
                } catch (Exception var8) {
                    var10000 = var8;
                    var10001 = false;
                    break label40;
                }

                if (!var1) {
                    continue;
                }

                try {
                    var0.put(var5, var6);
                    continue;
                } catch (Exception var7) {
                    var10000 = var7;
                    var10001 = false;
                }
            }

            Exception var9 = var10000;
            var9.printStackTrace();
        }

        if (var3.length() > 0) {
            var3.delete(0, 1);
        }

        return var3.toString().trim();
    }

    static class a implements Comparator<String> {
        a() {
        }

        @Override
        public int compare(String var1, String var2) {
            return var1.compareTo(var2);
        }


    }
}