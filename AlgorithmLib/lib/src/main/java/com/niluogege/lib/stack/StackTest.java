package com.niluogege.lib.stack;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {

//        MinStack minStack = new MinStack();
//        minStack.push(5);
//        minStack.push(2);
//        minStack.push(10);
//
//        int top = minStack.top();
//        int min = minStack.getMin();
//        int pop = minStack.pop();
//        System.out.println("top= " + top + " min= " + min + " pop= " + pop);

        //------验证栈序列-------

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {4, 5, 3, 1, 2};
        boolean stackSequences = validateStackSequences(arr1, arr2);
        System.out.println("stackSequences= "+stackSequences);
    }


    /**
     * 题目：验证栈序列
     * 参考：https://leetcode-cn.com/problems/validate-stack-sequences/solution/yan-zheng-zhan-xu-lie-by-leetcode/
     * 思路：模拟 pop 的过程
     */
    private static boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }


    /**
     * 题目：定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的 min 函数
     * 参考：https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/solution/mian-shi-ti-30-bao-han-minhan-shu-de-zhan-fu-zhu-z/
     * <p>
     * 思路：因为改题目中 限制了 获取最小元素的 时间复杂度为 O(1) 所以采用，以后空间换时间的方法来做，
     * 同时定义两个栈，一个栈中专门存放 最小元素，另一个存放原数据
     */
    static class MinStack {

        Stack<Integer> originStrack = new Stack<>();
        Stack<Integer> minStrack = new Stack<>();

        public void push(int value) {
            if (originStrack.isEmpty()) {
                originStrack.push(value);
                minStrack.push(value);
            } else {
                Integer pop = minStrack.pop();
                if (pop > value) {
                    minStrack.push(value);
                } else {
                    minStrack.push(pop);
                }
                originStrack.push(value);
            }
        }

        public int pop() {
            minStrack.pop();
            return originStrack.pop();
        }

        public int top() {
            return originStrack.peek();
        }

        public int getMin() {
            return minStrack.peek();
        }

    }
}
