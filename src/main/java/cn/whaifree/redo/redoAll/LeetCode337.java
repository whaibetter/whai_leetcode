package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 15:45
 * @注释
 */
public class LeetCode337 {

    @Test
    public void test() {
        System.out.println(
                new Solution()
                        .rob(TreeNode.constructTree(new Integer[]{3, 2, 3, null, 3, null, 1})));

    }


    class Solution {
        public int rob(TreeNode root) {
            int[] ints = robOrNot(root);
            return Math.max(ints[0], ints[1]);
        }

        public int[] robOrNot(TreeNode root) {

            if (root == null) {
                return new int[2];
            }

            int[] res = new int[2];

            int[] right = robOrNot(root.right);
            int[] left = robOrNot(root.left);

            res[0] = Math.max(right[0], right[1]) + Math.max(left[0], left[1]);
            res[1] = right[0] + left[0] + root.val;

            return res;
        }


    }
}
