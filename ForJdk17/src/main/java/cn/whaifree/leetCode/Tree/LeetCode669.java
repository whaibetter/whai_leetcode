package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/31 14:01
 * @注释
 */
public class LeetCode669 {

    @Test
    public void test() {
        new Solution().trimBST(TreeNode.constructTreeByArray(3,0,4,null,2,null,null,1), 1, 3).printTree();
    }

    class Solution {

        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == null) {
                return null;
            }

            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);

            if (root.val < low) {
                return root.right;
            }

            if (root.val > high) {
                return root.left;
            }

            return root;
        }
    }
}
