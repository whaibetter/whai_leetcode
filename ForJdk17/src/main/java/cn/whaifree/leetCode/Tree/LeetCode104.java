package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/21 20:05
 * @注释
 */
public class LeetCode104 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 2, 3, null,null,null});
        treeNode.printTree();
        System.out.println(new Solution2().maxDepth(treeNode));
    }

    class Solution {

        int depth = 0;
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            level(root, 1);
            return depth;
        }

        public void level(TreeNode treeNode, int level) {
            if (treeNode == null) {
                return;
            }
            if (level > depth) {
                depth = level;
            }
            if (treeNode.right != null) {
                level(treeNode.right, level + 1);
            }
            if (treeNode.left != null) {
                level(treeNode.left, level + 1);
            }

        }
    }

    class Solution2{

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return level(root);
        }

        public int level(TreeNode treeNode) {
            if (treeNode == null) {
                return 0;
            }
            int left = level(treeNode.left) + 1;
            int right = level(treeNode.right) + 1;
            return Math.max(left, right);
        }
    }

    class Solution1 {


        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int depth = 0;
            // 右视图
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = deque.pop();
                    if (pop.left != null) {
                        deque.add(pop.left);
                    }
                    if (pop.right != null) {
                        deque.add(pop.right);
                    }
                }
                depth++;
            }
            return depth;
        }

    }
}
