package cn.whaifree.leetCode.Tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/18 16:11
 * @注释
 */
public class LeetCode116 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    };

    @Test
    public void test() {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);
        System.out.println(new Solution1().connect(node));
    }

    class Solution {

        public Node connect(Node root) {
            if (root == null) {
                return null;
            }

            Deque<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {

                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node pop = queue.pop();

                    // 防止把下一层的节点赋值为next
                    if (i < size - 1) {
                        pop.next = queue.peek();
                    }

                    if (pop.left != null) {
                        queue.add(pop.left);
                    }
                    if (pop.right != null) {
                        queue.add(pop.right);
                    }
                }
            }
            return root;

        }
    }

    class Solution1 {

        /**
         * 递归方法
         * @param root
         * @return
         */
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }
            circle(root);
            return root;
        }

        void circle(Node root) {
            if (root == null) {
                return;
            }
            if (root.left!=null) root.left.next = root.right == null ? null : root.right;
            if (root.right!=null) root.right.next = root.next == null ? null : root.next.left;
            circle(root.left);
            circle(root.right);
        }
    }
}
