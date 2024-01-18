package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/17 16:41
 * @注释
 */
public class LeetCode637 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1,2,3});
        System.out.println(new Solution1().averageOfLevels(treeNode));
    }

    class Solution {


        public List<Double> averageOfLevels(TreeNode root) {

            List<Double> res = new ArrayList<>();
            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {

                int size = queue.size();
                double sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.pop();
                    sum += pop.val;
                    if(pop.left!=null) queue.add(pop.left);
                    if(pop.right!=null) queue.add(pop.right);
                }
                res.add((sum / size));
            }
            return res;
        }
    }


    class Solution1 {

        List<Double> res = new ArrayList<>();
        List<Integer> count = new ArrayList<>();


        public List<Double> averageOfLevels(TreeNode root) {

            level(root, 0);
            for (int i = 0; i < res.size(); i++) {
                res.set(i, res.get(i) / count.get(i));
            }
            return res;
        }

        void level(TreeNode treeNode, int level) {

            if (treeNode == null) {
                return;
            }

            if (res.size() <= level) {
                res.add(0d);
                count.add(0);
            }
            res.set(level, res.get(level) + treeNode.val);
            count.set(level, count.get(level) + 1);
            if (treeNode.left != null) {
                level(treeNode.left, level + 1);
            }
            if (treeNode.right != null) {
                level(treeNode.right, level + 1);
            }
        }
    }
}
