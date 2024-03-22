package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/20 14:06
 * @注释
 */
public class LeetCode206 {

    @Test
    public void test() {
        ListNode listNode = new Solution().reverseList(ListNode.listNodeFromArray(new int[]{1, 2, 3, 4, 5}));
        listNode.printList();
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            return reverse(null, head);
        }

        public ListNode reverse(ListNode before, ListNode after) {
            if (after == null) {
                return before;
            }
            ListNode tmp = after.next;
            after.next = before;
            return reverse(after, tmp);
        }

    }
}
