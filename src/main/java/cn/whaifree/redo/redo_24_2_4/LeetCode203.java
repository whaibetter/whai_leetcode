package cn.whaifree.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/6 23:05
 * @注释
 */
public class LeetCode203 {

    @Test
    public void test() {
        new Solution().removeElements(ListNode.listNodeFromArray(new int[]{1, 2, 3, 4}), 4).printList();
    }


    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return null;
            }

            head.next = removeElements(head.next, val);
            if (head.val == val) {
                return head.next;
            }else {
                return head;
            }
        }
    }
}
