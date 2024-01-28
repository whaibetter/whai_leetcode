package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import jdk.nashorn.internal.runtime.RewriteException;
import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 13:56
 * @注释
 */
public class LeetCode654 {

    @Test
    public void test() {
            new Solution().constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}).printTree();
    }

    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return circle(nums, 0, nums.length-1);
        }

        private TreeNode circle(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            // 只有一个元素，直接构造即可
            if (end == start) {
                return new TreeNode(nums[end]);
            }
            int maxIndex = findMax(nums, start, end);
            TreeNode treeNode = new TreeNode(nums[maxIndex]);
            treeNode.left = circle(nums, start, maxIndex - 1);
            treeNode.right = circle(nums, maxIndex + 1, end);
            return treeNode;
        }

        private int findMax(int[] nums, int start, int end) {
            int maxIndex = start;
            while (start < end) {
                start++;
                if (nums[start] > nums[maxIndex]) {
                    maxIndex = start;
                }
            }
            return maxIndex;
        }
    }
}
