package cn.whaifree.leetCode.Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 11:16
 * @注释
 */
public class LeetCode117 {

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
    }

    @Test
    public void test() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root = new Solution1().connect(root);
        System.out.println(root);
    }

    class Solution {

        List<Node> levelHead = new ArrayList<>();

        public Node connect(Node root) {
            level(root, 0);
            return root;
        }

        public void level(Node root, int level) {
            if (root == null) {
                return;
            }

            if (levelHead.size() > level) {
                root.next = levelHead.get(level);
                levelHead.set(level, root);
            }else {
                levelHead.add(level, root);
            }
            level(root.right, level + 1);
            level(root.left, level + 1);
        }
    }


    class Solution1 {
        public Node connect(Node root) {

            if (root == null) {
                return null;
            }
            Deque<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                Node nodeIndex = null;
                // 遍历这一层
                for (int i = 0; i < size; i++) {
                    Node pop = queue.pop();
                    pop.next = nodeIndex;
                    nodeIndex = pop;
                    if (pop.right != null) {
                        queue.add(pop.right);
                    }
                    if (pop.left != null) {
                        queue.add(pop.left);
                    }
                }
            }
            return root;
        }
    }
}
