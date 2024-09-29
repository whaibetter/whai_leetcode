package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 0:51
 * @注释
 */
public class LeetCode23 {

    @Test
    public void test() {

        new Solution().mergeKLists(
                new ListNode[]{
                        // [[-2,-1,-1,-1],[]]
                        ListNode.listNodeFromArray(new int[]{-2,-1,-1,-1}),
                        ListNode.listNodeFromArray(new int[]{}),
                }
        ).printList();

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
                ListNode tmp = list;
                while (tmp != null) {
                    priorityQueue.add(tmp);
                    tmp = tmp.next;
                }
            }

            ListNode pre = new ListNode(-1);
            ListNode tmp = pre;
            while (!priorityQueue.isEmpty()) {
                ListNode poll = priorityQueue.poll();
                poll.next = null; // 防止出现循环
                tmp.next = poll;
                tmp = tmp.next;
            }
            return pre.next;
        }
    }
}
