package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * https://leetcode.cn/problems/reverse-linked-list/?favorite=ex0k24j
 */
public class 反转链表 {

    public static void main(String[] args) {
        //1->4->5->6
        ListNode node = LinklistUtils.create();
        LinklistUtils.iterateLinkList(node);
        System.out.println("");
        LinklistUtils.iterateLinkList(reverseList(node));


    }

    public static ListNode reverseList(ListNode head) {

        ListNode pre =null;
        ListNode cur= head;

        while (cur!=null){
            ListNode next = cur.next;
            cur.next=pre;
            pre=cur;
            cur= next;
        }

        //pre 最终回我头结点
        return pre;
    }
}
