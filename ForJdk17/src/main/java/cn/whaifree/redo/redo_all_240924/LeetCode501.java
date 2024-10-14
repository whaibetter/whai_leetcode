package cn.whaifree.redo.redo_all_240924;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/13 12:22
 * @注释
 */
public class LeetCode501 {
    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(1, 0, 2, 2, 2);
        int[] res = new Solution().findMode(treeNode);
        System.out.println(Arrays.toString(res));
    }

    class Solution {

        int now = 0;
        int nowValue = 0;
        int max = 0;
        List<Integer> list = null;

        public int[] findMode(TreeNode root) {
            list = new ArrayList<>();
            in(root);
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        public void in(TreeNode root) {
            if (root == null) {
                return;
            }
            in(root.left);

            if (nowValue == root.val) {
                now++;
            }else {
                nowValue = root.val;
                now = 1;
            }

            if (now > max) {
                max = now;
                list.clear();
                list.add(root.val);
            } else if (now == max) {
                list.add(root.val);
            }


            in(root.right);
        }


    }

}
