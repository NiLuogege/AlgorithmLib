package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.linkList.Node;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * https://leetcode.cn/problems/rotate-list/?favorite=ex0k24j
 */
public class 旋转链表 {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        LinklistUtils.iterateLinkList(n1);
        System.out.println("");

//        LinklistUtils.iterateLinkList(rotateRight_me(n1, 2));
//        LinklistUtils.iterateLinkList(rotateRight_me(n1, 2));
        LinklistUtils.iterateLinkList(reverseByloop(n1));
    }

    public static ListNode rotateRight(ListNode head, int k) {
return null;
    }


    /**
     * 我这种方式没有考虑到 向右挪的的位数 大于链表长度的情况
     */
    public static ListNode rotateRight_me(ListNode head, int k) {


        int deep = 1;

        ListNode temp1 = head;
        ListNode last = null;
        while (temp1.next != null) {
            deep++;
            temp1 = temp1.next;
            if (temp1.next == null) {
                last = temp1;
            }
        }

        if (k >= deep) {
            return reverseByloop(head);
        }

        ListNode pre = null;
        ListNode current = head;
        for (int i = 0; i < deep - k; i++) {
            current = current.next;
            if (i == deep - k - 2) {
                pre = current;
            }
        }

        last.next = head;
        current.next = last;
        pre.next = null;

        return current;
    }


    private static ListNode reverseByloop(ListNode header) {

        if (header == null || header.next == null) {
            return header;
        }


        //上一个指针
        ListNode pre = null;
        //当前指针
        ListNode cur = header;

        while (cur != null) {
            //记录下一个点
            ListNode t = cur.next;

            //翻转
            cur.next = pre;

            //指针挪动
            pre=cur;
            //指针挪动
            cur = t;
        }

        //pre 最终就指向的 是原来的最后一个节点，翻转后的第一个节点
        return pre;
    }
}
