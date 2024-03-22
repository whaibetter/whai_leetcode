package cn.whaifree.redo.redo_24_3_16;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/17 15:56
 * @注释
 */
public class LeetCode236 {

    @Test
    public void test() {
        new Solution().lowestCommonAncestor(TreeNode.constructTreeByArray(1, 2, 3, 4, 5, 6), new TreeNode(4), new TreeNode(5)).printTree();
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }

            if (root.val == p.val || root.val == q.val) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null && right != null) {
                return root;
            }


            if (left == null) {
                return right;
            }else {
                return left;
            }
        }
    }
}
