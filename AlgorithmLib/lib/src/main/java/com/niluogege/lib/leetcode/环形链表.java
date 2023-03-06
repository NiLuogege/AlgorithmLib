package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * https://leetcode.cn/problems/linked-list-cycle/?favorite=ex0k24j
 */
public class 环形链表 {

    public static void main(String[] args) {
        ListNode node = LinklistUtils.create();
        //让他成环
        node.next.next.next = node.next;

        System.out.println(hasCycle(node));
    }

    public static boolean hasCycle(ListNode head) {

        if (head==null) return false;

        //一次走两步
        ListNode fast = head;
        //一次走一步
        ListNode slow = head;
        while (slow.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
