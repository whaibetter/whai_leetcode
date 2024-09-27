package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 13:00
 * @注释
 */
public class LeetCode968 {

    @Test
    public void test()
    {
        TreeNode root = TreeNode.constructTreeByArray(0,0,null,0,null,0,null,null,0);
        System.out.println(new Solution().minCameraCover(root));
    }

    class Solution {

        int res = 0;
        public int minCameraCover(TreeNode root) {
            return anInt(root) == 3 ? res + 1 : res;
        }

        /**
         *
         * 1 被覆盖
         * 2 有监控
         * 3 没有监控，没有被覆盖
         *
         * 1 & 1 3
         * 1 | 2 1
         * 1 | 3 2
         * 2 | 3 2
         * 2 & 2 1
         * 3 & 3 2
         *
         * @param root
         * @return -1不装 1 装
         *
         */
        public int anInt(TreeNode root) {
            if (root == null) {
                return 1; // 空节点表示被覆盖，上面那个节点才能表示没有被监控
            }
            int left = anInt(root.left);
            int right = anInt(root.right);
            if (left == 1 && right == 1) {
                return 3;
            }
            if (left == 2 && right == 2) {
                return 1;
            }
            if (left == 3 || right == 3) {
                res++;
                // 子树有没被覆盖的，就需要加监控
                return 2;
            }
            return 1;
        }

    }
}
