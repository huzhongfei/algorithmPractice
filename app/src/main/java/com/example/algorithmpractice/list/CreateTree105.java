package com.example.algorithmpractice.list;

import android.view.View;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by hufei on 2021/5/1.
 * 654267767@qq.com
 * des:
 *
 * 二叉树前序遍历的顺序为：
 *      先遍历根节点；随后递归地遍历左子树；最后递归地遍历右子树。
 *
 * 二叉树中序遍历的顺序为：
 *      先递归地遍历左子树；随后遍历根节点；最后递归地遍历右子树。
 */
class CreateTree105 {

    /**
     *  方法一：递归
     *  对弈任意一颗树而言，前序遍历的形式总是： [根节点， [左子树的前序遍历结果],[右子树的前序遍历结果]]
     *  即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是：[[左子树的中序遍历结果]， 根节点，[右子树的中序遍历结果]]
     *
     *  只要我们再中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的秦旭遍历和
     *  中序遍历的长度显然是相同的，因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
     *  这样一来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，我们就可以递归地
     *  构造出左子树和右子树，再将这两颗子树街道根节点的左右位置。
     *
     *  细节：
     *  在中序遍历中对根节点进行定位时，一种简单的方法就是直接扫描整个中序遍历的结果并找出根节点，
     *  但这样做的时间复杂度较高，我们可以考虑使用哈希表来帮助我们快速地定位根节点。
     *  对于哈希表中每个键值对，键便是一个元素（节点的值),值表示其在中序变美丽中的出现问题。
     */
    private Map<Integer, Integer> idexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder,
                                int preorder_left, int preorder_right,
                                int inorder_left, int inorder_right) {
        if(preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = idexMap.get(preorder[preorder_root]);

        // 先把根节点建立歘来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        //先序遍历中[从 左边界 + 1 开始的 size_left_subtree] 个元素就对应了中序遍历中 [从左边界 开始到 根节点定位-1] 的元素
        root.left = myBuildTree(preorder, inorder, preorder_left+1, preorder_left+size_left_subtree,
                inorder_left, inorder_root -1);

        // 递归地构造右子树，并连接到根节点
        // 先序遍历中[从 左边界+1+左子树节点数目 开始到 有边界] 的元素就对应了中序遍历中[从 根节点定位+1 到有边界] 的元素
        root.right = myBuildTree(preorder, inorder, preorder_left +size_left_subtree+1,
                preorder_right, inorder_root+1, inorder_right);

        return root;
    };

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        idexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idexMap.put(inorder[i], i);
        }

        return myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
    }

    //***********************************

    /**
     *  方法二：迭代
     *  对于前序遍历中的任意两个连续节点u 和v, 根据前序遍历的流程，我们可以知道u和v只有两种可能的关系：
     *  1、v 是 u 的左儿子，只是因为在遍历到u之后，下一个遍历的节点就是 u 的左儿子，即 v;
     *  2.u没有左儿子，并且并且v 是 u 的某个祖先节点 （或者u本身） 的右儿子。如果u没有左儿子，
     *  那么下一个遍历的节点就是 u 的右儿子。如果 u没有右儿子，我们就会向上回溯，直到遇到第一个有右儿子
     *  （且 u 不再它的右儿子的自述中）的节点Ua, 那么 v 就是 ua 的右儿子。
     *
     *  算法流程：
     *  1.我们用一个栈和一个指针辅助进行二叉树的构造。初始时栈中存放了根节点（前序遍历的第一个节点）， 指针指向中序遍历的第一个节点。
     *  2.我们一次枚举前序遍历中除了第一个节点以外的每个节点，如果 index 恰好指向栈顶节点，那么我们不断地
     *  弹出栈顶节点并向右移动 index， 并将当前节点作为左后一个弹出的的节点的右儿子；如果index 和栈顶节点不同，
     *  我们将当前节点作为栈顶节点的左儿子；
     *  3.无论是哪一种强开，我们最后都将当前的节点入栈。
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
      if(preorder == null || preorder.length == 0) {
          return null;
      }
      TreeNode root = new TreeNode(preorder[0]);
      Deque<TreeNode> stack = new LinkedList<>();
      stack.push(root);
      int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
      return root;
    }

}
