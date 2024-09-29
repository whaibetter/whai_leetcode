package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 15:39
 * @注释
 */
public class LeetCode23 {

    @Test
    public void test() {
        ListNode.printList(
                new Solution().mergeKLists(
                        new ListNode[]{
                                ListNode.listNodeFromArray(new int[]{1, 4, 5}),
                                ListNode.listNodeFromArray(new int[]{1, 3, 4}),
                                ListNode.listNodeFromArray(new int[]{2, 6})}
                )
        );
    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            });
            for (ListNode list : lists) {
                ListNode index = list;
                while (index != null) {
                    priorityQueue.add(index);
                    index = index.next;
                }
            }

            ListNode pre = new ListNode(-1);
            ListNode tmp = pre;
            while (!priorityQueue.isEmpty()) {
                ListNode poll = priorityQueue.poll();
                poll.next = null;
                tmp.next = poll;
                tmp = tmp.next;
            }

            return pre.next;

        }
    }
}
