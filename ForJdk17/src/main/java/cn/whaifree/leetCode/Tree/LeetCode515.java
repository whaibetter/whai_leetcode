package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.Node;
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
 * @Date 2024/1/18 10:45
 * @注释
 */
public class LeetCode515 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{3, 1, 5, 0, 2, 4, 6, null, null, null, 3});
        treeNode.printTree();
        List<Integer> integers = new Solution().largestValues(treeNode);
        System.out.println(integers);
    }

    class Solution {
        public List<Integer> largestValues(TreeNode root) {

            List<Integer> res = new ArrayList<Integer>();

            if (root == null) {
                return res;
            }

            Deque<TreeNode> queue = new LinkedList<>();

            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                int max = Integer.MIN_VALUE;

                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.pop();
                    max = Math.max(pop.val, max);
                    if (pop.left != null) queue.add(pop.left);
                    if (pop.right != null) queue.add(pop.right);
                }
                res.add(max);
            }
            return res;
        }
    }
}
