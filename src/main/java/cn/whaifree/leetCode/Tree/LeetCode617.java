package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 15:07
 * @注释
 */
public class LeetCode617 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(1, 3, 2, 5);
        TreeNode treeNode1 = TreeNode.constructTreeByArray(2, 1, 3, null, 4, null, 7);
        new Solution2().mergeTrees(treeNode, treeNode1).printTree();
    }

    class Solution {

        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            return merge(root1, root2);
        }

        public TreeNode merge(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) {
                return null;
            } else if (root1 != null && root2 == null) {
                TreeNode treeNode = new TreeNode(root1.val, root1.left, root1.right);
                return treeNode;
            } else if (root2 != null && root1 == null) {
                TreeNode treeNode = new TreeNode(root2.val, root2.left, root2.right);
                return treeNode;
            }
            TreeNode treeNode = new TreeNode(root1.val + root2.val);
            treeNode.right = merge(root1.right, root2.right);
            treeNode.left = merge(root1.left, root2.left);
            return treeNode;
        }
    }

    class Solution1 {

        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            return merge(root1, root2);
        }

        public TreeNode merge(TreeNode root1, TreeNode root2) {
            // 其中一边为空，直接返回另一半
            if (root2 == null) {
                return root1;
            }
            if (root1 == null) {
                return root2;
            }

            root1.val = root1.val + root2.val;
            root1.right = merge(root1.right, root2.right);
            root1.left = merge(root1.left, root2.left);
            return root1;
        }
    }

    class Solution2 {

        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }

            Deque<TreeNode> deque = new java.util.ArrayDeque<>();
            deque.add(root1);
            deque.add(root2);
            while (!deque.isEmpty()) {
                TreeNode pop1 = deque.pop();
                TreeNode pop2 = deque.pop();

                pop1.val += pop2.val;

                // 出来的两个节点，判断左右是否为空
                if (pop1.left != null && pop2.left != null) {
                    deque.add(pop1.left);
                    deque.add(pop2.left);
                }
                if (pop1.right != null && pop2.right != null) {
                    deque.add(pop1.right);
                    deque.add(pop2.right);
                }
                if (pop1.left == null && pop2.left != null) {
                    pop1.left = pop2.left;
                }
                if (pop1.right == null && pop2.right != null) {
                    pop1.right = pop2.right;
                }
            }

            return root1;
        }

    }
}
