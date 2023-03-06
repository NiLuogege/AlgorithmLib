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
        LinklistUtils.iterateLinkList(rotateRight_me(n1, 2));
    }

    public static ListNode rotateRight(ListNode head, int k) {

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

        //前一个节点
        ListNode pre = header;
        //当前节点
        ListNode cur = null;

        while (pre != null) {
            //使用临时遍历 记录下一个节点
            ListNode t = pre.next;

            //指针翻转
            pre.next = cur;

            //指针移动
            cur = pre;
            //指针移动
            pre = t;
        }

        return cur;
    }
}
