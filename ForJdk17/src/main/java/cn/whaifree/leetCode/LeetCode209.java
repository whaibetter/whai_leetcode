package cn.whaifree.leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/20 10:16
 */
public class LeetCode209 {

    public int minSubArrayLen(int target, int[] nums) {

        //滑动窗口
        Queue<Integer> queue = new LinkedList<Integer>();
        //标记最短长度
        int shortest = 100000;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {


            queue.add(nums[i]);
            if (sum + nums[i] >= target) {
                //达标了，需要将shortest更新为最短的
                if (shortest>queue.size()){
                    shortest = queue.size();
                }
                //sum减去上一个
                sum -= queue.poll();
            }
            sum += nums[i];
        }

        if (queue.size()==nums.length){
            return 0;
        }
        return shortest;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode209().minSubArrayLen2(8, new int[]{1,1,1,1,1,1,1,1}));
    }

    public int minSubArrayLen1(int target, int[] nums) {
        //使用队列，不停得入队，直到大于target再不停出队直到小于target
        Queue<Integer> queue = new LinkedList<>();

        int sum = 0;
        int shortest = 100000;
        int nowSum = 0;
        for (int i = 0; i < nums.length; i++) {


            sum += nums[i];

            //入队
            queue.add(nums[i]);
            //队列内的值之和
            nowSum += nums[i];

            while (nowSum >= target) {
                if (shortest > queue.size()){
                    shortest = queue.size();
                }
                //不停出队
                nowSum -= queue.poll();
            }
        }

        //判断nums之和小于target的情况
        if (sum < target) {
            return 0;
        }

        return shortest;
    }


    /**
     * 使用nums数组作为队列
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {

        int leftIndex = 0, rightIndex = 0;
        int shortest = 100000;
        int nowSum = 0;
        for (int i = 0; i < nums.length; i++) {

            nowSum += nums[i];
            //入队
            rightIndex++;
            while (nowSum >= target){
                //更新shortest
                shortest = Math.min(shortest, rightIndex - leftIndex);
                nowSum -= nums[leftIndex++];
                //出队
            }
        }

        if (nums.length==rightIndex-leftIndex){
            return 0;
        }
        return shortest;

    }

}
