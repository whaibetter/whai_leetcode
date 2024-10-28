package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 12:34
 * @注释
 */


public class LeetCode129_2 {


    /**
     * 向下遍历，从前往后获取值
     */
    class Solution {

        public int sumNumbers(TreeNode root) {
            return level(root, 0);
        }

        public int level(TreeNode root,int preV) {
            if (root == null) {
                return 0;
            }
            int sum = root.val + preV * 10;
            if (root.left == null && root.right == null) {
                return sum;
            }
            int right = level(root.right,sum);
            int left = level(root.left, sum);
            return right + left;
        }
    }
}
