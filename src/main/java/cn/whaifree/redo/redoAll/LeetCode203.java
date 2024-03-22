package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/20 14:01
 * @注释
 */
public class LeetCode203 {

    @Test
    public void main() {
        new Solution().removeElements(ListNode.listNodeFromArray(new int[]{1, 2, 6, 3, 4, 5, 6}), 6).printList();
    }

    class Solution {
        public ListNode removeElements(ListNode head, int val) {

            if (head == null) {
                return null;
            }

            head.next = removeElements(head.next, val);

            if (head.val == val) {
                return head.next;
            }
            return head;
        }
    }
}
