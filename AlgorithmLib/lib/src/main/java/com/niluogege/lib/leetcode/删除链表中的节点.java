package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * 删除链表中的节点
 *
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/?favorite=ex0k24j
 */
public class 删除链表中的节点 {

    public static void main(String[] args) {
        ListNode node = LinklistUtils.create();
        LinklistUtils.iterateLinkList(node);

        System.out.println("");

        deleteNode(node.next);
        LinklistUtils.iterateLinkList(node);
    }

    /**
     * 核心思想：绝了
     * 题目是要求 删除 给定node，因为题目要求我们无法访问头结点 ，所以无法使用前一个节点执行期下一个节点的这种操作
     *
     * 这道题的思想史，直接使用下一个节点的内容覆盖给定节点就行了，确实牛，怎么感觉在做竞猜题一样
     */
    public static void deleteNode(ListNode node) {

        node.val=node.next.val;
        node.next=node.next.next;
    }

}
