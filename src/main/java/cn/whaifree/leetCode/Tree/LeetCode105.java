package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/27 13:34
 * @注释
 */
public class LeetCode105 {

    @Test
    public void test() {
        TreeNode.constructTree(new Integer[]{3,9,20,1,null,15,7});
        // 9 3 20 7 15
        // 20 3 7 9 15
        // 20 7 3 15 9


        new Solution().buildTree(new int[]{3,9,1,20,15,7}, new int[]{1,9,3,15,20,7}).printTree();

    }

    class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        public TreeNode build(
                int[] preorder,
                int preorderStart,
                int preorderEnd,
                int[] inorder,
                int inorderStart,
                int inorderEnd
        ) {
            if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
                return null;
            }
            int value = preorder[preorderStart];
            Integer indexInInorder = map.get(value);
            TreeNode node = new TreeNode(value);
            // 左边的个数就是index的InInorderNumber

            //只要有左边节点数量，其他都好算
            int leftChildNumber = indexInInorder - inorderStart;

            // 前序 start的下一个 ~ start+左边的数量
            // 中序 start ~ start+左边的数量-1(不包含index)
            node.left = build(
                    preorder,
                    preorderStart + 1,
                    preorderStart + leftChildNumber,
                    inorder,
                    inorderStart,
                    inorderStart + leftChildNumber - 1
            );

            //3 9 1 20 15 7
            //1 9 3 15 20 7

            // 前序 start+左边的数量+1（index） ~ end
            // 中序 左边的数量+1（index） ~ end
            node.right = build(
                    preorder,
                    preorderStart + leftChildNumber + 1,
                    preorderEnd,
                    inorder,
                    indexInInorder + 1,
                    inorderEnd);

            return node;

        }
    }


}
