package cn.whaifree.leetCode;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/28 22:35
 * @注释
 */
public class LeetCode437 {

    @Test
    public void test() {
        // [1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]
        TreeNode treeNode = TreeNode.constructTreeByArray(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1);
        Solution1 solution = new Solution1();
        System.out.println(solution.pathSum(treeNode, 8));
    }


    /**
     * 穷举所有子树
     */
    class Solution {

        int res = 0;

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            dfs(root, 0, targetSum);
            pathSum(root.left, targetSum);
            pathSum(root.right, targetSum);
            return res;
        }

        public void dfs(TreeNode root, long nowHave, int targetSum) {

            if (root == null) {
                return;
            }
            nowHave += root.val;
            if (nowHave == targetSum) {
                res++;
            }
            dfs(root.left, nowHave, targetSum);
            dfs(root.right, nowHave, targetSum);
        }
    }

    /**
     * 前序遍历的前缀和
     * <p>
     * <p>
     * 前序遍历统计【当前路径的】前缀和，放入map
     * key为前缀和的值，value为对于值为key的前缀和的数量
     * <p>
     * 最后遍历每个节点，这个节点的前缀和为k，再找到是否有节点的前缀和为target-k即可
     */
    class Solution1 {
        Map<Long, Integer> prefix = new HashMap<>();
        int res = 0;

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return 0;
            }
            prefix.put(0L, 1);
            dfs(root, 0, targetSum);
            return res;
        }

        /**
         * @param root
         * @param nowHave 统计前缀和
         */
        public void dfs(TreeNode root, long nowHave,int target) {
            if (root == null) {
                return;
            }

            long addThis = nowHave + root.val;
            if (prefix.containsKey(addThis - target)) {
                res += prefix.get(addThis - target);
            }
            prefix.put(addThis, prefix.getOrDefault(addThis, 0) + 1);
            dfs(root.left, addThis, target);
            dfs(root.right, addThis, target);
            prefix.put(addThis, prefix.getOrDefault(addThis, 0) - 1);
        }
    }

}
