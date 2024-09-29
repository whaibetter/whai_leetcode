package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.ListNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/21 20:47
 * @注释
 */
public class LeetCode203 {

    public static void main(String[] args)
    {
        removeElements(ListNode.listNodeFromArray(new int[]{1, 2, 6, 3, 4, 5, 6}), 6).printList();
    }


    public static ListNode removeElements(ListNode node, int val) {
        if (node == null) {
            return null;
        }
        ListNode next = removeElements(node.next, val);
        if (node.val == val) { // 这个node不能用
            return next;
        }
        node.next = next;
        return node;
    }

    public static ListNode remove(ListNode node, int val) {
        if (node == null) {
            return null;
        }
        ListNode next = remove(node.next, val);
        if (node.val == val) { // 这个node不能用
            return next;
        }
        node.next = next;
        return node;
    }
}
