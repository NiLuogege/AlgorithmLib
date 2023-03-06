package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

import java.util.Arrays;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class 合并K个升序链表 {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n4 = new ListNode(5);
        n1.next = n2;
        n2.next = n4;

        ListNode n11 = new ListNode(1);
        ListNode n3 = new ListNode(3);
        ListNode n41 = new ListNode(4);
        n11.next = n3;
        n3.next = n41;

        ListNode n22 = new ListNode(2);
        ListNode n6 = new ListNode(6);
        n22.next = n6;

        LinklistUtils.iterateLinkList(n1);
        System.out.println("");
        LinklistUtils.iterateLinkList(n11);
        System.out.println("");
        LinklistUtils.iterateLinkList(n22);

        System.out.println("");
        LinklistUtils.iterateLinkList(mergeKLists(new ListNode[]{n1, n11, n22}));
    }

    /**
     * 核心思想就是 基于 两个有序链表的合并操作。
     *
     * https://leetcode.cn/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
     */
    public static ListNode mergeKLists(ListNode[] lists) {

        ListNode current = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode node = lists[i];
            current = merge(current, node);
        }

        return current;
    }

    public static ListNode merge(ListNode one, ListNode two) {

        if (one == null) {
            return two;
        } else if (two == null) {
            return one;
        } else if (one.val < two.val) {
            one.next = merge(one.next, two);
            return one;
        } else {
            two.next = merge(one, two.next);
            return two;
        }

    }
}
