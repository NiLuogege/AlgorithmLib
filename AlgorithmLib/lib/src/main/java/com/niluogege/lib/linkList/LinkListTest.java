package com.niluogege.lib.linkList;

/**
 * 链表
 */
public class LinkListTest {

    public static void main(String[] args) {


        Node node = craeteLinkList();
        iterateLinkList(node);

    }


    /**
     * 创建一个链表 内容为 0123456789
     *
     * @return
     */
    private static Node craeteLinkList() {

        Node header = null;
        Node pre = null;
        for (int i = 0; i < 10; i++) {
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

}
