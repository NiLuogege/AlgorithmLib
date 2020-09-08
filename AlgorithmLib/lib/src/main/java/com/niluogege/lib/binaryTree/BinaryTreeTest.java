package com.niluogege.lib.binaryTree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
//        postOrderRe(tree[0]);

        //查找下一个点
//        TreeNode tree5 = tree[5];
//        TreeNode treeNode = inorderSuccessor(tree5);
//        System.out.println("tree5= "+tree5.value+" treeNode= "+treeNode.value);

        //镜像
//        mirror(tree[0]);

        //从上往下打印二叉树
//        int[] ints = levelOrder(tree[0]);
//        for (int anInt : ints) {
//            System.out.println(anInt);
//        }


        //把二叉树打印成多行
//        List<List<Integer>> lists = levelOrder2(tree[0]);
//        for (List<Integer> list : lists) {
//            System.out.println(list.toString());
//        }

        //把二叉树 以Z字型 打印成多行
//        List<List<Integer>> lists = levelOrder3(tree[0]);
//        for (List<Integer> list : lists) {
//            System.out.println(list.toString());
//        }

        //二叉搜索树的后序遍历序列
//        int[] searchTree = createSearchTree();
//        boolean postorder = verifyPostorder(searchTree);
//        System.out.println("postorder= " + postorder);

        //二叉树中和为某一值的路径
//        List<List<Integer>> lists = pathSum(createTree()[0], 11);
//        System.out.println("lists= " + lists.toString());

        TreeNode searchTree = createSearchTree2();
//        midOrderRe(searchTree);
        TreeNode node = treeToDoublyList(searchTree);
//        midOrderRe(node);
    }


    /**
     * 返回一个二叉搜索树
     *
     * @return
     */
    private static int[] createSearchTree() {
        return new int[]{1, 3, 2, 6, 5};
    }

    /**
     * 返回一个二叉搜索树
     *
     * @return
     */
    private static TreeNode createSearchTree2() {

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

            int fatherNodeIndex = (i - 1) / 2;
            if (i > 0) {
                node[i].father = node[fatherNodeIndex];
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
            System.out.print(rootNode.val);
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
            System.out.print(rootNode.val);

            if (rootNode.right != null) {
                midOrderRe(rootNode.right);
            }
        }
    }

    /**
     * 后序遍历 递归实现
     * <p>
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

            System.out.print(rootNode.val);
        }
    }


    /**
     * 给定一棵二叉树的其中一个节点，请找出中序遍历序列的下一个节点
     * 参考：https://www.acwing.com/solution/content/714/
     * 思路：
     * 1. 因为是 中序遍历 ，所以 遍历方向是从下往上，符合 左跟右的规则
     * <p>
     * 分析：
     * 1. 如果当前节点有 右儿子，那么下一个节点就是 右子树的最左节点
     * 2. 如果当前节点没有右子树，那么 期父节点的父节点就是 它的后继(还是在左子树上找)
     */
    private static TreeNode inorderSuccessor(TreeNode p) {
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (p.father != null) {
            if (p.father.left == p) {
                return p.father;
            }
            p = p.father;
        }

        return null;
    }


    /**
     * 题目：操作给定的二叉树，将其变换为源二叉树的镜像
     * 参考：https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking
     * https://liweiwei1419.github.io/sword-for-offer/27-%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E9%95%9C%E5%83%8F/
     * <p>
     * 思路：其实就是将二叉树的左右 节点进行交换, 这是使用 前序遍历进行
     */
    private static void mirror(TreeNode root) {

        if (root == null) {
            return;
        }

        //这里对 足有节点进行交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;


        if (root.left != null)
            mirror(root.left);

        if (root.right != null)
            mirror(root.right);


    }


    /**
     * 题目：判断一棵二叉树是不是对称的
     * 参考：题目看：https://www.acwing.com/problem/content/description/38/
     * 题解看：https://liweiwei1419.github.io/sword-for-offer/28-%E5%AF%B9%E7%A7%B0%E7%9A%84%E4%BA%8C%E5%8F%89%E6%A0%91/
     * <p>
     * 思路：使用递归判断，左子树的左孩子等于右子树的右孩子 切 左子树的右孩子等于右子树的左孩子，并且值相等
     */
    private static boolean isSymmetrical(TreeNode pRoot) {

        if (pRoot == null) {
            return true;
        }

        return isCommon(pRoot.left, pRoot.right);
    }

    private static boolean isCommon(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left != null && right != null) {
            return left.val == right.val && isCommon(left.right, right.left) && isCommon(left.left, right.right);
        }

        return false;
    }


    /**
     * 题目：从上往下打印二叉树
     * 参考：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/solution/mian-shi-ti-32-i-cong-shang-dao-xia-da-yin-er-ch-4/
     * 思路：
     * 1.题目要求的二叉树的 从上至下 打印（即按层打印），又称为二叉树的 广度优先搜索（BFS）。
     * 2. BFS 通常借助 队列 的先入先出特性来实现。
     */
    private static int[] levelOrder(final TreeNode root) {
        if (root == null) return new int[]{};

        Queue<TreeNode> queue = new LinkedList<TreeNode>() {{
            add(root);
        }};

        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;

    }

    /**
     * 题目：把二叉树打印成多行
     * 参考：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/solution/mian-shi-ti-32-ii-cong-shang-dao-xia-da-yin-er-c-5/
     * 思路：整体思路和上面一题差不多，只不过是每次打印之前将 队列中的所有元素都拿出来
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                ans.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
            res.add(ans);
        }


        return res;
    }


    /**
     * 题目：把二叉树 以Z字型 打印成多行
     * 参考：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/solution/mian-shi-ti-32-iii-cong-shang-dao-xia-da-yin-er--3/
     * 思路：
     * 1. 一样上面一题的思路只需要在插入时 对奇数行和偶数行做不同操作就可以
     * 2. 使用 LinkedList 既可以插入头部又可以插入尾部的 特性，来替代 ArrayList
     */
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) return new ArrayList<>();


        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> ans = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (res.size() % 2 == 0) {
                    ans.addLast(node.val);
                } else {
                    ans.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }

            res.add(ans);
        }


        return res;
    }


    /**
     * 题目：二叉搜索树的后序遍历序列
     * 参考：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
     * 思路：
     * 1. 二叉搜索树定义： 左子树中所有节点的值 << 根节点的值；右子树中所有节点的值 >> 根节点的值；其左、右子树也分别为二叉搜索树。
     * 2. 根节点就是 数组的最后一位
     */
    private static boolean verifyPostorder(int[] postorder) {

        return recur(postorder, 0, postorder.length - 1);
    }

    private static boolean recur(int[] postorder, int i, int j) {
        if (i >= j) return true;
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int m = p; //m是第一个大于根的节点 ，也就是左右子树的界限
        while (postorder[p] > postorder[j]) p++;
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }


    static LinkedList<List<Integer>> res = new LinkedList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    /**
     * 题目：二叉树中和为某一值的路径
     * 参考：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/mian-shi-ti-34-er-cha-shu-zhong-he-wei-mou-yi-zh-5/
     * 思路：
     * 1. 使用回溯思想
     */
    private static List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }

    private static void recur(TreeNode root, int tar) {
        if (root == null) return;

        path.add(root.val);

        tar -= root.val;

        if (tar == 0 && root.right == null && root.left == null) {//因为要从 根节点 一直到 叶子节点所以 要判断 root.right==null&&root.left==null
            res.add(new LinkedList<Integer>(path));
        }

        recur(root.left, tar);
        recur(root.right, tar);

        path.removeLast();//在向上回溯前，需要将当前节点删除掉
    }

    private static TreeNode pre, head;

    /**
     * 题目：二叉搜索树 转为 双向链表
     * 参考：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solution/mian-shi-ti-36-er-cha-sou-suo-shu-yu-shuang-xian-5/
     * 思路：
     * 1. 中序遍历保证 顺序
     * 2. 中序遍历过程中修改引用指向
     */
    private static TreeNode treeToDoublyList(TreeNode root) {

        if (root == null) return null;

        dfs(root);

        head.left = pre;
        pre.right = head;

        return head;
    }

    private static void dfs(TreeNode cur) {
        if (cur == null) return;

        dfs(cur.left);

        if (pre == null) {
            head = cur;//找到最小的节点，记录为 头结点
        } else {
            pre.right = cur;
        }

        cur.left = pre;
        pre = cur;

        dfs(cur.right);

    }
}

