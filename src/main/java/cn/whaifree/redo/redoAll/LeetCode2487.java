package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 12:31
 * @注释
 */
public class LeetCode2487 {

    @Test
    public void test() {
        new Solution().removeNodes(ListNode.listNodeFromArray(new int[]{5})).printList();
    }

    class Solution {
        /**
         * 移除每个右侧有一个更大数值的节点。
         * 逆转后，移除左侧更小的节点
         * @param head
         * @return
         */
        public ListNode removeNodes(ListNode head) {
            ListNode reverse = reverse(head);
            ListNode index = reverse;
            while (index.next != null) {
                if (index.next.val < index.val) {
                    index.next = index.next.next;
                }else {
                    index = index.next;
                }
            }

            return reverse(reverse);
        }

        public ListNode reverse(ListNode head) {
            return rev(null, head);
        }

        public ListNode rev(ListNode before, ListNode after) {
            if (after == null) {
                return before;
            }
            ListNode next = after.next;
            after.next = before;
            return rev(after, next);
        }
    }

}
