package cn.whaifree.redo.redo_all_240511;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/6/2 14:49
 * @注释
 */
public class LeetCode142 {

    @Test
    public void test() {
        ListNode node = ListNode.listNodeFromArray(new int[]{1,3,2});
        node.next.next.next = node.next;
        System.out.println(new Solution().detectCycle(node).val);
    }


    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            do {
                if (fast == null || fast.next == null) {
                    return null;
                }
                fast = fast.next.next;
                slow = slow.next;
            } while (fast != slow);


            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }
}
