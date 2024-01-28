package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.Node;
import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 16:05
 * @注释
 */
public class LeetCode98 {


    @Test
    public void test() {
        System.out.println(new Solution().isValidBST(TreeNode.constructTreeByArray(5,4,6,null,null,3,7)));
    }

    class Solution {


        long compare = Long.MIN_VALUE;
        boolean res = true;

        public boolean isValidBST(TreeNode root) {
            isValid(root);
            return res;
        }
        public void isValid(TreeNode root) {

            if (root == null) {
                return;
            }
            isValid(root.left);
            if (root.val <= compare) {
                res = false;
                return;
            }
            compare = root.val;
            isValid(root.right);
        }

    }

    class Solution1 {
        public boolean isValidBST(TreeNode root) {
            return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        public boolean isValid(TreeNode root,int min,int max) {
            if (root == null) {
                return true;
            }
            if (root.val <= min || root.val >= max) {
                return false;
            }
            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
        }
    }
}
