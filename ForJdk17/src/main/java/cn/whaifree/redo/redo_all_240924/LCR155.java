package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 13:15
 * @注释
 */
public class LCR155 {

    @Test
    public void test() {
        Solution solution = new Solution();
        Node node = new Node(4);
        node.left = new Node(2);
        node.right = new Node(5);
        node.left.left = new Node(1);
        node.left.right = new Node(3);
        Node node1 = solution.treeToDoublyList(node);
        System.out.println(node1);
    }

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

    /*
// Definition for a Node.
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
};
*/
    class Solution {

        Node pre;
        Node index;

        public Node treeToDoublyList(Node root) {
            if (root == null)
                return null;
            pre = new Node(-1);
            index = pre; // index作为链表指针
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
