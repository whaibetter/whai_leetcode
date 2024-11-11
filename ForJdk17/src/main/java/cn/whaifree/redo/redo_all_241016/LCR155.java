package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/9 18:04
 * @注释
 */
public class LCR155 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        Node node = new Node(4);
        Node node1 = solution.treeToDoublyList(node);
        System.out.println(node1);
    }

    class Solution {

        Node head = new Node(-1);
        Node index = head;

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }

            in(root);
            index.right = head.right;
            head.right.left = index;
            return head.right;
        }

        public void in(Node root) {
            if (root == null) {
                return;
            }
            in(root.left);
            index.right = root;
            root.left = index;
            index = root;
            in(root.right);
        }
    }

}
