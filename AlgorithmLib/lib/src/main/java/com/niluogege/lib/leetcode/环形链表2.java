package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/?favorite=ex0k24j
 */
public class 环形链表2 {

    public static void main(String[] args) {
        ListNode node = LinklistUtils.create();
        //让他成环
        node.next.next.next = node.next;

        System.out.println(detectCycle(node).val);
    }

    /**
     * 核心思想：当确认成环以后  ，那么再从头结点出发一个指针，下次再和slow相遇 那个就是入环点
     *
     * https://leetcode.cn/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
     */
    public static ListNode detectCycle(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;

        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            slow = slow.next;


            //说明相遇了，那么再从头结点出发一个指针，下次再和slow相遇 那个就是入环点
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }

                return ptr;
            }
        }

        return null;

    }

}
