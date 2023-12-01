package cn.whaifree.leetCode;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/11 19:14
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer03 {
    public static void main(String[] args) {


//        System.out.println(new Solution1().findRepeatNumber5(new int[]{1, 2, 3,2, 3}));

    }


}

class Solution1 {

    /**
     * 1. 找一个num长度的数组n，遍历统计，n内大于1输出
     * 2. Map key为数字，value自增，遍历Map获得value>1的key
     * 3. 先排序，再向下找重复
     */

    /**
     * Map方法
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int integer = 0;
        for (int num : nums) {
            if (map.containsKey(num)){
                //原来是有这个数字的
                integer = map.get(num);
                map.put(num,++integer);
            }else {
                //没有这个数字
                map.put(num,1);
            }
        }

        /**
         * 遍历map的方法
         * 1. 遍历keySet的Set<K>
         * 2. 遍历Values，只能遍历Value
         * 3. 遍历entrySet()
         * 4. forEach遍历
         */
        //遍历map的key
//        System.out.println("1. 遍历keySet的Set<K>");
        for (Integer i : map.keySet()) {
            if (map.get(i)>1){
                System.out.println(i);
            }
        }

//        System.out.println("3. 遍历entrySet()");
//        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
//            System.out.println("key = " + integerIntegerEntry.getKey());
//            System.out.println("value = " + integerIntegerEntry.getValue());
//        }
//
//        System.out.println("4. forEach遍历");
//        map.forEach((key,value)->{
//            System.out.println("key is "+ key);
//            System.out.println("value is "+ value);
//        });

        return 0;
    }

    /**
     * 排序向下找重复
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        //冒泡排序
        boolean flag = true;
        int c=0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length-i-1; j++) {
                if (nums[j] > nums[j+1]) {
                    c = nums[j+1];
                    nums[j+1] = nums [j];
                    nums[j] = c;

                }
            }
        }

        /*for (int num : nums) {
            System.out.println(num);
        }*/
        //排序完成

        /**
         * 在已经排序的里面找到有出现大于两次的
         */
        return 0;
    }

    /**
     *
     * 完成
     *
     * 题目，找出任意一个重复的数字。
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)){
                //原来是有这个数字的，证明不是第一次出现了，直接输出就是结果
                return num;
            }else {
                //没有这个数字,首次出现
                map.put(num,1);
            }
        }
        return 0;

    }


    /**
     * 使用HashSet
     * @param nums
     * @return
     */

    public int findRepeatNumber4(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)){
                //有这个数字直接输出
                return num;
            }else {
                //没有这个数字就增加在set中
                set.add(num);
            }
        }
        return 0;
    }

    /**
     * 下标法：不停交换元素使得元素和对应下标相等。
     *  nums[i]=i
     *  nums[nums[i]] = nums[i] 下标与元素值相等
     *
     * 通过索引映射对应的值，起到与字典等价的作用。
     * 长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。即能够确定索引的大小。
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber5(int[] nums){

        int flag = 0;
        int i = 0;
        while (i < nums.length){

            //遍历每个数
            //1. 不在正确位置上，进行交换
            //  如果交换的位置已经有正确的数字，直接输出结果
            //2. 在正确的位置上，continue

            if (nums[i]!=i){
                //需要进行交换
                if (nums[nums[i]]==nums[i]){
                    //在该位置已经有正确的位置提前占好了
                    return nums[i];
                }
                //交换 nums[nums[i]]和nums[i]
                flag = nums[i];
                nums[i] = nums[nums[i]];
                nums[flag] = flag;
            }else {
                i++;
            }


        }

        return -1;
    }


}