package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/25 21:29
 * @注释
 */
public class LeetCode106 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7});
        // 9 3 20 7 15
        // 20 3 7 9 15
        // 20 7 3 15 9

        //9,3,15,20,7
        //9,15,7,20,3
        new Solution().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}).printTree();

    }

    class Solution {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 0) {
                return null;
            }
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return circle(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

        }

        public TreeNode circle(
                int[] inorder,
                int inorderStart,
                int inorderEnd,
                int[] postorder,
                int posterStart,
                int posterEnd
        ) {
            if (posterEnd < posterStart || inorderStart > inorderEnd) {
                return null;
            }
            int lastValue = postorder[posterEnd];
            TreeNode root = new TreeNode(lastValue);

            // 中序中的下标
            Integer index = map.get(lastValue);

            // 左字树的个数

            int leftChileNumber = index - inorderStart;
            // 中序 start ~ start+leftnumber(即index)
            // 后序 start ~ start+左边的树
            root.left = circle(
                    inorder,
                    inorderStart,
                    index - 1,
                    postorder,
                    posterStart,
                    posterStart + leftChileNumber - 1);
            // 中序 index+1 ~ end
            // 后序 start+left ~ end-1(中序节点)
            root.right = circle(
                    inorder,
                    index + 1,
                    inorderEnd, postorder,
                    posterStart + leftChileNumber,
                    posterEnd - 1);
            return root;
        }
    }





}
