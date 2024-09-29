package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海104
 * @Date 2024/1/21 19:19
 * @注释
 */
public class LeetCode101 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 2, 3, null,null, 3});
        treeNode.printTree();
        System.out.println(new Solution1().isSymmetric(treeNode));

    }

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return compare(root.left, root.right);
        }

        public boolean compare(TreeNode left, TreeNode right) {
            // left.left 与 right.right递归
            // left.right 与 right.left递归
            if (left == null && right == null) {
                return true;
            } else if (left != null && right == null) {
                return false;
            } else if (left == null && right != null) {
                return false;
            } else if (left.val != right.val) {
                return false;
            }

            boolean outSite = compare(left.left, right.right);
            boolean inSite = compare(left.right, right.left);
            return outSite && inSite;
        }
    }

    class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }

            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root.left);
            deque.add(root.right);
            while (!deque.isEmpty()) {
                TreeNode left = deque.pop();
                TreeNode right = deque.pop();
                if (left == null && right == null) {
                    continue;
                }
//                else if (left == null && right != null) {
//                    return false;
//                } else if (left != null && right == null) {
//                    return false;
//                } else if (left.val != right.val) {
//                    return false;
//                }
                if (left == null || right == null || left.val != right.val) {
                    return false;
                }

                deque.add(left.left);
                deque.add(right.right);
                deque.add(left.right);
                deque.add(right.left);

            }
            return true;
        }



    }

}
