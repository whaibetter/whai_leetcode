package cn.whaifree.interview.qiuzhao;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/18 18:55
 * @注释
 */
public class ByteDance {



}

class ByteDancep1{
    /**
     * 字数组
     * 4111 411 就是满足要求的
     * 字数组特殊数组个数
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        int[] nums = new int[i];
        for (int j = 0; j < i; j++) {
            nums[j] = in.nextInt();
        }
        nums = new int[]{2,2,3,2};
        method(nums);

    }

    public static void method(int[] nums) {

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 2; j < nums.length; j++) {
                count += display(nums, i, j);
            }
        }
        System.out.println(count);
    }

    public static int display(int[] nums, int start, int end) {
        if (end - start < 2) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.keySet().size() >= 3) {
                return 0;
            }
        }

        return 1;
    }

}



class ByteDance2{
    /**
     * n个事件，最大钻石数量m （1 <= n,m<= 1000）
     *
     * 每个事件有 x，y，z
     * 有俩个选择
     * 1 消耗 x钻石 获得 y金币
     * 2 获得 z钻石
     *
     * 钻石数量超出m的部分会消失，最多拥有m个钻石
     *
     * 事件结束后，可以将钻石1比1换成金币
     *
     * 问最多能得到多少金币
     *
     * 作者：Jovanko
     * 链接：https://www.nowcoder.com/feed/main/detail/e7e32ba86ff44c0aa40443c007991474?sourceSSR=search
     * 来源：牛客网
     *
     * 多重背包
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int thing = in.nextInt();
        int limit = in.nextInt();

        int[][] events = new int[thing][3];
        for (int i = 0; i < thing; i++) {
            events[i][0] = in.nextInt();
            events[i][1] = in.nextInt();
            events[i][2] = in.nextInt();}

        in.close();

        int[][] dp = new int[thing + 1][limit + 1];
        for (int i = 1; i <= thing; i++) {
            int[] event = events[i - 1];
            int x = event[0]; // 消耗x
            int y = event[1]; // 获得y个金币
            int z = event[2]; // 直接获取z个宝石
            for (int j = 0; j <= limit; j++) {
                dp[i][j] = dp[i - 1][j];
                // 消耗宝石
                if (j >= x) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - x] + y);
                }
                // 换宝石
                if (j + z <= limit) {
                    dp[i][j + z] = Math.max(dp[i][j + z], dp[i - 1][j]);
                }
            }
        }

        int maxCoin = 0;
        for (int i = 0; i <= limit; i++) {
            maxCoin = Math.max(maxCoin, dp[thing][i]);
        }
    }


}



class ByteDancep3{
    static int left = 0;
    static int right = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int num = in.nextInt();
        int sum = 0;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = in.nextInt();
            sum += nums[i];
        }
        right = nums.length - 1;

        for (int i = 0; i < num; i++) {
            String opr = in.next();
            Integer oprLen = Integer.valueOf(in.next());
            int value = 0;
            if (oprLen > num) {
                value += sum * (oprLen / len);
                oprLen %= len;
            }


            if (opr.equals("L")) {
                for (int j = 0; j < oprLen; j++) {
                    value += nums[left % len];
                    left++;
                }
            }else {
                for (int j = 0; j < oprLen; j++) {
                    if (right < 0) {
                        right = right + len;
                    }
                    value += nums[right];
                    right--;
                }
            }
            System.out.println(value);
        }

    }

    public static int cut(int[] nums, String opr, int len) {

        int res = 0;
        if (opr.equals("L")) {
            res += calLeft(nums, left, len);
            left += len;
            while (left > nums.length) {
                left %= nums.length;
            }
        }else {
            res += calRight(nums, right, len);
            right -= len;
            while (right < 0) {
                right += nums.length;
            }
        }
        System.out.println(res);

        return res;
    }

    public static int calRight(int[] nums, int end, int count) {
        int res = 0;
        while (count > 0) {
            res += nums[end];
            end--;
            count--;
            if (end < 0) {
                end += nums.length;
            }
        }
        return res;
    }

    public static int calLeft(int[] nums, int start, int count) {
        int res = 0;
        while (count >= 0) {
            res += nums[start];
            start++;
            count--;
            if (start > nums.length) {
                start -= nums.length;
            }
        }
        return res;
    }


}
