package cn.whaifree.redo.redo.redo_24_2_22;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/22 11:40
 * @注释
 */
public class LeetCode450 {

    @Test
    public void test() {
        new Solution().deleteNode(TreeNode.constructTreeByArray(5,3,6,2,4,null,7), 7).printTree();
    }

    class Solution {

        public TreeNode deleteNode(TreeNode root, int key) {

            if (root == null) {
                return null;
            }

            if (root.val == key) {
                // 遇到左边或右边为空
                if (root.left == null) {
                    return root.right;
                }
                if (root.right == null) {
                    return root.left;
                }

                // 左边右边都不为空
                TreeNode pre = root.right;
                while (pre.left != null) {
                    pre = pre.left;
                }
                pre.left = root.left;
                return root.right;
            }

            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }
            return root;
        }
    }
}
