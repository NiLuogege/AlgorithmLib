package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * https://leetcode.cn/problems/sort-list/?favorite=ex0k24j
 */
public class 排序链表 {

    public static void main(String[] args) {
        ListNode node = LinklistUtils.create();
        //让他成环
        node.next.next.next.next = new ListNode(2);
        LinklistUtils.iterateLinkList(node);

        System.out.println("");
        LinklistUtils.iterateLinkList(sortList(node));
    }

    /**
     * 核心思想：
     * 1. 快慢指针找到 链表的中点
     * 2. 从中点拆分为两个 链表，然后对两个进行排序
     * 3. 合并两个有序链表，这个是会的，其实就是一个单独的题
     * <p>
     * https://leetcode.cn/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     */
    public static ListNode sortList(ListNode head) {

        return sortList(head, null);

    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}

