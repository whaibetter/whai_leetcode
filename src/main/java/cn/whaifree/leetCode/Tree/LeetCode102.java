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
        System.out.println(new Solution().levelOrder(TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7})));
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
}
