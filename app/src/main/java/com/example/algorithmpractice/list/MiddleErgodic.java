package com.example.algorithmpractice.list;

import java.util.ArrayList;
import java.util.List;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/25
 * desc: 中序遍历
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *
 */
class MiddleErgodic {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        search(root, ans);
        return ans;
    }

    public void search(TreeNode node, List<Integer> ans) {
        if(node == null) {
            return;
        }

        search(node.left, ans);
        ans.add(node.val);
        search(node.right, ans);
    }
}
