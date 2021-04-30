package com.example.algorithmpractice.list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/27
 * desc: 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 */
public class TreeSum938 {

    public int rangeSumBST(TreeNode root, int low, int high) {
        // 深度优先
        if(root == null) {
            return 0;
        }
        if(root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        if(root.val <low) {
            return rangeSumBST(root.right, low, high);
        }

        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    /**
     *  广度优先
     *  思路：用一个队列 q 存储需要计算的节点，每次取出队首节点时，若节点为空则跳过该节点，
     *  否则按方法一种给出的大小关系来决定加入队列的子节点。
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST2(TreeNode root, int low, int high) {
        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if(node == null) {
                continue;
            }
            if(node.val > high) {
                q.offer(node.left);
            } else if (node.val < low) {
                q.offer(node.right);
            } else {
                sum += node.val;
                q.offer(node.left);
                q.offer(node.right);
            }
        }

        return sum;

    }

}
