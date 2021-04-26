package com.example.algorithmpractice.list;

import java.util.ArrayList;
import java.util.List;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/25
 * desc:897. 递增顺序搜索树
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 *         链接：https://leetcode-cn.com/problems/increasing-order-search-tree/solution/di-zeng-shun-xu-cha-zhao-shu-by-leetcode-dfrr/
 */
public class IncreaseSearchTree {
    private TreeNode resNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        return dummyNode.right;


//        List<TreeNode> ans = new ArrayList<>();
//        searchMidd(root, ans);
//
//        TreeNode dummyNode = new TreeNode(-1);
//        TreeNode currNode = dummyNode;
//        for (TreeNode node : ans) {
//            currNode.right = new TreeNode(node.val);
//            currNode = currNode.right;
//        }
//        return dummyNode.right;

    }


    public void inorder(TreeNode node) {
        if(node == null)
            return;

        inorder(node.left);
        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }

    public void searchMidd(TreeNode root, List<TreeNode> ans) {
        if(root == null) {
            return;
        }
        searchMidd(root.left, ans);
        ans.add(root);
        searchMidd(root.right, ans);

    }

    public static void main(String[] args) {
       IncreaseSearchTree tree = new IncreaseSearchTree();
       TreeNode node1 = new TreeNode(1);
       TreeNode node2 = new TreeNode(2, node1, null);
       TreeNode node4 = new TreeNode(4);
       TreeNode node3 = new TreeNode(3, node2, node4);
       TreeNode node7 = new TreeNode(7);
       TreeNode node9 = new TreeNode(9);
       TreeNode node8 = new TreeNode(8, node7, node9);
       TreeNode node6 = new TreeNode(6, null, node8);
       TreeNode node5 = new TreeNode(5, node3, node6);

       tree.increasingBST(node5);

    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
