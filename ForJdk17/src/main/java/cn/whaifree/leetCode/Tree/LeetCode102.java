package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/16 20:37
 * @注释
 */
public class LeetCode102 {


    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        root.printTree();
        System.out.println(new Solution1().levelOrder(root));
    }

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                // 遍历本层的个数
                List<Integer> e = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.pop();
                    e.add(pop.val);
                    if(pop.left!=null) queue.add(pop.left);
                    if(pop.right!=null) queue.add(pop.right);
                }
                res.add(e);
            }

            return res;
        }
    }

    class Solution1 {

        List<List<Integer>> res = new LinkedList<>();

        /**
         * 二叉树递归层次遍历
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {

            if (root == null) {
                return res;
            }
            level(root, 0);

            return res;
        }

        public void level(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            List<Integer> i = null;
            if (res.size() <= level) {
                i = new ArrayList<>();
                res.add(level, i);
            } else {
                i = res.get(level);
            }
            i.add(root.val);

            level(root.left, level + 1);
            level(root.right, level + 1);

        }
    }
}
