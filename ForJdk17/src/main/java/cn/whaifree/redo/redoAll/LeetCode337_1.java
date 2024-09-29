package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/2 21:00
 * @注释
 */
public class LeetCode337_1 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(3, 4, 5, 1, 3, null, 1);
        System.out.println(new Solution().rob(root));
    }

    class Solution {
        public int rob(TreeNode root) {
            int[] circle = circle(root);
            return Math.max(circle[0], circle[1]);
        }

        /**
         *
         * @param root
         * @return int[0] 为不抢该节点获得最大利润，int[1]为抢获得最大利润
         */
        public int[] circle(TreeNode root) {

            if (root == null) {
                return new int[2];
            }

            int[] left = circle(root.left);
            int[] right = circle(root.right);

            int a = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // 不抢这个节点
            int b = left[0] + right[0] + root.val; // 抢这个节点
            return new int[]{a, b};
        }
    }
}
