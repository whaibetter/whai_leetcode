package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/28 22:52
 * @注释
 */
public class LeetCode501 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.constructTreeByArray(1,1,2,null,null,2);
        int[] mode = new LeetCode501().findMode(treeNode);
        for (int j : mode) {
            System.out.println(j);
        }
    }

    List<Integer> res = new ArrayList<>();
    int max = 0;
    int tmpV = Integer.MAX_VALUE;
    int tmpCount = 1;
    public int[] findMode(TreeNode root) {
        find(root);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public void find(TreeNode root) {
        if (root == null) {
            return;
        }
        find(root.left);
        if (tmpV == root.val) {
            tmpCount++;
        }else {
            tmpV = root.val;
            tmpCount = 1;
        }
        if (tmpCount > max) {
            max = tmpCount;
            res.clear();
            res.add(root.val);
        } else if (tmpCount == max) {
            res.add(root.val);
        }
        find(root.right);

    }

}
