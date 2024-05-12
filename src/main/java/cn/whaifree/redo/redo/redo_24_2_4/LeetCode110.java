package cn.whaifree.redo.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/3 19:17
 * @注释
 */
public class LeetCode110 {

    @Test
    public void test() {
        System.out.println(new Solution().isBalanced(TreeNode.constructTreeByArray(1,null,2,null,3)));
    }
    class Solution {
        public boolean isBalanced(TreeNode root) {
            return balance(root) != -1;
        }

        public int balance(TreeNode root) {

            if (root == null) {
                return 0;
            }
            int right = balance(root.right);
            if (right == -1) {
                return -1;
            }
            int left = balance(root.left);
            if (left == -1) {
                return -1;
            }

            // 左右两边高度差
            if (Math.abs(right - left) > 1) {
                return -1;
            }

            // 返回高度
            return Math.max(right, left)+ 1;
        }

    }
}
