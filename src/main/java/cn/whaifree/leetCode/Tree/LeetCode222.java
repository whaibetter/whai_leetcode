package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/22 19:05
 * @注释
 */
public class LeetCode222 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5, 6});
        treeNode.printTree();
        System.out.println(new Solution1().countNodes(treeNode));
    }

    class Solution {


        public int countNodes(TreeNode root) {
            return inLevel(root);
        }

        public int inLevel(TreeNode node) {
            if (node==null) return 0;
            int left = inLevel(node.left) + 1;
            int right = inLevel(node.right) ;
            return left + right;
        }
    }

    class Solution1 {


        public int countNodes(TreeNode root) {
            return inLevel(root);
        }

        public int inLevel(TreeNode node) {
            if (node == null) {
                return 0;
            }
            // 计算左边深度
            int leftDepth = 0;
            TreeNode leftNode = node.left;
            while (leftNode != null) {
                leftNode = leftNode.left;
                leftDepth++;
            }

            // 计算右边深度
            int rightDepth = 0;
            TreeNode rightNode = node.right;
            while (rightNode != null) {
                rightNode = rightNode.right;
                rightDepth++;
            }

            // 如果两变深度一样，那么该树是完全二叉树
            if (leftDepth == rightDepth) {
                return (2 << leftDepth) - 1;
            }
            // 如果两边深度不一样，递归左右子节点+1
            return inLevel(node.right) + inLevel(node.left) + 1;

        }
    }

}
