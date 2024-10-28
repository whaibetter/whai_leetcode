package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/30 11:07
 * @注释
 */
public class LeetCode501 {
    class Solution {
        List<Integer> res = new ArrayList<>();
        int maxFrequency = 0;
        public int[] findMode(TreeNode root) {
            circle(root, 0);
            return null;
        }
        public void circle(TreeNode root, int maxFrequency) {
            if (root == null) {
                return;
            }
            if (root.right != null && root.right.val == root.val) {

            }
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTreeByArray(6,2,8,0,4,7,9,null,null,2,6);
        for (int i : new Solution1().findMode(root)) {
            System.out.println(i);
        }
    }
    class Solution1 {
        List<Integer> res = new ArrayList<>();
        // 记录当前出现次数最多的
        int max = 0;
        TreeNode pre = null;
        int sum = 0;

        public int[] findMode(TreeNode root) {
            circle(root);
            int size = res.size();
            int[] r = new int[size];
            for (int i = 0; i < size; i++) {
                r[i] = res.get(i);
            }
            return r;
        }

        public void circle(TreeNode root) {

            if (root == null) {
                return;
            }
            circle(root.left);

            int rootValue = root.val;
            // 如果当前节点是root，那么pre就是左节点
            // 如果当前节点是right,那么pre就是root
            if (pre == null || rootValue != pre.val) {
                sum = 1;
            }else {
                sum++;
            }

            // 如果出现次数比当前已经有的最大值都多，那么重新统计
            if (sum > max) {
                max = sum;
                res.clear();
                res.add(rootValue);
            } else if (sum == max) {
                res.add(rootValue);
            }
            // 记录上一个节点
            pre = root;


            circle(root.right);


        }

    }
}
