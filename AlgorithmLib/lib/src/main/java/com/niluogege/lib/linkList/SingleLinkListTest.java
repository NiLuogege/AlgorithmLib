package com.niluogege.lib.linkList;

/**
 * 单链表
 */
public class SingleLinkListTest {

    public static void main(String[] args) {


        //测试翻转
//        Node node = craeteLinkList(1);
//        iterateLinkList(node);
//        Node reverseNode = reverseByloop(node);
//        System.out.println("");
//        iterateLinkList(reverseNode);

        //测试merge
        // one=02468
        Node one = craeteLinkList(2);
        iterateLinkList(one);

        System.out.println("");

        //two=0369
        Node two = craeteLinkList(3);
        iterateLinkList(two);

        System.out.println("");

        Node merged = mergeListByRe(one, two);
        iterateLinkList(merged);
    }


    /**
     * 创建一个链表 内容为 0123456789
     *
     * @return
     */
    private static Node craeteLinkList(int diff) {

        Node header = null;
        Node pre = null;
        for (int i = 0; i < 10; i += diff) {
            Node node = new Node(i);
            if (header == null) {
                header = node;
            } else {
                if (pre.next == null) {
                    pre.next = node;
                }
            }
            pre = node;
        }


        return header;
    }

    /**
     * 输出链表内容
     *
     * @param header
     */
    private static void iterateLinkList(Node header) {
        if (header != null) {
            while (header != null) {
                System.out.print(header.value);
                header = header.next;
            }
        }
    }

    /**
     * 链表翻转 - 遍历的方式
     *
     * @return
     */
    private static Node reverseByloop(Node header) {

        if (header == null || header.next == null) {
            return header;
        }

        //前一个节点
        Node preNode = null;
        //下一个节点
        Node nextNode = null;

        while (header != null) {
            // 记录下一个节点
            nextNode = header.next;

            //设置当前节点 的下一个结节 为前一个节点
            header.next = preNode;

            //记录前一个节点为 当前节点
            preNode = header;


            //切换到下一个节点
            header = nextNode;

        }

        return preNode;
    }


    /**
     * 合并两个有序链表 - 递归
     */
    private static Node mergeListByRe(Node one, Node two) {
        if (one == null) return two;
        if (two == null) return one;

        Node head = null;

        if (one.value <= two.value) {
            head = one;
            head.next = mergeListByRe(one.next, two);
        } else {
            head = two;
            head.next = mergeListByRe(one, two.next);
        }
        return head;
    }


}
