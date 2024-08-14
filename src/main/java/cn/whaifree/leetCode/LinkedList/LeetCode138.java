package cn.whaifree.leetCode.LinkedList;

import org.junit.Test;

public class LeetCode138 {

    static class Node {
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

        // 1 2 3
        // 1 3
        // 2 1

//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(3);
//        node1.next = node2;
//        node2.next = node3;
//        node1.random = node3;
//        node2.random = node1;
//        Node node = copyRandomList(node1);
//        System.out.println(node);


//        [[7,null],[13,0],[11,4],[10,2],[1,0]]
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
        Node node = copyRandomList(node1);
        System.out.println(node);
    }

    public Node copyRandomList(Node head) {
        Node index = head;
        while (index != null) {
            Node tmp = index.next;
            index.next = new Node(index.val);
            index.next.next = tmp;
            index = index.next.next;
        }

        index = head;
        while (index != null) {
            if (index.random != null) {
                index.next.random = index.random.next;
            }
            index = index.next.next;
        }


        Node newHead = head.next;
        index = newHead;
        Node tmpPreIndex = head;
        while (index.next != null) {
            tmpPreIndex.next = tmpPreIndex.next.next;
            tmpPreIndex = tmpPreIndex.next;
            index.next = index.next.next;
            index = index.next;
        }
        tmpPreIndex.next = null;
        return newHead;
    }

}
