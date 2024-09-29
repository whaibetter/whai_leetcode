package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/21 20:36
 * @注释
 */
public class LeetCode110 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1,2,2,3,null,null,3,4,null,null,4});
        treeNode.printTree();
        System.out.println(new Solution().isBalanced(treeNode));
    }

    class Solution {
        public boolean isBalanced(TreeNode root) {
            return level(root)!=-1;
        }

        public int level(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = level(root.left);
            if (leftHeight == -1) {
                return -1;
            }
            int rightDepth = level(root.right);
            if (rightDepth == -1) {
                return -1;
            }
            if (Math.abs(leftHeight - rightDepth) > 1) {
                return -1;
            }
            return Math.max(leftHeight, rightDepth) + 1;
//            return Math.abs(leftHeight - rightDepth) > 1 ? -1 : Math.max(leftHeight, rightDepth) + 1;
        }
    }
}
