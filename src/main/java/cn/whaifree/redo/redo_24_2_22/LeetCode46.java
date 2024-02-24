package cn.whaifree.redo.redo_24_2_22;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/23 13:19
 * @注释
 */
public class LeetCode46 {

    @Test
    public void test() {
        new Solution().permute(new int[]{1, 2, 3}).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution
    {

        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = null;
        public List<List<Integer>> permute(int[] nums) {
            used = new boolean[nums.length];
            backTracking(nums);
            return res;
        }

        void backTracking(int[] nums) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                // 跳过子树路径上同一个
                if (used[i] == true) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                backTracking(nums);
                used[i] = false;
                path.removeLast();
            }


        }
    }

    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }

}
