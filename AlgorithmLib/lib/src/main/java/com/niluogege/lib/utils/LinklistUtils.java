package com.niluogege.lib.utils;

import com.niluogege.lib.javabean.ListNode;

public class LinklistUtils {

    /**
     * 输出链表内容
     *
     * @param header
     */
    public static void iterateLinkList(ListNode header) {
        if (header != null) {
            ListNode temp = header;
            while (temp != null) {
                System.out.print(temp.val);
                temp = temp.next;
            }
        }
    }

}
