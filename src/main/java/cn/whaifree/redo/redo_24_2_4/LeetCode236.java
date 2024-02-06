package cn.whaifree.redo.redo_24_2_4;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/4 0:08
 * @注释
 */
public class LeetCode236 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(1, 2, 3, 4, 5, 6);
        new Solution().lowestCommonAncestor(treeNode, treeNode.right, treeNode.left).printTree();
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            if (root == p || root == q) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null && right != null) {
                // 左右两边都不为空，那么我就是最近公共节点
                return root;
            }

            if (left != null) {
                return left;
            }else {
                return right;
            }
        }
    }
}
