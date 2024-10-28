package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 19:41
 * @注释
 */
public class LeetCode17_12BiNode {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(4, 2, 5, 1, 3, null, 6, 0);
        TreeNode treeNode = new Solution().convertBiNode(root);
        TreeNode.treeToArray(treeNode).forEach(i -> System.out.println(i));

    }
    class Solution {

        TreeNode res = new TreeNode();
        TreeNode index = res;
        public TreeNode convertBiNode(TreeNode root) {
            circle(root);
            return res.right;
        }

        private void circle(TreeNode root) {
            if (root == null) {
                return;
            }
            circle(root.left);
            index.right = new TreeNode(root.val);
            index = index.right;
            circle(root.right);
        }
    }

    class Solution1 {

        TreeNode res = new TreeNode();
        TreeNode index = res;
        public TreeNode convertBiNode(TreeNode root) {
            circle(root);
            return res.right;
        }

        private void circle(TreeNode root) {
            if (root == null) {
                return;
            }
            circle(root.left);
            // 直接使用，不创建新对象，能提升很多效率
            index.right = root;
            root.left = null;
            index = index.right;
            circle(root.right);
        }
    }
}



