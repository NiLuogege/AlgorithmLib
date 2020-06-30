package com.niluogege.lib.binaryTree;


/**
 * 二叉树
 */
public class BinaryTreeTest {
    public static void main(String[] args) {


    }

    private static TreeNode[] createTree() {
        int lenth = 10;

        //创建 lenth 个 树节点
        TreeNode[] node = new TreeNode[lenth];
        for (int i = 0; i < lenth; i++) {
            node[i] = new TreeNode(i);
        }

        //
        for (int i = 0; i < lenth; i++) {

            int leftNodeIndex = i * 2 + 1;
            if (leftNodeIndex < 10) {
                node[i].left = node[leftNodeIndex];
            }

            int rightNodeIndex = i * 2 + 2;
            if (rightNodeIndex < 10) {
                node[i].right = node[rightNodeIndex];
            }
        }

        return node;

    }


    /**
     * 前序遍历递归实现
     */
    public static void preOrderRe() {

    }
}
