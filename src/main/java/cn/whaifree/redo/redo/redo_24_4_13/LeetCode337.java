package cn.whaifree.redo.redo.redo_24_4_13;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/14 11:55
 * @注释
 */
public class LeetCode337 {
    @Test
    public void test() {

        Solution solution = new Solution();
        System.out.println(solution.rob(TreeNode.constructTreeByArray(3,2,3,null,3,null,1)));
    }


    class Solution {
        /**
         * 0 表示不选该点最大收益
         * 1 表示选择该点的最大收益
         * @param root
         * @return
         */
        public int rob(TreeNode root) {
            int[] res = dpIn(root);
            return Math.max(res[0], res[1]);
        }

        public int[] dpIn(TreeNode root) {
            if (root == null) {
                return new int[]{0, 0};
            }
            int[] dp = new int[2];
            int[] left = dpIn(root.left);
            int[] right = dpIn(root.right);

            // 不选择本点,那么子节点随便选择，只要收益大
            dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            // 选择了本点，子节点就不能选
            dp[1] = left[0] + right[0] + root.val;
            return dp;
        }
    }

}
