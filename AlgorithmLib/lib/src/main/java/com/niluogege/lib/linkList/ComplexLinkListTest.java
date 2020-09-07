package com.niluogege.lib.linkList;

import java.util.HashMap;

/**
 * 复杂链表
 */
public class ComplexLinkListTest {

    private static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int value) {
            this.val = value;
        }
    }


    public static void main(String[] args) {

    }


    /**
     * 题目：复杂链表的复制
     * 参考：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/solution/tong-guo-hashmaplai-shi-xian-by-try-62/
     * 思路：
     * 0. 复制的意思是 深拷贝，就是 创建新的对象
     * 1. 使用 HashMap记录 新旧 节点的映射
     * 2. 通过 map的映射关系 整理新链表节点间关系
     */
    private static Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();

        Node temp = head;

        while (temp != null) {
            map.put(temp, new Node(temp.val));//创建新节点，并创建映射关系
            temp = temp.next;
        }

        temp = head;
        while (temp != null) {//通过 map的映射关系 ，整理新链表节点间关系
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }

        //返回链表头
        return map.get(head);
    }
}
