package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.ListNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/23 23:09
 * @注释
 */
public class LeetCode2487 {
    public static void main(String[] args) {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{99, 2, 13, 3, 8});
        removeNodes(listNode).printList();
    }

    public static ListNode removeNodes(ListNode head) {
        head = reverseList(new ListNode(-1, head));
        ListNode index = head;
        while (index.next != null) {
            if (index.val > index.next.val) {
                index.next = index.next.next;
                continue;
            }
            index = index.next;
        }
        return reverseList(head);
    }

    public static ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    /**
     * a b c
     * @param pre
     * @param after
     * @return
     */
    public static ListNode reverse(ListNode pre, ListNode after) {
        if (after == null) {
            return pre;
        }
        ListNode tmp = after.next;
        after.next = pre;
        return reverse(after, tmp);
    }

}
