package cn.whaifree.redo.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/6 23:18
 * @注释
 */
public class LeetCode206 {

    @Test
    public void test() {
        new Solution().reverseList(ListNode.listNodeFromArray(new int[]{1, 2, 3})).printList();
    }

    class Solution {
        public ListNode reverseList(ListNode head) {

            if (head == null) {
                return null;
            }
            return reverse(null, head);
        }

        public ListNode reverse(ListNode pre, ListNode after) {
            if (after == null) {
                return pre;
            }
            ListNode afterNext = after.next;
            after.next = pre;
            return reverse(after, afterNext);
        }
    }
}
