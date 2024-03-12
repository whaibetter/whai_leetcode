package cn.whaifree.redo.redo_24_3_9;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 19:03
 * @注释
 */
public class LeetCode236 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        System.out.println(new Solution().lowestCommonAncestor(root, root.left, root.left.right).val);

    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            // 如果是p或者q 返回该节点
            if (root.val == p.val || root.val == q.val) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            // 如果左右都不为空，则找到了公共父节点
            if (left != null && right != null) {
                return root;
            }

            // 左边为空，则左边不存在要找的p或者q，返回右边
            if (left != null) {
                return left;
            }else {
                return right;
            }
        }
    }
}
