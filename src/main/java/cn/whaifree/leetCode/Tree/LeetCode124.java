package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 15:05
 * @注释
 */
public class LeetCode124 {
    @Test
    public void test()
    {

        TreeNode treeNode = TreeNode.constructTreeByArray(1, 2, 3);
        System.out.println(new Solution().maxPathSum(treeNode));
    }

    class Solution {
        /**
         * f
         * a
         * b c
         * <p>
         * a有可能的路径
         * 1. f a c
         * 2. f a b
         * 3. b a c 不包含父亲节点 用b+a+c与max判断
         *
         * @param root
         * @return
         */
        int max = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            maxPath(root);
            return max;
        }
        public int maxPath(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxPath(root.left);
            int right = maxPath(root.right);
            if (left < 0) {
                left = 0;
            }
            if (right < 0) {
                right = 0;
            }
            max = Math.max(max, left + right + root.val); // 如果是 b a c 既没有用父亲节点
            return Math.max(left, right) + root.val;
        }
    }
}
