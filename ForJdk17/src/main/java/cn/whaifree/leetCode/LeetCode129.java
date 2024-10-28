package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 22:27
 * @注释
 */
public class LeetCode129 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(new Integer[]{4, 9, 0, 5, 1});
        int sum = new Solution().sumNumbers(treeNode);
        System.out.println(sum);
    }

    class Solution {
        public int sumNumbers(TreeNode root) {
            return sumNumbers(root, 0);
        }
        public int sumNumbers(TreeNode root,int pre) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return pre * 10 + root.val;
            }
            int now = pre * 10 + root.val;
            int left = sumNumbers(root.left, now);
            int right = sumNumbers(root.right, now);
            return left + right;
        }
    }
}
