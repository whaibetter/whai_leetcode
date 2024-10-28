package cn.whaifree.leetCode.Tree;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/17 15:07
 * @注释
 */
public class LeetCode199 {

    @Test
    public void test() {
        TreeNode root = TreeNode.constructTree(new Integer[]{1, 2, 3, 4});
        root.printTree();
        System.out.println(new Solution1().rightSideView(root));
    }



    class Solution {
        /**
         * 层次遍历，每层最右边就是结果
         * @param root
         * @return
         */
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> queue = new LinkedList<>();

            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.pop();
                    // 只加入每层最后一个元素
                    if(i==size-1) res.add(pop.val);
                    if (pop.left != null) {
                        queue.add(pop.left);
                    }
                    if (pop.right != null) {
                        queue.add(pop.right);
                    }
                }
            }
            return res;
        }
    }

    class Solution1 {


        List<Integer> res = new ArrayList<>();
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return res;
            }
            level(root, 0);
            return res;
        }

        public void level(TreeNode root, int level) {

            if (root == null) {
                return;
            }

            // 每层只有一个输出，就是每层最右边的那个
            // 每层都是让右边先进入递归，就能保证获取到最右的数据
            if (res.size() == level) {
                res.add(root.val);
            }
            level(root.right, level + 1);
            level(root.left, level + 1);

        }
    }
}
