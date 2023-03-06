package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.TreeNode;

import java.util.LinkedList;

/**
 * 定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?favorite=ex0k24j
 */
public class 二叉搜索树中第K小的元素 {

    public static void main(String[] args) {

    }

    /**
     *核心思想,参考下面链接的 方法1
     *
     * 首先搜索二叉树有如下几个特点
     *  - 结点的左子树只包含小于当前结点的数。
     *  - 结点的右子树只包含大于当前结点的数。
     *  - 所有左子树和右子树自身必须也是二叉搜索树
     *
     * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/
     */
    public static int kthSmallest(TreeNode root, int k) {

        LinkedList<TreeNode> nodes = new LinkedList<>();

        while (root != null || !nodes.isEmpty()) {

            //找到所有的左节点
            while (root != null) {
                nodes.push(root);
                root = root.left;
            }

            root = nodes.pop();
            --k;
            if (k == 0) {
                break;
            }

            //root 的左节点搞完了 再高右节点
            root = root.right;
        }

        return root.val;

    }

}
