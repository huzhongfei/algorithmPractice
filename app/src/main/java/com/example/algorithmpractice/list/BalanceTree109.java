package com.example.algorithmpractice.list;

/**
 * Created by hufei on 2021/5/4.
 * 654267767@qq.com
 * des: 将给定的有序链表转换为二叉搜索树的第一步是确定根节点。由于我们需要构造出平衡的二叉树，
 * 因此比较直观的想法是让根节点左子树中的节点个数与右子树中的节点个数尽可能接近。
 * 这样一来，左右子树的高度也会非常接近，可以达到高度差绝对值不超过1 的题目要求。
 * 如何找这样一个根节点？ 可以找出链表元素的  中位数 作为根节点的值。
 * 如果链表中元素个数为奇数，那么唯一的中间值为中位数；如果为偶数，那么唯二的中间值都可以作为中位数。
 *
 * 如此，递归地对左右子树进行构造，找出对应的中位数作为根节点，依次类推。
 */
class BalanceTree109 {
    /**
     *  分治法
     * 找出中位数， 使用快慢指针
     */
    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if(left == right) {
            return null;
        }
        ListNode mid = getMedia(left, right);
        TreeNode root =  new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    private ListNode getMedia(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while(fast != right &&fast.next !=right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 分治法+中序遍历
     */
    ListNode globalHead;
    public TreeNode sortedListToBST2(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree2(0, length -1);
    }

    private TreeNode buildTree2(int left, int right) {
        if(left > right) {
            return null;
        }
        int mid = (left + right +1) /2;
        TreeNode root = new TreeNode();
        root.left = buildTree2(left, mid -1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree2(mid+1, right);
        return root;
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

}
