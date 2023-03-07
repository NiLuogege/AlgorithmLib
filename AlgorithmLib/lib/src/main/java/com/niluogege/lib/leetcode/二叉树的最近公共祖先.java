package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.TreeNode;
import com.niluogege.lib.utils.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树的最近公共祖先
 * <p>
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/?favorite=ex0k24j
 */
public class 二叉树的最近公共祖先 {

    public static void main(String[] args) {

        TreeNode tree3 = TreeUtils.createTree3();

        TreeNode treeNode = lowestCommonAncestor(tree3, tree3.left.left, tree3.left.right);
        System.out.println(treeNode.val);
    }

    private static Map<Integer, TreeNode> roots = new HashMap<>();
    private static ArrayList<TreeNode> pPath = new ArrayList<>();

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //获取所有子节点和根节点的映射关系
        getRoots(root);

        //向上查找p 点到 根节点路径

        while (p != null) {
            pPath.add(p);
            p = roots.get(p.val);
        }

        //向上查找 q 的子节点，并且和 q 的path中进行对比
        while (q != null) {
            if (pPath.contains(q)) {
                return q;
            }

            q = roots.get(q.val);
        }

        return null;
    }

    /**
     * 获取所有子节点和根节点的映射关系
     */
    public static void getRoots(TreeNode root) {

        if (root.left != null) {
            roots.put(root.left.val, root);
            getRoots(root.left);
        }

        if (root.right != null) {
            roots.put(root.right.val, root);
            getRoots(root.right);
        }

    }

}
