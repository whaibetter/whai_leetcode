package cn.whaifree.redo.redo_24_2_22;

import cn.whaifree.leetCode.model.ListNode;
import cn.whaifree.leetCode.model.TreeNode;
import com.sun.xml.internal.ws.server.sei.ValueGetter;
import org.junit.Test;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/22 12:35
 * @注释
 */
public class LeetCode206 {

    @Test
    public void test() {
        new Solution().reverseList(ListNode.listNodeFromArray(new int[]{1, 2, 3})).printList();
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
            return reverse(after,tmp);
        }
    }
}
