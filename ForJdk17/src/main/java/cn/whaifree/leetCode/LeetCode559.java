package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/21 20:20
 * @注释
 */
public class LeetCode559 {

    @Test
    public void test() {
        Node node = new Node(1);
        node.children = new LinkedList<>();
        node.children.add(new Node(2));
        node.children.add(new Node(3));
        node.children.add(new Node(4));
        node.children.get(2).children = new ArrayList<>();
        node.children.get(2).children.add(new Node(6));
        node.children.get(2).children.add(new Node(7));

        System.out.println(new Solution1().maxDepth(node));
    }

    class Solution {
        public int maxDepth(Node root) {
            return getDepth(root);
        }

        public int getDepth(Node root) {
            if (root == null) {
                return 0;
            }
            int max = 1;

            List<Node> children = root.children;
            if (children != null) {
                for (Node child : children) {
                    max = Math.max(getDepth(child) + 1, max);
                }
            }
            return max;
        }
    }

    class Solution1 {
        int max = 0;
        public int maxDepth(Node root) {
            if (root == null) {
                return 0;
            }
            getDepth(root, 1);
            return max;
        }

        public void getDepth(Node root,int level) {
            if (root == null) {
                return;
            }
            if (level > max) {
                max = level;
            }
            List<Node> children = root.children;
            if (children != null) {
                for (Node child : children) {
                    getDepth(child, level + 1);
                }
            }
        }
    }

}
