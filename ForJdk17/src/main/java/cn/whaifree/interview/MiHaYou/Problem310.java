package cn.whaifree.interview.MiHaYou;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/25 13:23
 * @注释
 */
public class Problem310 {

    @Test
    public void test() {
        jumpSLM(3, new int[]{1, 0, 1});
    }


    /**
     *
     * @param n n个格子 1-n
     * @param direct 第i只史莱姆的跳跃方向 0表示往左，1表示往右
     */
    public void jumpSLM(int n, int[] direct) {

        int[] space = new int[n];
        Arrays.fill(space, 1);

        // 表示1-n秒
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (direct[j] == 1) {
                    //右跳
                    if (j + i + 1 < n) {
                        space[j + i] -= 1;
                        space[j + i + 1] += 1;
                    } else if (j + i + 1 == n) {
                        //边界条件
                        space[j + i] -= 1;
                    }
                }else {
                    //左跳
                    if (j - (i + 1) >= 0) {
                        space[j - i] -= 1;
                        space[j - (i + 1)] += 1;
                    } else if (j - (i + 1) == -1) {
                        //边界条件
                        space[j - i] -= 1;
                    }
                }
            }
            System.out.println(Arrays.toString(space));
        }

    }




}
