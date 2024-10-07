package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/7 11:37
 * @注释
 */
public class LeetCode138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    @Test
    public void test() {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

        Node res = new Solution().copyRandomList(node1);
        System.out.println(res);
    }

    class Solution {
        public Node copyRandomList(Node head) {
            Node pre = new Node(-1);
            pre.next = head;
            Node index = pre.next;
            while (index!= null) {
                Node tmp = index.next;
                Node newNode = new Node(index.val);
                index.next = newNode;
                newNode.next = tmp;
                index = index.next.next;
            }

            index = pre.next;
            while (index != null) {
                Node nowRandom = index.random;
                if (nowRandom == null) {
                    index.next.random = null;
                }else {
                    index.next.random = nowRandom.next;
                }
                index = index.next.next;
            }

            Node newHead = new Node(-1);
            Node newHeadIndex = newHead;
            Node idx = pre.next;
            while (idx != null) {
                newHeadIndex.next = idx.next;
                newHeadIndex = newHeadIndex.next;
                idx.next = idx.next.next;
                idx = idx.next;
            }

            return newHead.next;
        }
    }

}
