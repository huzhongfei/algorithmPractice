package com.example.algorithmpractice.list;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/30
 * desc:二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
class ToothSeq103 {

    /**
     *  广度优先
     *
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if(curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if(curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }

            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }




    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        List<Integer> ceng = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();

        boolean left = true;
        queue.add(root);
        while (!queue.isEmpty() || !deque.isEmpty()) {
            if (left) {
                if(queue.isEmpty())
                    continue;
                TreeNode node = queue.pollLast();
                if(node != null) {
                    ceng.add(node.val);
                    if(node.left != null) {
                        deque.add(node.left);
                    }
                    if(node.right != null) {
                        deque.add(node.right);
                    }
                }

                if(queue.isEmpty()) {
                    left = false;
                    ans.add(new ArrayList<>(ceng));
                    ceng.clear();
                }
            } else {
                if(deque.isEmpty())
                    continue;
                TreeNode node = deque.pollLast();
                if(node != null) {
                    ceng.add(node.val);
                    if(node.right != null) {
                        queue.add(node.right);
                    }
                    if(node.left != null) {
                        queue.add(node.left);
                    }
                }

                if(deque.isEmpty()) {
                    left = true;
                    ans.add(new ArrayList<>(ceng));
                    ceng.clear();
                }
            }
        }

        return ans;
    }
}
