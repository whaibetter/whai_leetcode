package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 17:00
 * @注释
 */
public class LeetCode230 {
    @Test
    public void test() {





        TreeNode treeNode = TreeNode.constructTreeByArray(5,3,6,2,4,null,null,1);
        System.out.println(new Solution().kthSmallest(treeNode, 3));
    }

    class Solution {
        int res = 0;
        public int kthSmallest(TreeNode root, int k) {
            if (root == null) {
                return -1;
            }
            int left = kthSmallest(root.left, k);
            if (left != -1) {
                return left;
            }
            res++;
            if (res == k) {
                return root.val;
            }
            int right = kthSmallest(root.right, k);
            if (right != -1) {
                return right;
            }
            return -1;
        }



    }
}
