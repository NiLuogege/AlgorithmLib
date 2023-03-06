package com.niluogege.lib.utils;


import com.niluogege.lib.javabean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    public static TreeNode[] createTree() {
        int lenth = 10;

        //创建 lenth 个 树节点
        TreeNode[] node = new TreeNode[lenth];
        for (int i = 0; i < lenth; i++) {
            node[i] = new TreeNode(i);
        }

        // 构建完全二叉树
        for (int i = 0; i < lenth; i++) {

            int leftNodeIndex = i * 2 + 1;
            if (leftNodeIndex < 10) {
                node[i].left = node[leftNodeIndex];
            }

            int rightNodeIndex = i * 2 + 2;
            if (rightNodeIndex < 10) {
                node[i].right = node[rightNodeIndex];
            }

            int fatherNodeIndex = (i - 1) / 2;
            if (i > 0) {
                node[i].father = node[fatherNodeIndex];
            }
        }

        return node;

    }

    public static TreeNode createTree2() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;

        node1.father = node2;
        node3.father = node2;
        node2.father = node4;
        node5.father = node4;

        return node4;
    }

    public static String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                res.append("null,");
            }
        }
        res.deleteCharAt(res.length() - 1);//删除多余的 逗号
        res.append("]");
        return res.toString();
    }

}
