package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/24 20:59
 * @注释
 */
public class LeetCode112 {

    @Test
    public void test(
    ) {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,null,1});
        treeNode.printTree();
        System.out.println(new Solution1().hasPathSum(treeNode, 22));
    }

    class Solution {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            return level(root, 0, targetSum);
        }

        public boolean level(TreeNode treeNode,int sum,int targetSum) {
            if (treeNode == null) {
                return false;
            }
            sum += treeNode.val;
            // 最后一个必须是叶子节点
            if (treeNode.left == null && treeNode.right == null && sum == targetSum) {
                return true;
            }
            boolean left = level(treeNode.left, sum, targetSum);
            boolean right = level(treeNode.right, sum, targetSum);
            return left || right;
        }
    }

    class Solution1 {

        /**
         * 使用队列
         * @param root
         * @param targetSum
         * @return
         */
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }

            Deque<Object> queue = new java.util.ArrayDeque<>();
            queue.add(root);
            queue.add(root.val);
            while (!queue.isEmpty()) {
                TreeNode pop = (TreeNode) queue.pop();
                Integer value = (Integer) queue.pop();
                if (pop.right == null && pop.left == null && value == targetSum) {
                    return true;
                }
                if (pop.right != null) {
                    queue.add(pop.right);
                    queue.add(value + pop.right.val);
                }
                if (pop.left != null) {
                    queue.add(pop.left);
                    queue.add(value + pop.left.val);
                }
            }
            return false;
        }

    }
}
