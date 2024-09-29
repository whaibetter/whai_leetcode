package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 15:46
 * @注释
 */
public class LeetCode246 {

    @Test
    public void test()
    {
        TreeNode root = TreeNode.constructTreeByArray(6,2,8,0,4,7,9,null,null,3,5);
        TreeNode p = new TreeNode(0);
        TreeNode q = new TreeNode(5);
        Solution solution = new Solution();
        TreeNode treeNode = solution.lowestCommonAncestor(root, p, q);
        System.out.println(treeNode.val);
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

            if(left == null && right == null){
                return null;
            }
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
