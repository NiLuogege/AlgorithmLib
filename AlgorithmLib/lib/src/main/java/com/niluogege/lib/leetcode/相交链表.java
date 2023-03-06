package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.ListNode;
import com.niluogege.lib.utils.LinklistUtils;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/?favorite=ex0k24j
 */
public class 相交链表 {

    public static void main(String[] args) {
        //1->4->5->6
        ListNode node = LinklistUtils.create();

        //9->4->5->6
        ListNode n1 = new ListNode(9);
        ListNode n2 = node.next;
        ListNode n4 = node.next.next;
        ListNode n6 = node.next.next.next;
        n1.next = n2;
        n2.next = n4;
        n4.next = n6;

//        System.out.println(getIntersectionNode_me(node,n1).val);
        System.out.println(getIntersectionNode(node, n1).val);

    }

    /**
     * 参考中的第二种核心思想， 类似于 寻找成环链表的入口一样，运用了数学原理
     * <p>
     * https://leetcode.cn/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        ListNode pa = headA, pb = headB;

        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }

        return pa;

    }

    /**
     * 这是我自己的写法，比较暴力 ，直接双层循环对比值
     */
    public static ListNode getIntersectionNode_me(ListNode headA, ListNode headB) {

        ListNode tempA = headA;
        while (tempA != null) {

            ListNode tempB = headB;

            while (tempB != null) {
                if (tempA.equals(tempB)) {
                    return tempA;
                }

                tempB = tempB.next;
            }

            tempA = tempA.next;
        }

        return null;
    }

}
