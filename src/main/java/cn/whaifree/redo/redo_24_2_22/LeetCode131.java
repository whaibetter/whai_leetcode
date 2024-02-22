package cn.whaifree.redo.redo_24_2_22;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/22 11:25
 * @注释
 */
public class LeetCode131 {

    @Test
    public void test(
    ) {
        TreeNode treeNode = TreeNode.constructTreeByArray(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return root;
            }

            if (root.val == p.val) {
                return root;
            }
            if (root.val == q.val) {
                return root;
            }

            TreeNode right = lowestCommonAncestor(root.right, p, q);
            TreeNode left = lowestCommonAncestor(root.left, p, q);

            // 这里用于输出公共父节点
            // 某个节点，第一个出现的左边有p或者q，右边也有p或者q，则就是公共父节点。
            if (right != null && left != null) {
                return root;
            }

            if (right != null) {
                return right;
            } else {
                return left;
            }

        }
    }
}
