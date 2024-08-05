package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/4 16:38
 * @注释
 */
public class LeetCode376 {

    @Test
    public void test()
    {
        System.out.println(wiggleMaxLength(new int[]{0,0}));
        System.out.println(wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8}));

    }


    public int wiggleMaxLength(int[] nums)
    {
        if (nums.length <= 1) {
            return nums.length;
        }
        int res = 1;
        int preSub = 0;
        int nowSub = 0;
        for (int i = 1; i < nums.length; i++) {
            nowSub = nums[i] - nums[i - 1];
            if ((nowSub > 0 && preSub <= 0) || nowSub < 0 && preSub >= 0) {
                res++;
                preSub = nowSub;
            }
        }
        return res;
    }

//    public int wiggleMaxLength(int[] nums)
//    {
//        if (nums.length <= 1) {
//            return nums.length;
//        }
//
//        int left = 1;
//        int right = 2;
//        int res = 1;
//        boolean up = nums[1] - nums[0] > 0;
//        if (nums[1] - nums[0] != 0) {
//            res++;
//        }
//        while (right < nums.length) {
//            if (nums[right] < nums[left] && up) {
//                // 是要的
//                res++;
//                up = false;
//                left++;
//            }else if (nums[right] > nums[left] && !up){
//                // 是要的
//                res++;
//                up = true;
//                left++;
//            }
//            right++;
//        }
//        return res;
//    }

}
