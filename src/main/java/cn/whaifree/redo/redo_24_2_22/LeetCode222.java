package cn.whaifree.redo.redo_24_2_22;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/22 13:16
 * @注释
 */
public class LeetCode222 {

    @Test
    public void test() {
        System.out.println(new Solution().countNodes(TreeNode.constructTreeByArray(1)));
    }

    class Solution {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            TreeNode tmp = root;
            int right = 0;
            while (tmp!=null) {
                tmp = tmp.right;
                right++;
            }
            tmp = root;
            int left = 0;
            while (tmp!=null) {
                tmp = tmp.left;
                left++;
            }
            if (right == left) {
                return (2 << right-1) - 1;
            }

            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
}
