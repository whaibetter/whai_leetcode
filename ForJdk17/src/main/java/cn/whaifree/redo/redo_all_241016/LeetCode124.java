package cn.whaifree.redo.redo_all_241016;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/25 12:23
 * @注释
 */
public class LeetCode124 {


    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(2, -1);
        System.out.println(new Solution().maxPathSum(treeNode));
    }
    class Solution {

        int max = 0;
        public int maxPathSum(TreeNode root) {
            max = Integer.MIN_VALUE;
            maxPath(root);
            return max;
        }
        public int maxPath(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxPath(root.left);
            int right = maxPath(root.right);

            if (left < 0) { // 该路径 至少包含一个 节点
                left = 0;
            }
            if (right < 0) {
                right = 0;
            }

            max = Math.max(max, left + right + root.val);
            if (left > right) {
                return left + root.val;
            }else {
                return right + root.val;
            }
        }
    }
}
