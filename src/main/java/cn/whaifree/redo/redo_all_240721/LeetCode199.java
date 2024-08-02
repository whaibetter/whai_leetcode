package cn.whaifree.redo.redo_all_240721;

import cn.whaifree.leetCode.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode199 {

    public static void main(String[] args) {
        System.out.println(rightSideView(TreeNode.constructTreeByArray(1,2,3,null,5,null,4)));
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        level(res,root,0);
        return res;
    }

    public static void level(List<Integer> res,TreeNode root,int level){
        if (root==null){
            return;
        }
        if (res.size()==level){
            res.add(level,root.val);
        }
        level(res, root.right, level+1);
        level(res, root.left, level+1);
    }
}
