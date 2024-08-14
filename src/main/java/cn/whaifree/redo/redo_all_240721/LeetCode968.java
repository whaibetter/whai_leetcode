package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/10 16:52
 * @注释
 */
public class LeetCode968 {

    @Test
    public void test() {
       System.out.println(new Solution().minCameraCover(TreeNode.constructTreeByArray(1,2,null,null,3,4,null,null,5,6)));
        System.out.println(new Solution().minCameraCover(TreeNode.constructTreeByArray(1,2,null,3,null,4,null,null,5)));

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

            int left = circle(root.left);
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
