package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 13:57
 * @注释
 */
public class LeetCode376 {

    @Test
    public void test() {
        int[] nums = {1,1,7,4,9,2,5};
        Solution solution = new Solution();
        int res = solution.wiggleMaxLength(nums);
        System.out.println(res);
    }


    class Solution {

        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }

            int pre = 0; // 0是可以进入的

            int right = 1;
            int res =  1; // 注意第一个
            while (right < nums.length) {
                int now = nums[right] - nums[right - 1];
                if (now < 0 && pre >= 0) { // 如果本次<0，上个区间>0
                    res++;
                    pre = now;
                    right++;
                } else if (now > 0 && pre <= 0) { // 如果本次>0，上个区间<0
                    res++;
                    pre = now;
                    right++;
                } else {
                    // 如果上一个区间和本区间都是递增或者递减
                    right++;
                }
            }
            return res;
        }

//        public int wiggleMaxLength(int[] nums) {
//            if (nums.length <= 1) {
//                return nums.length;
//            }
//
//            boolean up = nums[1] - nums[0] > 0;
//
//            int left = 1;
//            int right = 2;
//            int res = nums[1] - nums[0] != 0 ? 2 : 1; // 注意第一个
//            while (right < nums.length) {
//                int now = nums[right] - nums[left];
//                if (now < 0 && up) { // 如果本次<0，上个区间>0
//                    res++;
//                    up = !up;
//                    left++;
//                    right++;
//                }else if (now > 0 && !up) { // 如果本次>0，上个区间<0
//                    res++;
//                    up = !up;
//                    left++;
//                    right++;
//                }else {
//                    // 如果上一个区间和本区间都是递增或者递减
//                    right++;
//                }
//            }
//            return res;
//        }


    }

    @Test
    public void test1() {
        int[] nums = {0,0};
        Solution1 solution1 = new Solution1
                ();
        System.out.println(solution1.wiggleMaxLength(nums));
    }

    class Solution1 {
        /**
         * up[i] 表示0-i最长上升摆动序列的长度（可以不包括最后一个元素，只要这个序列的最后一个摆动是上升的）
         *      up[i-1] 不是摇摆
         *      max( up[i-1], down[i-1] +1)  nums[i] < nums[i-1]  表示递减，则使用前面的最长序列+1
         *      之前的序列可能更长，或者本down+1
         * down[i] 表示0-i最长下降摆动序列
         *      down[i-1]
         *      max ( down[i-1], up[i-1]+1)
         * @param nums
         * @return
         */
        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }
            int[] down = new int[nums.length];
            int[] up = new int[nums.length];
            down[0] = 1;
            up[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) { // 递增
                    down[i] = Math.max(down[i - 1], up[i - 1] + 1);
                    up[i] = up[i - 1];
                } else if (nums[i] < nums[i - 1]) {
                    up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                    down[i] = down[i - 1];
                }else {
                    up[i] = up[i - 1];
                    down[i] = down[i - 1];
                }
            }
            return Math.max(down[nums.length - 1], up[nums.length - 1]);
        }
    }
}
