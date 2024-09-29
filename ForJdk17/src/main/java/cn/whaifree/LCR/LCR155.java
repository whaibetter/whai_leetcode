package cn.whaifree.LCR;

import org.junit.Test;

import java.util.*;

public class LCR155 {


    public static void main(String[] args) {
        new Thread(() -> {
            List<byte[]> list = new ArrayList<byte[]>();
            while (true) {
//
            }
        }).start();

        // 线程二
        new Thread(() -> {
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //    https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/description/
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    @Test
    public void test() {
        Node node1 = new Node(4);
        node1.left = new Node(2);
        node1.right = new Node(5);
        node1.left.left = new Node(1);
        node1.left.right = new Node(3);
        Node node = new Solution1().treeToDoublyList(node1);

    }

    class Solution {

        public Node treeToDoublyList(Node root) {

            if (root == null) {
                return null;
            }
            if (root.left == null && root.right == null) {
                root.right = root;
                root.left = root;
                return root;
            }


            List<Node> nodes = new ArrayList<>();
            Deque<Node> deque = new LinkedList<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                Node pop = deque.pop();
                if (pop != null) {
                    if (pop.right != null) {
                        deque.push(pop.right);
                    }
                    deque.push(pop);
                    deque.push(null);
                    if (pop.left != null) {
                        deque.push(pop.left);
                    }
                }else {
                    nodes.add(deque.pop());
                }
            }


            Node head = new Node(Integer.MAX_VALUE);
            Node index = head;
            for (int i = 0; i < nodes.size() - 1; i++) {
                Node node = nodes.get(i);
                index.right = node;
                node.left = index;
                index = index.right;
            }
            Node node = nodes.get(nodes.size() - 1);
           index.right = node;
            node.left = index;
            node.right = head.right;
            head.right.left = node;


            return head.right;
        }

    }

    class Solution1 {

        Node pre;
        Node index;
        public Node treeToDoublyList(Node root) {
            pre = new Node(-1);
            index = pre;
            in(root);
            index.right = pre.right;
            pre.right.left = index;
            return pre.right;
        }

        public void in(Node root) {
            if (root == null) {
                return;
            }

            in(root.left);
            index.right = root;
            root.left = index;
            index = index.right;

            in(root.right);
        }
    }


}
