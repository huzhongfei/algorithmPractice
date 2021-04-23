package com.example.algorithmpractice.list;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/21
 * desc:给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 */
public class NodeSort86 {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;

//        ListNode minNode = null;
//        ListNode maxNode = null;
//        ListNode firstNode = null;
//        ListNode thirdNode = null;
//        while(head != null) {
//            int val = head.val;
//            if(val < x) {
//                if(minNode == null) {
//                    minNode = head;
//                    firstNode = minNode;
//                } else {
//                    minNode.next = head;
//                    minNode = minNode.next;
//                }
//
//            }  else {
//                if(maxNode == null) {
//                    maxNode = head;
//                    thirdNode = maxNode;
//                } else {
//                    maxNode.next = head;
//                    maxNode = maxNode.next;
//                }
//            }
//
//            head = head.next;
//        }
//
//        if(maxNode != null){
//            maxNode.next = null;
//        }
//
//        if(minNode == null) {
//            firstNode = thirdNode;
//        }  else {
//            minNode.next = thirdNode;
//        }
//
//        return firstNode;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}