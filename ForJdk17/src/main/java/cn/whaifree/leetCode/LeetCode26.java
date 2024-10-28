package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 *  删除有序数组中的重复项
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 *
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * 判题标准:
 *
 * 系统会用下面的代码来测试你的题解:
 *
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按 非严格递增 排列
 */
public class LeetCode26 {

    public int removeDuplicates(int[] nums) {
        // 保证return k 的k nums 前k个为唯一递增的
        int left = 0;  // 左指针，指向无重复元素数组的起始位置
        int right = 1;  // 右指针，指向当前元素数组的位置
        while (right < nums.length) {
            if (nums[right] == nums[left]) {  // 如果右指针指向的元素等于左指针指向的元素
                right++;  // 则右指针向右移动
            } else {
                nums[++left] = nums[right++];  // 若不相等，则将当前元素放入无重复元素数组中，并同时将左指针和右指针向右移动
            }
        }
        return left + 1;  // 返回无重复元素数组的长度
    }

    public int removeDuplicates1(int[] nums) {
        // 保证return k 的k nums 前k个为唯一递增的
        int left = 0;
        int right = 1;
        int jump = 0;
        while (right < nums.length) {
            if (nums[right] == nums[left]) {
                right++;
                jump++;
            } else {
                nums[left + 1] = nums[right];
                right++;
                left++;
            }
        }
        return left + 1;
    }


    @Test
    public void test() {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }
}
