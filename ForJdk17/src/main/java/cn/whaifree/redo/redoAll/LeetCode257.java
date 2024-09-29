package cn.whaifree.redo.redoAll;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/31 12:54
 * @注释
 */
public class LeetCode257 {
    @Test
    public void test() {

        new Solution().binaryTreePaths(TreeNode.constructTreeByArray(1)).forEach(
                i -> System.out.println(i)
        );
    }

    class Solution {

        List<String> list;
        List<Integer> path;
        public List<String> binaryTreePaths(TreeNode root) {
            list = new ArrayList<>();
            path = new ArrayList<>();
            in(root);
            return list;
        }

        public void in(TreeNode root) {
            if (root == null) {
                return;
            }
            if (root.right == null && root.left == null) {
                // 还有尾巴一个节点要处理
                StringBuilder stringBuilder = new StringBuilder();
                path.forEach(i -> {
                    stringBuilder.append(i).append("->");
                });
                stringBuilder.append(root.val);
                list.add(stringBuilder.toString());
                return;
            }
            path.add(root.val);
            if (root.left != null) {
                in(root.left);
            }
            if (root.right != null) {
                in(root.right);
            }
            path.remove(path.size() - 1);
        }
    }
}
