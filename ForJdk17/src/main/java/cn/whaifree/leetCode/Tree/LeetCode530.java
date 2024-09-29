package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 18:44
 * @注释
 */
public class LeetCode530 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(236,104,701,null,227,null,911);
        System.out.println(new Solution2().getMinimumDifference(treeNode));
    }

    class Solution {

        int res = Integer.MAX_VALUE;
        public int getMinimumDifference(TreeNode root) {
            difference(root);
            return res;
        }

        public void difference(TreeNode root) {
            //二叉树搜索树，差值为相邻节点
            if (root == null) {
                return;
            }
            int right = Integer.MAX_VALUE;
            if (root.right != null) {
                right = Math.abs(root.val - root.right.val);
            }
            int left = Integer.MAX_VALUE;
            if (root.left != null) {
                left = Math.abs(root.val - root.left.val);
            }

            if (right < left && right < res) {
                res = right;
            } else if (left <= right && left < res) {
                res = left;
            }
            difference(root.right);
            difference(root.left);
        }
    }

    class Solution1 {

        int res = Integer.MAX_VALUE;
        int storage = Integer.MAX_VALUE;
        public int getMinimumDifference(TreeNode root) {
            difference(root);
            return res;
        }

        public void difference(TreeNode root) {
            if (root == null) {
                return;
            }
            difference(root.left);
            int i = Math.abs(storage - root.val);
            if (i < res) {
                res = i;
            }
            // 记录当前这个值，进入下次递归使用
            storage = root.val;
            difference(root.right);
        }
    }



    class Solution2 {


        public int getMinimumDifference(TreeNode root) {

            Deque<TreeNode> deque = new LinkedList<>();
            deque.push(root);
            int res = Integer.MAX_VALUE;
            TreeNode before = null;
            while (!deque.isEmpty()) {
                TreeNode pop = deque.pop();
                if (pop != null) {
                    if (pop.right != null) deque.push(pop.right);
                    deque.push(pop);
                    deque.push(null);
                    if (pop.left != null) deque.push(pop.left);
                } else {
                    TreeNode s = deque.pop();
                    if (before != null) {
                        res = Math.min(res, s.val - before.val);
                    }
                    before = s;
                    System.out.println(s.val);
                }
            }
            return res;

        }

    }

}
