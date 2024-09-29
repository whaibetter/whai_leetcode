package cn.whaifree.leetCode.Hash;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和

 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 // 参考LeetCode15题
 *
 */
public class LeetCode18 {
    @Test
    public void test() {
//        System.out.println(new Solution().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(1000000000+1000000000+1000000000+1000000000); // -294967296
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 5, 6, 7));
        expected.add(Arrays.asList(3, 4, 6, 7));
        expected.add(Arrays.asList(3, 5, 6, 6));
        System.out.println(new Solution().fourSum(nums, target));
    }

    /**
     * TODO 没完全做出来
     * 和LeetCode 15题类似，但需要考虑 2 2 2 2 2 target=8 这样去重的情况
     */
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            Arrays.sort(nums);

            List<List<Integer>> lists = new ArrayList<>();

            // 第一个数>0，并且>目标值，递增的数组，一定为空
            /**
             *  int[] nums = {1000000000,1000000000,1000000000,1000000000};
             *  int target = -294967296;
             *  不加这段代码，这个用例会报错
             *  System.out.println(1000000000+1000000000+1000000000+1000000000); // -294967296
             *  因为： -109 <= nums[i] <= 109
             *        -109 <= target <= 109
             */
            if (nums[0] > 0 && nums[0] > target) {
                return lists;
            }

            for (int i = 0; i < nums.length - 3; i++) {



                // TODO 保证能够进入一次循环 i>0
                if (i > 0 && nums[i] == nums[i - 1]) {
                    // 去重
                    continue;
                }
                for (int j = i + 1; j < nums.length - 2; j++) {

                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        // 这里需要考虑这样一种情况 [-2, -1, 0, 0, 1, 2] 如果用nums[j] == num[j + 1] 判断 -2 0 0 2 这种会被忽略
                        // 去重可以考虑向前或者向后去重，注意<p>去重是要让指针和已经指过的对比</p>

                        continue;
                    }


                    int left = j + 1;
                    int right = nums.length - 1;

                    while (left < right) {
                        int sum = nums[right] + nums[left] + nums[i] + nums[j];
                        if (sum > target) {
                            right--;
                        } else if (sum < target) {
                            left++;
                        } else {
                            lists.add(Arrays.asList(nums[right], nums[left], nums[i], nums[j]));
                            while (left != right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            while (left != right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            right--;
                            left++;
                        }
                    }


                }
            }

            return lists;
        }

    }
}
