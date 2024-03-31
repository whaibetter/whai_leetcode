package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 11:37
 * @注释
 */
public class LeetCode199 {
    @Test
    public void test() {

        Solution1 solution = new Solution1();
        List<Integer> integers = solution.rightSideView(TreeNode.constructTreeByArray(1, 2, 3, null, 5, null, null));
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }
    }

    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            // 层次遍历，每层的最后一个
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size ; i++) {
                    TreeNode e = queue.pop();
                    if (e.left != null) queue.add(e.left);
                    if (e.right != null) queue.add(e.right);
                    if (i == size - 1) {
                        res.add(e.val);
                    }
                }

            }
            return res;
        }
    }
    class Solution1 {
        List<Integer> res = new ArrayList<>();
        public List<Integer> rightSideView(TreeNode root) {
            level(root, 0);
            return res;
        }

        public void level(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            if (res.size() == level) res.add(root.val);
            level(root.right, level + 1);
            level(root.left, level + 1);
        }
    }
}
