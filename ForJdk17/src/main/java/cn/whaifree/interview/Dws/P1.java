package cn.whaifree.interview.Dws;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/20 19:32
 * @注释
 */
public class P1 {

    class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         * <p>
         * 每日温度
         *
         * @param dailyTemperatures int整型一维数组
         * @return int整型一维数组
         */
        public int[] temperatures(int[] dailyTemperatures) {
            // write code here
            // 每一天需要等待几天会出现更高温
            // 单调栈
            int[] res = new int[dailyTemperatures.length];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < dailyTemperatures.length; i++) {
                while (!stack.isEmpty() && dailyTemperatures[i] > dailyTemperatures[stack.peek()]) {
                    Integer pop = stack.pop();
                    res[pop] = i - pop;
                }
                stack.push(i);
            }
            return res;
        }
    }


}

class Solution {


    public static void main(String[] args) {
        Solution solution = new Solution();
        // {1,2,3,#,#,4,5},1
        TreeNode treeNode = TreeNode.constructTree(new Integer[]{1, 2, 3, null, null, 4, 5});
        System.out.println(solution.cyclicShiftTree(treeNode, 1));
    }

    // Node类封装了节点的层次信息，便于后续重构指针
    static class Node {
        int parentIndex;// 父节点在当前层的位置
        int currentIndex; // 当前节点在该层的索引位置
        boolean leftFlag; // 标记当前节点是左孩子(0)还是右孩子(1)
        TreeNode currentNode; // 当前节点的引用

        public Node(int parentIndex, int currentIndex, boolean leftFlag, TreeNode currentNode) {
            this.parentIndex = parentIndex;
            this.currentIndex = currentIndex;
            this.leftFlag = leftFlag;
            this.currentNode = currentNode;
        }
    }

    /**
     * https://blog.csdn.net/ouyang_peng/article/details/143254632
     *
     * @param root TreeNode类
     * @param k    int整型
     * @return TreeNode类
     */
    public TreeNode cyclicShiftTree(TreeNode root, int k) {
        if (root == null) return null;  //

        // 存储每层的节点信息，最后用来重构
        List<List<Node>> levels = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        // 根节点加入队列
        queue.offer(new Node(0, 0, true, root));

        // `parentNum`记录当前层节点数，`nextNum`记录下一层节点数
        int parentNum = 1; // 根节点只有1个节点
        int nextNum = 0; // 层次遍历，每次进入一个到这一层，就++，就能统计这一层的元素个数
        List<Node> tempLevel = new ArrayList<>(); // 当前层的节点列表

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            tempLevel.add(node); // 加入当前层
            parentNum--;

            if (node.currentNode.left != null) {
                queue.offer(
                        new Node(
                                node.currentIndex, // 父节点位置， node为父亲节点在当前层的位置
                                nextNum++, // 当前节点位置
                                true,
                                node.currentNode.left
                        )
                );
            }
            if (node.currentNode.right != null) {
                queue.offer(new Node(node.currentIndex, nextNum++, false, node.currentNode.right));
            }
            if (parentNum == 0) {
                // 每层最后一个元素
                parentNum = nextNum;
                nextNum = 0;
                levels.add(tempLevel);
                tempLevel = new ArrayList<>();
            }
        }

        int depth = levels.size() - 1;
        // 从最底层开始，逐层向上进行位移操作。
        // 对于每一层，首先计算出这一层需要位移的步数，然后根据位移步数调整每个节点的左右子节点指针。
        for (int i = depth; i > 0; i--) {
            List<Node> parentLev = levels.get(i - 1); // 上一层的节点
            int parentSize = parentLev.size();

            // 这一层节点左右都设置为空
            for (Node node : parentLev) {
                node.currentNode.left = null;
                node.currentNode.right = null;
            }

            // 计算这一层需要位移的位置，位移后在哪个父节点之下
            //  上一层的个数决定位移个数
            /**
             * 比如
             *   1
                 \
                  3
                 / \
                4   5
               第3层的位移个数是根据第二层有几个元素确定的，这个位置和父亲节点的距离为move
             */
            int move = k % (2 * parentSize);
            tempLevel = levels.get(i); // 本层
            for (Node node : tempLevel) {
                int targetIndex = 0; // 在本层的新位置
                if (node.leftFlag) {
                    targetIndex = (node.parentIndex + move / 2) % parentSize;
                } else {
                    targetIndex = (node.parentIndex + (move + 1) / 2) % parentSize;
                }


                int targetFlag = 0;
                if (node.leftFlag) {
                    targetFlag = move % 2;
                } else {
                    targetFlag = (move + 1) % 2;
                }

                Node targetP = parentLev.get(targetIndex);
                if (targetFlag == 0) {
                    targetP.currentNode.left = node.currentNode;
                } else {
                    targetP.currentNode.right = node.currentNode;
                }
            }
        }

        return root;
    }
}
