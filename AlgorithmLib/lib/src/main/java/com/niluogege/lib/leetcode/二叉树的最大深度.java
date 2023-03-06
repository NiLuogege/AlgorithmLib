package com.niluogege.lib.leetcode;


import com.niluogege.lib.javabean.TreeNode;
import com.niluogege.lib.utils.TreeUtils;

import java.util.LinkedList;

/*
  给定一个二叉树，找出其最大深度。
  二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 
  https://leetcode.cn/problems/maximum-depth-of-binary-tree/?favorite=ex0k24j
 **/
public class 二叉树的最大深度 {

    public static void main(String[] args) {
        TreeNode trees = TreeUtils.createTree2();
        System.out.println(TreeUtils.serialize(trees));


//        System.out.println(maxDepth_me(trees));
        System.out.println(maxDepth(trees));
    }


    /**
     * 核心思想： 使用递归，算出每个子树的高度，最终的高度就是 子树高度+1
     *
     * https://leetcode.cn/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/
     */
    public static int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }else {
            int leftDepth = maxDepth(root.left);
            int rightDepth=maxDepth(root.right);
            return Math.max(leftDepth,rightDepth)+1;
        }
    }

    /**
     * 这是我的，我感觉对着呢，但是 LeetCode报错，估计有问题
     */
    public static int maxDepth_me(TreeNode root) {
        if (root==null) return 0;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>(){{
            add(root);
        }};

        int deep=0;

        int doubleCount=0;

        while (!queue.isEmpty()){
            deep++;
            TreeNode pop = queue.pop();
            if (pop.left!=null) queue.push(pop.left);
            if (pop.right!=null) queue.push(pop.right);

            if (pop.left!=null && pop.right!=null){
                doubleCount++;
            }
        }

        return deep-doubleCount;
    }



}
