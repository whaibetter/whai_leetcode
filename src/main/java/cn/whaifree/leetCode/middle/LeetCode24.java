package cn.whaifree.leetCode.middle;

import org.junit.Test;
import cn.whaifree.leetCode.model.ListNode;

/**
 * 两两交换链表中的节点
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *
 */
public class LeetCode24 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     * pre index tmp
     *  0 -> 1 -> 2 -> 3 -> 4 -> 5
     *
     * pre index tmp
     *  0    1 -> 2 -> 3 -> 4 -> 5
     *  ----------^
     *
     *       -----------
     * pre index tmp   |
     *  0    1 -> 2 -> 3 -> 4 -> 5
     *  ----------^
     *
     *       -----------
     * pre index tmp   |
     *  0    1 <- 2 -> 3 -> 4 -> 5
     *  ----------^
     *
     *  pre tmp  index
     *   0-> 2 -> 1 -> 3 -> 4 -> 5
     *
     *
     *           pre tmp  index
     *  0-> 2 -> 1 -> 3 -> 4 -> 5
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(0, head);
        ListNode tmp = head.next;
        ListNode index = head;
        head = pre;
        while (tmp != null) {
            pre.next = tmp;
            index.next = tmp.next;
            tmp.next = index;
            pre = index;
            index = pre.next;
            tmp = index == null ? null : index.next;
        }
        return head.next;
    }

    public ListNode swapPairsD(ListNode head) {
        if (head == null) {
            return head;
        }
        return swap(head, head.next);
    }

    /**
     * 递归的关键：
     * 1. 结束条件
     * 2. 递归条件（只考虑当前这一点点的情况，剩下的交给下次递归）
     * @param pre
     * @param after
     * @return
     */
    public ListNode swap(ListNode pre, ListNode after) {
        if (pre == null || after == null) {
            return pre;
        }
        // 递归后面的指针
        pre.next = swap(after.next, after.next == null ? null : after.next.next);
        after.next = pre;

        return after;
    }


    @Test
    public void test() {
        ListNode listNode = swapPairsD(ListNode.listNodeFromArray(new int[]{1,2,3,4,5}));
        ListNode.printList(listNode);

    }
}
