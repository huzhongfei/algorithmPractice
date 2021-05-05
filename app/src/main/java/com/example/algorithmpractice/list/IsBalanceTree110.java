package com.example.algorithmpractice.list;

/**
 * Created by hufei on 2021/5/5.
 * 654267767@qq.com
 * des:给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
class IsBalanceTree110 {

    /**
     * 方法一：自定向下递归，比较层数。 类似于前序遍历
     * 时间复杂度: O(n²),其中n 是二叉树中的节点个数。
     * 空间复杂度:O(n),取决于递归调用的层数。
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1
                    && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    private int height(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    /***************************
     *
     */

    /**
     *  方法二：自底向上递归
     *  方法一是自顶向下递归，因此对于同一个节点，函数 height 会被重复调用，导致时间复杂度较高，
     *  如果使用自底向上的做法，则对于每个节点，函数 height 只会被调用一次。
     *  自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断左右子树是否平衡，再判读以当前节点为根的子树是否平衡。
     *  如果一颗子树是平衡的，则返回其高度，否则返回 -1，如果存在一颗子树不平衡，则曾哥二叉树一定不平衡。
     *
     *  时间复杂度：O(n),空间复杂度O(n)
     */
    public boolean isBalanced2(TreeNode root) {
        return height2(root) >=0;
    }

    private int height2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftHeight = height2(root.left);
        int rightHeight = height2(root.right);
        if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) >1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
