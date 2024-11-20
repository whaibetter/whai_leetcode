package cn.whaifree.interview.Dws;

import cn.whaifree.leetCode.model.TreeNode;

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
         *
         * 每日温度
         * @param dailyTemperatures int整型一维数组
         * @return int整型一维数组
         */
        public int[] temperatures (int[] dailyTemperatures) {
            // write code here
            // 每一天需要等待几天会出现更高温
            // 单调栈
            int[] res = new int[dailyTemperatures.length];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < dailyTemperatures.length; i++) {
                while (!stack.isEmpty()&&dailyTemperatures[i] > dailyTemperatures[stack.peek()]) {
                    Integer pop = stack.pop();
                    res[pop] = i -pop;
                }
                stack.push(i);
            }
            return res;
        }
    }


}

class Solution {

    static class Node{
        int parentIndex;
        int currentIndex;
        int flag;
        TreeNode currentNode;

        public Node(int parentIndex, int currentIndex, int flag, TreeNode currentNode) {
            this.parentIndex = parentIndex;
            this.currentIndex = currentIndex;
            this.flag = flag;
            this.currentNode = currentNode;
        }
    }
    /**
     * https://blog.csdn.net/ouyang_peng/article/details/143254632
     *
     *
     * @param root TreeNode类
     * @param k int整型
     * @return TreeNode类
     */
    public TreeNode cyclicShiftTree (TreeNode root, int k) {
        // write code here

        List<List<Node>> levels = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(0,0,0,root));
        int parentNum = 1;
        int nextNum = 0;
        List<Node> tempLevel = new ArrayList<>();

        while (!queue.isEmpty()){
            Node node = queue.poll();
            tempLevel.add(node);
            parentNum--;
            if (node.currentNode.left!=null){
                queue.offer(new Node(node.currentIndex,nextNum++,0,node.currentNode.left));
            }
            if (node.currentNode.right!=null){
                queue.offer(new Node(node.currentIndex,nextNum++,1,node.currentNode.right));
            }
            if (parentNum==0){
                parentNum = nextNum;
                nextNum = 0;
                levels.add(tempLevel);
                tempLevel = new ArrayList<>();
            }
        }

        int depth = levels.size()-1;
        for (int i = depth; i > 0; i--) {
            List<Node> parentLev = levels.get(i - 1);
            int parentSize = parentLev.size();

            for (Node node : parentLev) {
                node.currentNode.left=null;
                node.currentNode.right=null;
            }

            int move = k%(2*parentSize);
            tempLevel = levels.get(i);
            for (Node node : tempLevel) {
                int targetIndex = node.flag == 0 ? (node.parentIndex + move / 2) % parentSize : (node.parentIndex + (move + 1) / 2) % parentSize;
                int targetFlag = node.flag == 0?move%2:(move+1);
                Node targetP = parentLev.get(targetIndex);
                if (targetFlag==0){
                    targetP.currentNode.left = node.currentNode;
                }else {
                    targetP.currentNode.right = node.currentNode;
                }
            }
        }

        return root;
    }
}
