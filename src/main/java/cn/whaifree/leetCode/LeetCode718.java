package cn.whaifree.leetCode;

/**
 *
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 *
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 *
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/24 15:39
 */
public class LeetCode718 {

    public static void main(String[] args) {
        System.out.println(new LeetCode718().findLength(new  int[]{1,2,3,2,1},new int[]{3,2,1,4,7}));
    }


    /**
     * 两层循环 动态规划
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {

        //存储标记
        int db[][] = new int[nums1.length+1][nums2.length+1];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                //如果两个数组在某处重复，就在i-1,j-1的基础上+1，表示新已知最大子数组长度+1
                if (nums1[i]==nums2[j]){
                    db[i + 1][j + 1] = db[i][j] + 1;
                }
            }
        }

        //求得二维度最大值
        int max = 0;
        for (int[] ints : db) {
            for (int anInt : ints) {
                max = Math.max(max, anInt);
            }
        }

        return max;

    }


    /**
     * 默认nums1短 nums2长
     * @param nums1
     * @param nums2
     * @return
     *//*
    public int findLength2(int[] nums1, int[] nums2) {

        int max = 0;
        for (int i = 0; i < nums1.length; i++) {
            max = Math.max(max, findMaxInTwo(nums1, 0, nums2, nums2.length-i, i));
        }

        for (int i = 0; i < nums1.length; i++) {
            max = Math.max(max, findMaxInTwo(nums1, 0, nums2, nums2.length-i, i));
        }

        for (int i = 0; i < nums1.length; i++) {
            max = Math.max(max, findMaxInTwo(nums1, 0, nums2, nums2.length-i, i));
        }


    }

    *//**
     * 如 findMaxInTwo({2,3,4},1,{3,4,5},0,2) 就是从两个数组中找到字串为2的公共字串长度
     *
     *
     *         A: |*|*|*|*|
     *         B:   |*|*|*|*|*|*|
     *
     *
     * @param nums1
     * @param nums1Index
     * @param nums2
     * @param nums2Index
     * @param len
     * @return
     *//*
    int findMaxInTwo(int[] nums1,int nums1Index ,int[] nums2,int nums2Index,int len){
        //判断是否有重复子串

        int count = 0;
        //存放现在的匹配字串长度值
        int max = 0;
        for (int i = 0; i < len; i++) {
            //遇到相等的就当前匹配长度++
            if (nums1[nums1Index + i] == nums2[nums2Index + i]) {
                count++;

            } else {
                //遇到不相等的就判断一下当前匹配字串与最大匹配字串长度
                max = Math.max(max, count);
                count = 0;
            }
        }

        //找到最大匹配的字串长度
        return Math.max(max,count);

    }*/

}
