package cn.whaifree.redo.redo_all_241121;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/23 12:32
 * @注释
 */
public class LeetCode124 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{9, 9, 20, null, null, 15, 7});
        System.out.println(new Solution().maxPathSum(treeNode));
    }

    class Solution {
        int max = Integer.MIN_VALUE;
        /**
         * 每个节点的路径来源于 左边 右边 上面
         * @param root
         * @return
         */
        public int maxPathSum(TreeNode root) {
            int in = in(root);
            return max;
        }

        public int in(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left = in(root.left);
            if (left < 0) {
                left = 0;// 左边拖后腿，直接不采用
            }
            int right = in(root.right);
            if (right < 0) {
                right = 0;// 右边拖后腿，直接不采用
            }
            max = Math.max(max, left + right + root.val);
            return Math.max(left, right) + root.val;
        }

    }
}
