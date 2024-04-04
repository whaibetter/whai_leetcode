package cn.whaifree.leetCode.Dynamic;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/3 13:55
 * @注释
 */
public class LeetCode337 {

    @Test
    public void test()
    {
        System.out.println(new Solution().rob(TreeNode.constructTreeByArray(4,1,null,2,null,3)));
    }


    class Solution {
        public int rob(TreeNode root) {
            /**
             * dp[0]表示不选该点的最大收益 dp[1]表示选该节点的最大收益
             *
             */
            int[] ints = robDown(root);
            return Math.max(ints[0], ints[1]);
        }

        public int[] robDown(TreeNode root) {
            int res[] = new int[2];
            if (root == null) {
                return res;
            }
            int[] left = robDown(root.left);
            int[] right = robDown(root.right);

            // 不偷：Max(左孩子不偷，左孩子偷) + Max(右孩子不偷，右孩子偷)
            //      不偷：左右随意，选最大的
            res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            // 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
            //      偷：必须保证左右都不偷
            res[1] = left[0] + right[0] + root.val;
            return res;
        }

    }

    class Solution1 {
        public int rob(TreeNode root) {
            // 两层，根节点用和不用
            return Math.max(robDown(root, true), robDown(root, false));
        }

        public int robDown(TreeNode root, boolean flag) {
            if (root == null) {
                return 0;
            }

            int left = robDown(root.left, !flag);
            int right = robDown(root.right, !flag);

            if (flag) {
                return left + right + root.val;
            }else {
                return left + right;
            }
        }
    }
}
