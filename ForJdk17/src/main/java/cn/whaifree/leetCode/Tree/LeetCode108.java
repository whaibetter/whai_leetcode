package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/31 18:56
 * @注释
 */
public class LeetCode108 {

    @Test
    public void test() {
        new Solution().sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).printTree();
    }

    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return construct(nums, 0, nums.length - 1);
        }

        public TreeNode construct(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            int middle = (end + start) / 2;
            int num = nums[middle];
            TreeNode root = new TreeNode(num);
            root.left = construct(nums, start, middle - 1);
            root.right = construct(nums, middle + 1, end);
            return root;
        }
    }

}
