package cn.whaifree.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/13 20:54
 */
public class LeetCode001 {




    /**
     * map存储，key为nums内的值，value为下标
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int key;
        for (int i = 0; i < nums.length; i++) {
            //如果包含匹配的元素即输出下标

            key = target - nums[i];
            if (map.containsKey(key)){
                return new int[]{map.get(key),i};
            }else {
                map.put(nums[i],i);
            }
            //不包含匹配的元素则增加到map中
        }

        return null;

    }

    public static void main(String[] args) {
        int[] ints = new LeetCode001().twoSum(new int[]{2, 7, 11, 15}, 13);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
