package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/30 21:10
 * @注释
 */
public class LeetCode235 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(6,2,8,0,4,7,9,null,null,3,5);

        new Solution().lowestCommonAncestor(treeNode, treeNode.left.right, treeNode.left).printTree();
    }

    class Solution {
        /**
         * 1. p,q在两边，直接返回
         * 2. pq在左边，向左递归
         * 3. pq在右边，向右递归
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//            if (root == null) {
//                return null;
//            }
//            if (root == p || root == q) {
//                // 找到元素
//                return root;
//            }

//            if (p.val < root.val && q.val > root.val) {
//                return root;
//            }
//            if (p.val > root.val && q.val < root.val) {
//                return root;
//            }

            // 只有这个有用
            if (p.val > root.val && q.val > root.val) {
                // 向右递归
                return lowestCommonAncestor(root.right, p, q);
            } else if (p.val < root.val && q.val < root.val) {
                // 向左递归
                return lowestCommonAncestor(root.left, p, q);
            }


            return root;
        }

        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) return lowestCommonAncestor1(root.left, p, q);
            if (root.val < p.val && root.val < q.val) return lowestCommonAncestor1(root.right, p, q);
            return root;
        }
    }
}
