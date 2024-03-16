package cn.whaifree.redo.redo_24_3_16;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 18:18
 * @注释
 */
public class LeetCode968 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(0,0,null,0,0);
        System.out.println(new Solution().minCameraCover(treeNode));
    }

    class Solution {
        int res = 0;
        /**
         * 0 被覆盖
         * 1 有监控
         * 2 没覆盖
         *
         * @param root
         * @return
         */
        public int minCameraCover(TreeNode root) {
            if (circle(root) == 2) {
                res++;
            }
            return res;
        }

        public int circle(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left =  circle(root.left);
            int right = circle(root.right);

            // 左边或者右边有未覆盖的，就在这里安排一个监控
            if (left == 2 || right == 2) {
                res++;
                return 1;
            }

            // 左边和右边有监控，这里就被覆盖了
            if (left == 1 || right == 1) {
                return 0;
            }

            // 左右都覆盖，这里就没覆盖
            if (left == 0 && right == 0) {
                return 2;
            }

            return -1;
        }
    }
}
