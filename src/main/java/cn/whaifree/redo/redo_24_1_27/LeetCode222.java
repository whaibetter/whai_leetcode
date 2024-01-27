package cn.whaifree.redo.redo_24_1_27;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/27 20:57
 * @注释
 */
public class LeetCode222 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println(new Solution().countNodes(treeNode));
    }


    class Solution {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            TreeNode indexRight = root.right;
            int rightDepth = 0;
            while (indexRight != null) {
                rightDepth++;
                indexRight = indexRight.right;
            }
            TreeNode indexLeft = root.left;
            int leftDepth = 0;
            while (indexLeft != null) {
                leftDepth++;
                indexLeft = indexLeft.left;
            }


            // 计算两边的深度，如果深度一样，就是完全二叉树，返回 2^0+2^1+2^2...
            // 满二叉树
            if (rightDepth == leftDepth) {
                // 相当于 2^0+2^1+....+2^rightDepth
                return (2 << rightDepth) - 1;
            }

            // 如果两边深度不一样，返回+1
            return countNodes(root.right) + countNodes(root.left) + 1;

        }
    }
}
