package com.niluogege.lib.leetcode;


import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

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
