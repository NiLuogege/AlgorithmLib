package com.niluogege.lib.binaryTree;


/**
 * 二叉树
 */
public class BinaryTreeTest {

    public static void main(String[] args) {

        TreeNode[] tree = createTree();

        //前序递归遍历
//        preOrderRe(tree[0]);

        //中序递归遍历
//        midOrderRe(tree[0]);

        //后序递归遍历
        postOrderRe(tree[0]);
    }

    /**
     * 创建一个 完全二叉树 树的形状如下图
     * <p>
     * --------0
     * ----1        2
     * --3   4    5   6
     * 7   8   9
     *
     * @return
     */
    private static TreeNode[] createTree() {
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
        }

        return node;

    }


    /**
     * 前序遍历 递归实现
     * <p>
     * 正确结果应该是  0137849256
     */
    private static void preOrderRe(TreeNode rootNode) {
        if (rootNode != null) {
            System.out.print(rootNode.value);
            if (rootNode.left != null) {
                preOrderRe(rootNode.left);
            }

            if (rootNode.right != null) {
                preOrderRe(rootNode.right);
            }
        }
    }


    /**
     * 中序遍历 递归实现
     * <p>
     * 正确结果应该是：7381940526
     */
    private static void midOrderRe(TreeNode rootNode) {
        if (rootNode != null) {
            if (rootNode.left != null) {
                midOrderRe(rootNode.left);
            }
            System.out.print(rootNode.value);

            if (rootNode.right != null) {
                midOrderRe(rootNode.right);
            }
        }
    }

    /**
     * 后序遍历 递归实现
     *
     * 正确结果应该是：7839415620
     */
    private static void postOrderRe(TreeNode rootNode) {
        if (rootNode != null) {
            if (rootNode.left != null) {
                postOrderRe(rootNode.left);
            }

            if (rootNode.right != null) {
                postOrderRe(rootNode.right);
            }

            System.out.print(rootNode.value);
        }
    }

}
