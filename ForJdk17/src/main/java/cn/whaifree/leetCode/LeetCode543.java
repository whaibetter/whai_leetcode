package cn.whaifree.leetCode;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/28 22:21
 * @注释
 */
public class LeetCode543 {
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(new Solution().diameterOfBinaryTree(treeNode));
    }

    class Solution {
        int max = 0;
        public int diameterOfBinaryTree(TreeNode root) {
            in(root);
            return max;
        }

        public int in(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = in(root.left);
            int right = in(root.right);
            max = Math.max(left + right, max);
            return Math.max(left, right) + 1;
        }
    }
}
