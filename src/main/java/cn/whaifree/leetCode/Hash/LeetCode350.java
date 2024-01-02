package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。*
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class LeetCode350 {

    @Test
    public void test() {
        int[] nums2 = {4,9,9,5};
        int[] nums1 = {9,4,9,8,4};
        int[] res = new Solution2().intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {

            int[] A = new int[1001];
            int[] B = new int[1001];
            for (int i : nums1) {
                A[i]++;
            }
            for (int i : nums2) {
                B[i]++;
            }

            int[] ans = new int[1001];
            for (int i = 0; i < A.length; i++) {
                ans[i] = Math.min(A[i], B[i]);
            }


            ArrayList<Integer> objects = new ArrayList<>();
            for (int i = 0; i < ans.length; i++) {
                for (int j = 0; j < ans[i]; j++) {
                    objects.add(i);
                }
            }

            return objects.stream().mapToInt(i -> i).toArray();
        }
    }

    class Solution1 {
        /**
         * 计算两个数组的交集
         * @param nums1 第一个数组
         * @param nums2 第二个数组
         * @return 一个包含交集元素的数组
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            // 创建一个长度为1001的数组A，并初始化为0
            int[] A = new int[1001];

            // 遍历第一个数组，将数组中的元素作为索引将对应索引的值加1
            for (int i : nums1) {
                A[i]++;
            }

            // 创建一个长度为第一个数组和第二个数组中较小长度的数组res，用于存储交集元素
            int[] res = new int[Math.min(nums1.length, nums2.length)];
            // 初始化索引为0
            int index = 0;

            // 遍历第二个数组，判断对应索引的值是否为0
            // 如果不为0，说明该元素在第一个数组中出现过，将该元素加入res数组中，并将对应索引的值减1
            // 同时将索引加1
            for (int i : nums2) {
                if (A[i] != 0) {
                    res[index++] = i;
                    A[i]--;
                }
            }

            // 截取res数组中实际的长度，并返回结果
            return Arrays.copyOfRange(res, 0, index);
        }
    }

    class Solution2 {
        /**
         * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
         * @param nums1 第一个数组 长
         * @param nums2 第二个数组 短
         * @return 一个包含交集元素的数组
         */
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length < nums2.length) {
                return intersect(nums2, nums1);
            }

            // 排序
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            int[] res = new int[nums2.length];
            int index = 0 ;
            int indexA = 0;
            int indexB = 0;
            // 使用两个指针，不断向前移动，如果哪个指针所指的数小了，就让他向前移动
            while (index < res.length && indexB < nums2.length && indexA < nums1.length) {
                if (nums1[indexA] == nums2[indexB]) {
                    res[index++] = nums1[indexA++];
                    indexB++;
                } else if (nums1[indexA] < nums2[indexB]) {
                    indexA++;
                } else if (nums1[indexA] > nums2[indexB]) {
                    indexB++;
                }
            }

            return Arrays.copyOfRange(res, 0, index);
        }
    }
}
