package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/23 12:54
 * @注释
 */
public class LeetCode141 {
    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1});
//        listNode.next.next.next = listNode;
        System.out.println(new Solution().hasCycle(listNode));
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null) {
                return false;
            }
            if (head.next == null) {
                return false; // 只有一个节点
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return true;


        }
    }
}
