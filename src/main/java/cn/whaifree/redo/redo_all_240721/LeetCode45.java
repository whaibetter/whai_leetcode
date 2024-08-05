package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/4 21:52
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test()
    {
        int[] nums = {1,2,3};
        System.out.println(jump1(nums));
    }

    /**
     * 2,3,5,0,1,4
     * [2 3 5] 内只会有一个能到最远的
     * @param nums
     * @return
     */
    public int jump(int[] nums)
    {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int maxCover = 0;
        int minJump = 0;
        while (left < nums.length) {
            maxCover = Math.max(maxCover, left + nums[left]);
            if (left == right) {
                right = maxCover;
                minJump++;
            }
            if (right >= nums.length - 1) {
                break;
            }
            left++;
        }
        return minJump;
    }

    public int jump1(int[] nums)
    {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int nowJump = 0;
        int minJump = Integer.MAX_VALUE;
        while (left < nums.length) {
            int tmpFar = left + nums[left];
            while (left <= right) {
                if (left + nums[left] > tmpFar) {
                    tmpFar = left + nums[left];
                }
                left++;
            }
            nowJump++;
            if (tmpFar >= nums.length - 1) {
                minJump = Math.min(minJump, nowJump);
                break;
            }
            right = tmpFar;
        }
        return minJump == Integer.MAX_VALUE ? 1 : minJump;
    }
}
