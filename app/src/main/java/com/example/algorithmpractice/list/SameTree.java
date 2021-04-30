package com.example.algorithmpractice.list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/29
 * desc: 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
//        return isSame(p, q);

        // 深度优先
        if(p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSame(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     *  广度优先
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if(node1.val != node2.val) {
                return false;
            }
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            TreeNode right1 = node1.right;
            TreeNode right2 = node1.right;
            if(left1 == null ^ left2 == null) {
                return false;
            }

            if(right1 == null ^ right2 == null) {
                return false;
            }

            if(left1 != null) {
                queue1.offer(left1);
            }
            if(right1 != null) {
                queue1.offer(right1);
            }

            if(left2 != null) {
                queue2.offer(left2);
            }
            if(right2 != null) {
                queue2.offer(right2);
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }




    public boolean isSame(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if(p == null) {
            return false;
        }
        if(q == null) {
            return false;
        }

        boolean left = isSame(p.left, q.left);
        boolean self = p.val == q.val;
        boolean right = isSame(p.right, q.right);

        return left && self && right;
    }
}
