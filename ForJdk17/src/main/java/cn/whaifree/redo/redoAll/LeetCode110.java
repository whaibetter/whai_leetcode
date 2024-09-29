package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 12:29
 * @注释
 */
public class LeetCode110{

    @Test
    public void test()
    {
        Solution solution = new Solution();
        System.out.println(solution.isBalanced(TreeNode.constructTreeByArray(1,2,2,3,null,null,3,4,null,null,4)));
    }


    class Solution {
        public boolean isBalanced(TreeNode root) {
            return getLevel(root) != -1;
        }

        public int getLevel(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = getLevel(root.left);
            int right = getLevel(root.right);
            if (left == -1 || right == -1) {
                return -1;
            }
            if (Math.abs(left - right) > 1) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }
}
