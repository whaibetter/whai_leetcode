package cn.whaifree.redo.redo.redo_24_3_9;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 11:35
 * @注释
 */
public class LeetCode968 {

    @Test
    public void test() {
        System.out.println(new Solution().minCameraCover(TreeNode.constructTreeByArray(1,2,null,3,4,5,null,null,6)));
    }

    class Solution {
        int total = 0;
        /**
         * 监控二叉树
         * 2 被覆盖
         * 0 没覆盖
         * 1 有摄像头
         *
         * 0 0 | 0 1 | 1 1 | 0 2 | 1 2 | 2 2
         *
         *
         * 根节点必然只能被监控，根节点的父节点放摄像头
         * @param root
         * @return
         */
        public int minCameraCover(TreeNode root) {
            // 判断根节点的左右
            int rootIndex = Tracking(root);
            if (rootIndex == 0) {
                total++;
            }
            return total;
        }

        public int Tracking(TreeNode root) {
            if (root == null) {
                return 2; // 空节点表示被覆盖
            }

            int left = Tracking(root.left);
            int right = Tracking(root.right);

            if (left == 2 && right == 2) {
                return 0;
            }
            // 如果左右存在没被覆盖的，这个点就放置一个摄像头
            if (left == 0 || right == 0) {
                total++;
                return 1;
            }
            // 如果左右子节点有摄像头，本节点为被覆盖的
            if (left ==1 || right == 1) {
                return 2;
            }

            return -1;
        }
    }

}
