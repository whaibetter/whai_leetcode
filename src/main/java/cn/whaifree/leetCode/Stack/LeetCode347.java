package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 *
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/10 11:39
 * @注释
 *
 * 347. 前 K 个高频元素

 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 *
 */
public class LeetCode347 {

    @Test
    public void test() {
        for (int i : new Solution1().topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2)) {
            System.out.println(i);
        }


    }

    class Solution {

        /**
         * 先将 值 和 频率 存储在hashMap中
         * 使用一个大顶堆
         *      将前k个值导入，就形成了一个大顶堆（这个大顶堆维持k个）
         *      后面的数据挨个导入，
         *          - 如果比当前最大值大，去除堆顶（堆顶是最小的值，比最小的值还大）
         *          - 导入
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] topKFrequent(int[] nums, int k) {

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            // 小顶堆
            PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    // 一定要分清 0 正数 负数
                    return map.get(o1) - map.get(o2);
                }
            });
            map.keySet().forEach(key -> {
                // 导入前k个数据，作为基准堆
                if (heap.size() < k) {
                    heap.add(key);
                } else if (map.get(key) > map.get(heap.peek())) {
                    // k个之外的数，如果频率比堆顶（目前堆频率最小的值）要大，就删除堆顶，加入此数（会自动调整到合适的位置）
                    heap.remove(); // 等于poll
                    heap.add(key);
                }
            });

            int[] res = new int[k];
            int index = 0;
            for (Integer integer : heap) {
                res[index++] = integer;
            }
            return res;
        }
    }

    class Solution1 {
        /**
         * 全部导入大顶堆，获取前k个就好了
         * @param nums
         * @param k
         * @return
         */
        public int[] topKFrequent(int[] nums, int k) {

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            // 大顶堆
            PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });

            // 将所有entry存入堆
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                heap.add(new int[]{entry.getKey(), entry.getValue()});
            }

            int[] res = new int[k];
            // 获取堆的前k个数据
            for (int i = 0; i < k; i++) {
                res[i] = heap.poll()[0];
            }

            return res;

        }
    }
}
