package cn.whaifree.leetCode.LinkedList;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 23:06
 * @注释
 */
public class LeetCode103 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTreeByArray(new Integer[]{3, 9, 20, 88, 99, 15, 7, 10, 5, 1});

        List<List<Integer>> lists =
                new Solution().zigzagLevelOrder(treeNode);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return res;
            }
            level(root, 0, true);
            return res;
        }

        public void level(TreeNode root, int level, boolean leftIn) {
            if (root == null) {
                return;
            }
            if (level == res.size()) {
                res.add(level, new ArrayList<>());
            }
            if (leftIn) {
                res.get(level).add(root.val);
            } else {
                res.get(level).add(0, root.val);
            }
            level(root.left, level + 1, !leftIn);
            level(root.right, level + 1, !leftIn);
        }
    }
}
