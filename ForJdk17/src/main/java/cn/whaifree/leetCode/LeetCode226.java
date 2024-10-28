package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/19 14:54
 * @注释
 */
public class LeetCode226 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5});
        treeNode.printTree();
        TreeNode treeNode1 = new Solution2().invertTree(treeNode);
        treeNode1.printTree();
    }

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            reverse(root);
            return root;
        }

        public void reverse(TreeNode root) {
            if (root == null) {
                return;
            }
            swap(root);
            invertTree(root.left);
            invertTree(root.right);
        }

        public void swap(TreeNode root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    class Solution1 {
        /**
         * 层次遍历
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode pop = queue.pop();
                swap(pop);
                if (pop.right != null) {
                    queue.add(pop.right);
                }
                if (pop.left != null) {
                    queue.add(pop.left);
                }
            }
            return root;
        }


        public void swap(TreeNode root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    class Solution2 {
        /**
         * 先序遍历
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                swap(pop);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }

            }
            return root;
        }


        public void swap(TreeNode root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    class Solution3 {
        /**
         * 先序遍历
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                swap(pop);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }

            }
            return root;
        }


        public void swap(TreeNode root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

}
