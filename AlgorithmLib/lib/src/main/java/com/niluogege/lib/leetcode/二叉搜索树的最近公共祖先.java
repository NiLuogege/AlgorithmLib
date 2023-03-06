package com.niluogege.lib.leetcode;

import com.niluogege.lib.javabean.TreeNode;

import java.util.ArrayList;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/?favorite=ex0k24j
 */
public class 二叉搜索树的最近公共祖先 {

    public static void main(String[] args) {

    }

    /**
     * 核心思想：先获取 p，q 到根节点的距离，然后 在从根节点向下查找 分叉点
     * <p>
     * <p>
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/?favorite=ex0k24j
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        ArrayList<TreeNode> path_p = getPath(root, p);
        ArrayList<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            }else {
                break;
            }
        }

        return ancestor;

    }

    public static ArrayList<TreeNode> getPath(TreeNode root, TreeNode target) {
        ArrayList<TreeNode> path = new ArrayList<>();

        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (node.val > target.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        path.add(node);
        return path;
    }
}
