package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/8 11:23
 * @注释
 */
public class LeetCode42 {

    @Test
    public void test()
    {

        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = new Solution2().trap(height);
        System.out.println(trap);
    }

    class Solution {
        /**
         * 从左到右 获得每个位置的左边最高
         * 从右到左 获得每个位置的右边最高
         *
         * 取交集
         *
         * @param height
         * @return
         */
        public int trap(int[] height) {
            int[] leftHeight = new int[height.length];
            int leftH = 0;
            for (int i = 0; i < height.length; i++) {
                leftH = Math.max(leftH, height[i]);
                leftHeight[i] = leftH;
            }
            int[] rightHeight = new int[height.length];
            int rightH = 0;
            for (int i = height.length - 1; i >= 0; i--) {
                rightH = Math.max(rightH, height[i]);
                rightHeight[i] = rightH;
            }

            int res = 0;
            for (int i = 0; i < height.length; i++) {
                res += Math.min(rightHeight[i], leftHeight[i]) - height[i];
            }
            return res;
        }
    }

    class Solution2{
        /**
         * 关键在于
         * - i值 < 栈顶值 入栈
         *      【3 2 1】 递减，下一个遇到5时，先计算left=2 mid=1 right=5 的容积,再while left=3 mid=1 right=5的容积
         * - i值 > 栈顶值 需要计算i为槽时的容积
         * - i值 == 栈顶值 更新
         * @param height
         * @return
         */
        public int trap(int[] height) {
            // 单调栈 找到右边第一个高于他的墙
            // 保证栈内元素，从上到下为递增的，这样就能获取到槽的两边，并且确保不会有一边为空的
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int res = 0;
            for (int i = 1; i < height.length; i++) {
                if (height[i] > height[stack.peek()]) {
                    // 遇到凹陷
                    //pop up all lower value
                    int heightAtIdx = height[i]; // 右边墙的高度
                    // 不断判断左边的墙，因为如果左边的墙存在，必然大于上一个左墙   如height 3 2 1 5
                    while (!stack.isEmpty() && (heightAtIdx > height[stack.peek()])){
                        int mid = stack.pop(); // 中间槽的下标

                        if (!stack.isEmpty()){
                            int left = stack.peek(); // 左边墙的下标

                            int h = Math.min(height[left], height[i]) - height[mid];
                            int w = i - left - 1;
                            int hold = h * w;

                            if (hold > 0) res += hold;
                        }
                    }
                    stack.push(i);


                } else if (height[i] == height[stack.peek()]) {
                    stack.pop();
                    stack.push(i);
                }else {
                    stack.push(i);
                }
            }

            return res;
        }
    }
}
