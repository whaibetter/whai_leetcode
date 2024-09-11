package cn.whaifree.interview.XieChen;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/5 19:09
 * @注释
 */
public class p1 {

    @Test
    public void test1() {

//        Map<String,Integer> map =countOrdersPerUser(orders);
        String maxUser = null;
        Integer max = 0;

    }

    @Test
    public void test() {
        System.out.println(cal(new int[]{0}, 0, 0));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        String next = in.next();
        String[] split = next.split("");
        int[] nums = new int[i];
        for (int i1 = 0; i1 < split.length; i1++) {
            nums[i1] = Integer.parseInt(split[i1]);
        }
        int method = method(nums);
        System.out.println(method);

    }

    public static int method(int[] nums) {

        // 权重为0的数量


        int count = 0;
        int length = nums.length;
        for (int start = 0; start < length; start++) {
            for (int len = 0; start + len < length; len += 2) {
                int weight = cal(nums, start, start + len);
                if (weight % 2 == 1) {
                    count++;
                }
            }
        }

        return count;

    }


    public static int cal(int[] nums, int start, int end) {
        int weight = 0;
        int last = 1;
        for (int i = start; i <= end; i++) {
            if (nums[i] != last) {
                weight++;
                last = nums[i];
            }
        }


        return weight;
    }


}


class p442{

    public static void main(String[] args) {
        System.out.println(method(7 ,4, 7653));

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        System.out.println(method(n, m, k));
    }




    static int nowSum = 0;
    static int res = 0;
    static Set<Integer> set = new HashSet<>();
    /**
     *
     * @param n 背包物品
     * @param m
     * @param k
     * @return
     */
    public static int method(int n, int m, int k) {
        circle(n, 0, m, k);
        return res;
    }


    public static void circle(int n, int start, int m, int k) {

        if (m < 0) {
            return;
        }

        if (nowSum > k) {
            res++;
            System.out.println(nowSum);
        }

        for (int i = 0; i <= n; i++) {
            if (set.contains(i)) {
                continue;
            }
            set.add(i);
            int tmp = nowSum;
            nowSum = nowSum * 10 + i;
            circle(n, i + 1, m - 1, k);
            nowSum = tmp;
            set.remove(i);
        }

    }


}

class p3{

    public static void main(String[] args) {

    }

}

class p4{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int sum = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }


        int res = 0;
        for (int i = 0; i <= n - k; i++) {
            int start = i;
            int end = i + k - 1;
            int tmpSum = 0;
            for (int j = start; j <= end; j++) {
                tmpSum += nums[j];
            }

            int needSub = tmpSum - sum;
            int reduceIndex = end;
            int count = 0;
            while (needSub > 0) {
                if (needSub > nums[reduceIndex]) {
                    count += nums[reduceIndex];
                    needSub -= nums[reduceIndex];
                    nums[reduceIndex] = 0;
                    reduceIndex--;
                } else {
                    count += needSub;
                    nums[reduceIndex] -= needSub;
                    needSub = 0;
                }
            }
            res += count;
        }
        System.out.println(Arrays.toString(nums));

        System.out.println(res);
    }
}
