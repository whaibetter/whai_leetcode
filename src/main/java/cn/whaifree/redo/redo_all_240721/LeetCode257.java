package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 0:32
 * @注释
 */
public class LeetCode257 {
    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.constructTreeByArray(3, 9, 20, null, null, 15, 7);
        for (String binaryTreePath : binaryTreePaths(treeNode)) {
            System.out.println(binaryTreePath);
        }
        for (String binaryTreePath : binaryTreePaths(TreeNode.constructTreeByArray(1,2,3,null,null,4,5))) {
            System.out.println(binaryTreePath);
        }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        search(root, res, path);
        return res;
    }

    public static void search(TreeNode root,List<String> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            int val = root.val;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                stringBuilder.append(path.get(i)).append("->");
            }
            stringBuilder.append(val);
            res.add(stringBuilder.toString());
        }
        if (root.right != null) {
            search(root.right,  res, path);
        }
        if (root.left != null) {
            search(root.left, res, path);
        }
        path.remove(path.size() - 1);
    }


}
