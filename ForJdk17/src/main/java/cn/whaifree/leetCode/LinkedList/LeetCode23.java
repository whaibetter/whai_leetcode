package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/5 12:00
 * @注释
 */
public class LeetCode23 {

    @Test
    public void test()
    {
        new Solution2().mergeKLists(
                new ListNode[]{
                        ListNode.listNodeFromArray(new int[]{}),
                        ListNode.listNodeFromArray(new int[]{1, 3, 4}),
                        ListNode.listNodeFromArray(new int[]{2, 6})
                }
        ).printList();
    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            List<ListNode> arrayList = new ArrayList<>(Arrays.asList(lists));
            return merge(arrayList);
        }

        public ListNode merge(List<ListNode> lists) {
            // 删除list中所有null
            // 使用removeIf()方法删除所有null元素
            lists.removeIf(Objects::isNull);

            if (lists.size() == 0) {
                return null;
            }

            int minIndex = 0;
            // 找到最小的返回
            // 这个最小的的下一个就是递归的

            // 找到最小的头节点的位置
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(minIndex).val > lists.get(i).val) {
                    minIndex = i;
                }
            }
            ListNode minNode = lists.get(minIndex);
            // 去掉本节点
            lists.set(minIndex, minNode.next);
            minNode.next = merge(lists);
            return minNode;

        }

    }


    class Solution1 {
        public ListNode mergeKLists(ListNode[] lists) {
            for (int i = 1; i < lists.length; i++) {
                lists[i] = merge(lists[i], lists[i - 1]);
            }
            return lists[lists.length - 1];
        }

        public ListNode merge(ListNode one, ListNode two) {
            if (one == null) {
                return two;
            } else if (two == null){
                return one;
            }

            if (one.val < two.val) {
                one.next = merge(one.next, two);
                return one;
            }else {
                two.next = merge(one, two.next);
                return two;
            }
        }

    }

    class Solution2 {
        // 时间复杂度：
        // O(nlogk)，其中 k 为 lists的长度，n 为所有链表的节点数之和。

        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        public ListNode mergeKLists(ListNode[] lists) {


           PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
               @Override
               public int compare(ListNode o1, ListNode o2) {
                   return o1.val - o2.val;
               }
           });

            for (ListNode list : lists) {
                if (list!=null) queue.add(list);
            }

            ListNode head = new ListNode(-1);
            ListNode headIndex = head;
            while (!queue.isEmpty()) {
                ListNode poll = queue.poll();
                headIndex.next = poll;
                headIndex = headIndex.next;
                if (poll.next != null) {
                    queue.add(poll.next);
                }
            }

            return head.next;
        }


    }

}
