package com.example.algorithmpractice.list;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by hufei on 2021/5/3.
 * 654267767@qq.com
 * des: 根据一棵树的中序遍历与后续遍历构造二叉树
 */
class CreateTree106 {


    int post_idx;
    int[] postorder;
    int[] inorder;
    private Map<Integer, Integer> idxMap;

    /**
     * 如果 in_left >in_right ,说明子树为空，返回空节点。
     * 选择后序遍历的最后一个节点作为根节点，
     * 李彤哈希表 查询当根节点在中序遍历中下标为 index， 从in_left 到index-1属于左子树，
     * 从 index+1 到 in right 属于右子树
     * 根据后序遍历逻辑， 递归创建右子树 和左子树， 注意这里要需要先穿件右子树，再创建左子树的依赖关系，
     * 可以理解为在后续遍历的数组中整个数组是先存储左子树的节点，再存储右子树的节点，最后存储根节点，
     * 如果按每次选择 后序遍历的最后一个节点 为根节点，则仙贝构造出来的应该为右子树。
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length -1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        idxMap = new HashMap<>();
        for (int val : inorder) {
            idxMap.put(val, idx);
        }

        return helper(0, inorder.length -1);
    }

    private TreeNode helper(int in_left, int in_right) {
        // 如果这里没有二叉树了，结束
        if(in_left > in_right) {
            return null;
        }

        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两颗子树
        int index = idxMap.get(root_val);

        // 下标减一
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index -1);

        return root;
    }

    /////////////////////////

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if(postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length -1]);
        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        int inorderIndex = inorder.length -1;
        for (int i = postorder.length-2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if(node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }
}
