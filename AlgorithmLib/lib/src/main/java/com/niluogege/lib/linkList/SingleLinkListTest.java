package com.niluogege.lib.linkList;

import java.util.Stack;

/**
 * 单链表
 */
public class SingleLinkListTest {

    public static void main(String[] args) {


        //测试翻转
        Node node = craeteLinkList(1);
        iterateLinkList(node);
        Node reverseNode = reverseByloop(node);
        System.out.println("");
        iterateLinkList(reverseNode);

        //--------------------------

        //测试merge
        // one=02468
//        Node one = craeteLinkList(2);
//        iterateLinkList(one);
//
//        System.out.println("");
//
//        //two=0369
//        Node two = craeteLinkList(3);
//        iterateLinkList(two);
//
//        System.out.println("");
//
//        Node merged = mergeListByRe(one, two);
//        iterateLinkList(merged);

        //--------------------------

        //测试从尾到头打印
//        Node node = craeteLinkList(1);
//        //使用栈
////        printListFromTailToHeadWithStack(node);
//        //使用回溯思想
//        printListFromTailToHeadWithBacktracking(node);


        //--------------------------

        //测试删除重复值节点
//        Node node = craeteLinkList(1);
//        iterateLinkList(node);
//        System.out.println("\n");
//        Node deleteDuplicates = deleteDuplicates(node);
//        iterateLinkList(deleteDuplicates);


        //--------------------------
        //测试倒数第K个节点

        //链表 内容为 0123456789
//        Node node = craeteLinkList(1);
//        Node kth = findKthToTail(node, 6);
//        System.out.println("倒数第K个节点= " + kth.value);


        //--------------------------
//        //链表中环的入口结点
//        Node node = craeteLinkList(1);
//        //检验成环
////        iterateLinkList(node);
//
//        Node entryNodeOfLoop = entryNodeOfLoop(node);
//        System.out.println("入口节点= " + entryNodeOfLoop.value);
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
//            if (i == 2 || i == 1) {
//                node = new Node(0);
//            }
//
//            if (i == 7) {
//                node = new Node(6);
//            }

            if (header == null) {
                header = node;
            } else {
                if (pre.next == null) {
                    pre.next = node;
                }

                // TODO: 2020/8/27 这里进行成环，其他用例要删掉
//                if (i == 9) {// 9 指向 3 成环
//                    node.next = header.next.next.next;
//                }

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
     * <p>
     * 参考：
     * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/fan-zhuan-lian-biao-yi-dong-de-shuang-zhi-zhen-jia/ 中的动图
     * <p>
     * 思路：局部反转，然后在移动 翻转完以后 整个链表的翻转也就完成了
     *
     * @return
     */
    private static Node reverseByloop(Node header) {

        if (header == null || header.next == null) {
            return header;
        }

        //前一个节点
        Node pre = header;
        //当前节点
        Node cur = null;

        while (pre != null) {
            //使用临时遍历 记录下一个节点
            Node t=pre.next;

            //指针翻转
            pre.next=cur;

            //指针移动
            cur=pre;
            //指针移动
            pre=t;
        }

        return cur;
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


    /**
     * 从尾到头打印链表 1
     * <p>
     * 思路 用栈来辅助实现
     */
    private static void printListFromTailToHeadWithStack(Node list) {

        Stack<Integer> stack = new Stack<>();
        while (list != null) {
            stack.push(list.value);
            list = list.next;
        }

        while (stack != null) {
            if (!stack.isEmpty())
                System.out.println("pop = " + stack.pop());
        }

    }

    /**
     * 从尾到头打印链表 2
     * <p>
     * 思路 使用回溯思想
     */
    private static void printListFromTailToHeadWithBacktracking(Node list) {
        if (list.next != null) {
            //写在这里就是正序
            printListFromTailToHeadWithBacktracking(list.next);
        }
        //写在这里就是倒序
        System.out.println(list.value);
    }


    /**
     * 题目：给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * 参考：https://liweiwei1419.github.io/sword-for-offer/18-%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E4%B8%AD%E9%87%8D%E5%A4%8D%E7%9A%84%E7%BB%93%E7%82%B9/
     * 思路：因为 头结点 可能会被删除所以要搞一个 虚拟头结点
     * <p>
     * <p>
     * 1->2->2->2->3
     */
    private static Node deleteDuplicates(Node head) {
        //因为头结点可能会被删掉 所以创建 虚拟头结点
        Node dummyNode = new Node(-1);
        dummyNode.next = head;
        Node currentNode = dummyNode;
        while (currentNode.next != null && currentNode.next.next != null) {

            if (currentNode.next.value == currentNode.next.next.value) {//重复数组节点
                Node delNode = currentNode.next.next;//记录要删除的节点
                while (delNode.next.value == currentNode.next.value) {//如果后面还有重复的节点 一并删掉
                    delNode = delNode.next;//向后移 要删除的节点
                }
                currentNode.next = delNode.next;
                delNode.next = null;
            } else {
                currentNode = currentNode.next;
            }

        }

        return dummyNode.next;
    }

    /**
     * 题目： 输入一个链表，输出该链表中倒数第 k 个结点。
     * 参考：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solution/mian-shi-ti-22-lian-biao-zhong-dao-shu-di-kge-j-11/
     * 思路：使用双指针法 ，先移动前指针 和 后指针的 距离为 k, 然后在 同时移动前后指针，知道前指针指向 null ,这时 后指针指向的就是 倒数第 n 个节点，
     * 这个思路 只需要一遍遍历 就可以，一般的做法 都需要两边遍历
     *
     * @param head ： 头结点
     * @param k    ： 步长
     * @return
     */
    private static Node findKthToTail(Node head, int k) {

        Node befor = head;
        Node after = head;
        for (int i = 1; i < k; i++) {
            befor = befor.next;
        }

        while (befor.next != null) {
            befor = befor.next;
            after = after.next;
        }
        return after;
    }


    /**
     * 题目：链表中环的入口结点
     * 参考：https://www.acwing.com/solution/content/741/
     * https://www.acwing.com/solution/content/7475/
     * <p>
     * 思路 ：
     * 1. 第一次相遇时 快节点 走了 慢节点的 两倍。
     * 2. 环的周长是 x + y (参考 https://www.acwing.com/solution/content/741/ 中图)
     * <p>
     * 如下是我按照 思路写的 代码，和 参考答案不一样
     */
    private static Node entryNodeOfLoop(Node head) {

        Node fast = head;
        Node slow = head;


        while (slow.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                slow = head;
                break;
            }
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }
}
