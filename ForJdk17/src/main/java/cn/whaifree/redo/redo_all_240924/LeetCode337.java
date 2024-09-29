package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/29 11:56
 * @注释
 */
public class LeetCode337 {
    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(3, 2, 3, null, 3, 1, 1);
        System.out.println(new Solution().rob(root));

    }

    class Solution {
        public int rob(TreeNode root) {
            int[] dp = robIn(root);
            return Math.max(dp[0], dp[1]);
        }

        /**
         * int 0 不抢该节点的最大利润
         * int 1 抢该节点的最大利润
         * @param root
         * @return
         */
        public int[] robIn(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] left = robIn(root.left);
            int[] right = robIn(root.right);

            int noRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            int rob = left[0] + right[0] + root.val;
            return new int[]{noRob, rob};
        }
    }
}
