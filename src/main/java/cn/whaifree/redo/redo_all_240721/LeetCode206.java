package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.ListNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/22 22:56
 * @注释
 */
public class LeetCode206 {
    public static void main(String[] args)
    {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5});
        ListNode.printList(reverseList(listNode));

    }

    /**
     * 递归
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head)
    {
        return reverse(null, head);
    }

    /**
     *  a b c d
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
