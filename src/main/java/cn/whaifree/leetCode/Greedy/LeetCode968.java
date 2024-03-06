package cn.whaifree.leetCode.Greedy;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/6 16:34
 * @注释
 */
public class LeetCode968 {

    @Test
    public void test() {
        System.out.println(new Solution().minCameraCover(TreeNode.constructTreeByArray(1, 2, null, 2, 4, 6)));
    }


    /**
     * 我们分别有三个数字来表示：
     *
     * 0：该节点无覆盖
     * 1：本节点有摄像头
     * 2：本节点有覆盖
     */
    class Solution {

        int res = 0;
        public int minCameraCover(TreeNode root) {

            // 根节点
            if (travel(root) == 0) {
                res++;
            }
            return res;
        }

        public int travel(TreeNode root) {

            if (root == null) {
                return 2;
            }


            int left = travel(root.left);
            int right = travel(root.right);

            // 左右节点都有覆盖
            if (left == 2 && right == 2) return 0;

            //左或右无覆盖，本节点要增加摄像头
            if (left == 0 || right == 0) {
                res++;
                return 1;
            }

            // 左或右有摄像头，本节点被覆盖
            if (left == 1 || right == 1) {
                return 2;
            }

            return -1;
        }
    }
}
