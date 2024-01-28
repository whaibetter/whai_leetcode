package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 15:53
 * @注释
 */
public class LeetCode700 {
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(4, 2, 7, 1, 3);
        new Solution1().searchBST(treeNode, 2).printTree();
    }

    class Solution {
        public TreeNode searchBST(TreeNode root, int val) {
            TreeNode index = root;
            while (index != null) {
                if (index.val > val) {
                    index = index.left;
                } else if (index.val < val) {
                    index = index.right;
                } else if (index.val == val) {
                    return index;
                }
            }
            return null;
        }
    }

    class Solution1 {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val > val) {
                return searchBST(root.left, val);
            }
            if (root.val < val) {
                return searchBST(root.right, val);
            }
            return root;
        }
    }
}
