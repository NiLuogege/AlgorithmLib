package com.niluogege.lib.utils;

import com.niluogege.lib.javabean.ListNode;

public class LinklistUtils {

    public static ListNode create(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n4 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n4;
        n4.next=n6;

        return n1;
    }


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
