package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 12:22
 * @注释
 */
public class LeetCode124 {
    class Solution {

        int max = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxPathSum(root.left);
            if (left < 0) {
                // 不采纳左边的
                left = 0;
            }
            int right = maxPathSum(root.right);
            if (right < 0) {
                right = 0;
            }
            max = Math.max(max, left + right + root.val);
            return Math.max(left, right) + root.val; // 一个节点只能选择一条路，左路或者右路
        }
    }
}

