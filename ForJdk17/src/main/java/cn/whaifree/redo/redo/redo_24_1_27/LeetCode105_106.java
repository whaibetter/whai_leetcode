package cn.whaifree.redo.redo.redo_24_1_27;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/28 13:01
 * @注释
 */
public class LeetCode105_106 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{3,9,20,null,null,15,7});


        new SolutionPost().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}).printTree();
    }

    class SolutionPre {
        Map<Integer, Integer> map =new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode build(
                int[] preorder,
                int preStart,
                int preEnd,
                int[] inorder,
                int inStart,
                int inEnd
        ) {

            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }
            int needValue = preorder[preStart];
            Integer indexInInorder = map.get(needValue);
            TreeNode treeNode = new TreeNode(needValue);
            //treeNode左边节点个数
            int leftNumber = indexInInorder - inStart;

            treeNode.left = build(preorder, preStart + 1, preStart + leftNumber, inorder,inStart,indexInInorder-1);

            treeNode.right = build(preorder, preStart + leftNumber + 1, preEnd, inorder, indexInInorder + 1, inEnd);


            return treeNode;
        }

    }


    class SolutionPost {
        Map<Integer, Integer> map =new HashMap<>();

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }

        private TreeNode build(
                int[] inorder,
                int inStart,
                int inEnd,
                int[] postorder,
                int postStart,
                int postEnd
        ) {

            // 9 15 8 20 3
            // 9 3 20 15 7

            if (postStart > postEnd || inStart > inEnd) {
                return null;
            }
            int needValue = postorder[postEnd];
            Integer indexInInorder = map.get(needValue);
            TreeNode treeNode = new TreeNode(needValue);

            //treeNode左边节点个数
            int leftNumber = indexInInorder - inStart;

            // 4 5 2 7 3 1
            // 4 2 5 1 3 7
            treeNode.left = build(inorder, inStart, indexInInorder - 1, postorder, postStart, postStart + leftNumber - 1);
            treeNode.right = build(inorder, indexInInorder + 1, inEnd, postorder, postStart + leftNumber, postEnd - 1);


            return treeNode;
        }

    }
}
