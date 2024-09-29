package cn.whaifree.redo.redo.redo_24_1_27;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/27 19:52
 * @注释
 */
public class LeetCode257 {

    @Test
    public void test() {
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, null, 5});
        List<String> list = new Solution1().binaryTreePaths(treeNode);
        list.forEach(
                s -> System.out.println(s)
        );

    }

    class Solution {
        List<String> res = new ArrayList<>();
        public List<String> binaryTreePaths(TreeNode root) {
            level(root, "");
            return res;
        }

        public void level(TreeNode root, String path) {
            if (root == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                String s = new StringBuilder().append(path).append(root.val).toString();
                res.add(s);
                return;
            }
            String s = new StringBuilder().append(path).append(root.val).append("->").toString();
            level(root.right, s);
            level(root.left, s);
        }
    }

    class Solution1 {

        List<String> res = new ArrayList<>();
        List<Integer> paths = new ArrayList<>();
        public List<String> binaryTreePaths(TreeNode root) {
            level(root);
            return res;
        }

        public void level(TreeNode root) {
            if (root == null) {
                return;
            }
            paths.add(root.val);
            if (root.left == null && root.right == null) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < paths.size()-1; i++) {
                    str.append(paths.get(i)).append("->");
                }
                str.append(root.val);
                res.add(str.toString());
                return;
            }


            if (root.right != null) {
                level(root.right);
                paths.remove(paths.size() - 1);
            }
            if (root.left != null) {
                level(root.left);
                paths.remove(paths.size() - 1);
            }
        }
    }
}
