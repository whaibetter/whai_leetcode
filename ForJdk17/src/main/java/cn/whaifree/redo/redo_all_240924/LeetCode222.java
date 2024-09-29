package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 12:05
 * @注释
 */
public class LeetCode222 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(1, 2, 3, 4);
        System.out.println(new Solution().countNodes(treeNode));
    }

    class Solution {
        public int countNodes(TreeNode root) {

            if (root == null) {
                return 0;
            }

            TreeNode left = root;
            int leftCount = 0;
            while (left != null) {
                leftCount++;
                left = left.left;
            }
            TreeNode right = root;
            int rightCount = 0;
            while (right != null) {
                rightCount++;
                right = right.right;
            }

            if (leftCount == rightCount) {
                return (2 << (leftCount - 1)) - 1;
            }
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
}
