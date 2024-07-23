package cn.whaifree.redo.redo_all_240721;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/22 23:12
 * @注释
 */
public class LeetCode287
{
    public static void main(String[] args)
    {
        int[] nums = {3,1,3,4,2};
        System.out.println(findDuplicate(nums));
        int[] nums2 = {1,3,4,2,2};
        System.out.println(findDuplicate(nums2));
        int[] nums3 = {1,1};
        System.out.println(findDuplicate(nums3));
    }

    /**
     * 包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）
     *
     * 3,1,3,4,2
     * 0 1 2 3 4
     *
     * fast = nums[nums[fast]]
     * slow = nums[slow]
     *
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums)
    {
        // 快慢指针
        int fast = 0;
        int slow = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (slow != fast);

        fast = 0;

        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
