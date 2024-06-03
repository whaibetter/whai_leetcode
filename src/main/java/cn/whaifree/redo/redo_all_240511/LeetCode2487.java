package cn.whaifree.redo.redo_all_240511;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/6/2 16:34
 * @注释
 */
public class LeetCode2487 {
    @Test
    public void test() {
        ListNode listNode = ListNode.listNodeFromArray(new int[]{1, 1, 1, 1});
        new Solution().removeNodes(listNode).printList();
    }

    class Solution {
        public ListNode removeNodes(ListNode head) {
            head = reverse(head);
            // 如果左侧会更大，前移动
            // 如果左侧会更小，删除左侧
            ListNode tmp = head;
            ListNode pre = head;
            while (tmp!= null) {
                // 找到比pre小的
                if (tmp.val < pre.val) {
                    pre.next = tmp.next;
                }else {
                    pre = tmp;
                }
                tmp = tmp.next;
            }


            return reverse(head);
        }

        public ListNode reverse(ListNode head) {
            return reverseList(null, head);
        }

        public ListNode reverseList(ListNode pre, ListNode after) {
            if (after == null) {
                return pre;
            }
            ListNode tmp = after.next;
            after.next = pre;
            return reverseList(after, tmp);
        }
    }

}
