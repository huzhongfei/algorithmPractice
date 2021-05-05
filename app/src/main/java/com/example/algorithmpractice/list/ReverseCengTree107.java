package com.example.algorithmpractice.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by hufei on 2021/5/4.
 * 654267767@qq.com
 * des:
 */
class ReverseCengTree107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        if (root == null) {
            return levelOrder;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            levelOrder.add(0, level);
        }
        return levelOrder;
    }



    public List<List<Integer>> mylevelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        Queue<ArrayList<TreeNode>> que = new LinkedList<>();
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        que.offer(treeNodes);
        while(!que.isEmpty()) {
           ArrayList<TreeNode> nodeList = que.poll();
            if(null != nodeList && nodeList.size() > 0) {
                ArrayList<TreeNode> list = new ArrayList<>();
                List<Integer> integers = new ArrayList<>();
                for (TreeNode node : nodeList) {
                    integers.add(node.val);
                    if(node.left != null) {
                        list.add(node.left);
                    }
                    if(node.right != null) {
                        list.add(node.right);
                    }
                }

                if(list.size()>0) {
                    que.offer(list);
                }
                if(ans.size() > 0) {
                    ans.add(0,integers);
                }else {
                    ans.add(integers);
                }
            }
        }

        return ans;
    }
}
