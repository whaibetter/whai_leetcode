package cn.whaifree.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/4 12:18
 * @注释
 */
public class LeetCode701 {

    @Test
    public void test() {

        new Solution1().insertIntoBST(TreeNode.constructTreeByArray(4, 2, 7, 1, 3), 6).printTree();
    }

    class Solution {
        public TreeNode insertIntoBST(TreeNode root, int val) {

            if (root == null) {
                return new TreeNode(val);
            }

            TreeNode pre = null;
            TreeNode index = root;
            while (index != null) {
                pre = index;
                if (index.val > val) {
                    index = index.left;
                } else {
                    index = index.right;
                }
            }

            if (pre.val > val) {
                pre.left = new TreeNode(val);
            } else {
                pre.right = new TreeNode(val);
            }

            return root;
        }
    }

    class Solution1 {
        public TreeNode insertIntoBST(TreeNode root, int val) {

            if (root == null) {
                return new TreeNode(val);
            }

            if (root.val > val) {
                root.left = insertIntoBST(root.left, val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }

            return root;
        }
    }

}
