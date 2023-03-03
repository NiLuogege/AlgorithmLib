package com.niluogege.lib.leetcode;


import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 两数相加 {

    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(8);
        ListNode n3 = new ListNode(8);
        n1.next = n2;
        n2.next = n3;

        ListNode l1 = n1;
        ListNode l2 = n1;

        ListNode result = addTwoNumbers(l1, l2);
        LinklistUtils.iterateLinkList(result);

    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //创建虚拟头结点，方便操作
        ListNode pre = new ListNode(0);
        ListNode cur = pre;

        //用于记录进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;

            cur.next = new ListNode(sum);

            cur = cur.next;

            if (l1 != null)
                l1 = l1.next;

            if (l2 != null)
                l2 = l2.next;

        }

        //说明最后一次相加 有进位所以要补一位
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }

        return pre.next;
    }

    /**
     * 我的算法 和标准答案的算法差不多，不过我的有以下几个可以优化的点
     * 1. 可以先不用计算两个 链表长度，可以 同时遍历两个链表，如果长度不够直接按0计算就行了
     */
    private static ListNode addTwoNumbers_me(ListNode l1, ListNode l2) {
        //获取链表最大长度
        int list1Length = getNodeLength(l1);
        int list2Length = getNodeLength(l2);

        int resultLength = Math.max(list1Length, list2Length);

        //创建结果链表
        ListNode resultList = null;
        ListNode current = null;
        for (int i = 0; i < resultLength; i++) {
            ListNode n = new ListNode(0);
            if (resultList == null) {
                resultList = n;
                current = resultList;
            } else {
                current.next = n;
                current = n;
            }
        }

        ListNode currentNode = resultList;
        ListNode currentList1Node = l1;
        ListNode currentList2Node = l2;
        int ext = 0;
        //变量结果链表
        while (currentNode != null) {
            int list1Value = 0;
            int list2Value = 0;
            if (currentList1Node != null) {
                list1Value = currentList1Node.val;
            }

            if (currentList2Node != null) {
                list2Value = currentList2Node.val;
            }

            int addValue = list1Value + list2Value + ext;
            if (addValue == 10) {
                ext = 1;
                addValue = 0;
            } else if (addValue > 10) {
                ext = 1;
                addValue = addValue % 10;
            } else {
                ext = 0;
            }
            currentNode.val = addValue;

            ListNode temp = currentNode.next;
            //说明最后还进位了，要补一位
            if (temp == null && ext == 1) {
                temp = new ListNode(0);
                currentNode.next = temp;
            }
            currentNode = currentNode.next;


            if (currentList1Node != null) {
                currentList1Node = currentList1Node.next;
            }
            if (currentList2Node != null) {
                currentList2Node = currentList2Node.next;
            }


        }

        return resultList;
    }

    private static int getNodeLength(ListNode node) {
        int length = 0;
        ListNode current = node;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }


}
