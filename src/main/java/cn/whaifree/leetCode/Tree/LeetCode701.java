package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/31 9:13
 * @注释
 */
public class LeetCode701 {

    @Test
    public void test() {
        new Solution2().insertIntoBST(TreeNode.constructTreeByArray(5,null,14,10,77,null,null,null,95,null,null), 4).printTree();

    }

    class Solution {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            insert(root, val);
            return root;
        }

        public void insert(TreeNode root, int val) {
            if (root == null) {
                return;
            }


            if (root.val < val) {
                insert(root.right, val);
                if (root.right == null) {
                    root.right = new TreeNode(val);
                }
            } else {
                insert(root.left, val);
                if (root.left == null) {
                    root.left = new TreeNode(val);
                }
            }
        }
    }

    class Solution1 {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            // 找到了叶子节点，该位置就是要插入的地方
            if (root == null) {
                return new TreeNode(val);
            }

            if (root.val < val) {
                root.right = insertIntoBST(root.right, val);
            }else if (root.val > val){
                root.left = insertIntoBST(root.left, val);
            }
            return root;
        }
    }
    class Solution2 {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root==null) return new TreeNode(val);
            TreeNode pre = null;
            TreeNode index = root;
            while (index != null) {
                pre = index;
                if (index.val > val) {
                    index = index.left;
                } else {
                    index = index.right;
                }
            }
            if (pre.val > val) {
                pre.left = new TreeNode(val);
            } else {
                pre.right = new TreeNode(val);
            }
            return root;


        }
    }

}
