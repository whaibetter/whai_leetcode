package cn.whaifree.redo.redo_24_4_20;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/22 11:44
 * @注释
 */
public class LeetCode337 {
    @Test
    public void test()
    {

        int rob = new Solution().rob(TreeNode.constructTreeByArray(1));
        System.out.println(rob);
    }

    class Solution {
        public int rob(TreeNode root) {
            int[] circle = circle(root);
            return Math.max(circle[0], circle[1]);
        }

        /**
         * int[0] 表示 不偷该点的最大收益
         * int[1] 表示 偷该点的最大收益
         * @param root
         * @return
         */
        public int[] circle(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }

            int[] left = circle(root.left);
            int[] right = circle(root.right);
            int[] res = new int[2];
            // 该店不偷，子节点可偷可不偷 只要最大收益
            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            res[1] = left[0] + right[0] + root.val;

            return res;
        }
    }
}
