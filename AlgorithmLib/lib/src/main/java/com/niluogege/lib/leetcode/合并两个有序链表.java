package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * https://leetcode.cn/problems/merge-two-sorted-lists/?favorite=ex0k24j
 */
public class 合并两个有序链表 {

    public static void main(String[] args) {
//        l1 = [1,2,4], l2 = [1,3,4]

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n4;

        ListNode n11 = new ListNode(1);
        ListNode n3 = new ListNode(3);
        ListNode n41 = new ListNode(4);
        n11.next = n3;
        n3.next = n41;

        LinklistUtils.iterateLinkList(n1);
        System.out.println("");
        LinklistUtils.iterateLinkList(n11);

        System.out.println("");
        LinklistUtils.iterateLinkList(mergeTwoLists(n1, n11));
    }

    /**
     * 递归，每次都去找 小的那个
     *
     * https://leetcode.cn/problems/merge-two-sorted-lists/solution/yi-kan-jiu-hui-yi-xie-jiu-fei-xiang-jie-di-gui-by-/
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }

    }

}
