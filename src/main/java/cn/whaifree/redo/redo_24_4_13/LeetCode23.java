package cn.whaifree.redo.redo_24_4_13;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 13:49
 * @注释
 */
public class LeetCode23 {

    @Test
    public void test() {
        new Solution().mergeKLists(
                new ListNode[]{
                        ListNode.listNodeFromArray(new int[]{1, 4, 5})
                        , ListNode.listNodeFromArray(new int[]{1, 3, 4})
                        , ListNode.listNodeFromArray(new int[]{2, 6})
                }
        ).printList();

    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            // 优先队列
            PriorityQueue<ListNode> queue = new PriorityQueue<>(
                    new Comparator<ListNode>() {
                        @Override
                        public int compare(ListNode o1, ListNode o2) {
                            return o1.val - o2.val;
                        }
                    }
            );

            // 优先队列内出来就是最大的头部
            for (ListNode list : lists) {
                if (list != null) {
                    queue.add(list);
                }
            }

            ListNode pre = new ListNode(-1);
            ListNode preIndex = pre;
            while (!queue.isEmpty()) {
                ListNode poll = queue.poll();
                preIndex.next = poll;
                preIndex = preIndex.next;
                if (poll.next != null) {
                    queue.add(poll.next);
                }
            }

            return pre.next;
        }
    }


}
