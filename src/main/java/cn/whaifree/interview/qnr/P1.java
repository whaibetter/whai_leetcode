package cn.whaifree.interview.qnr;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/6 18:25
 * @注释
 */
public class P1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] nums = new int[num];
        for (int i = 0; i < num; i++) {
            nums[i] = in.nextInt();
        }


        method(nums,nums.length);
        in.close();
        for (Integer i : min) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    static List<Integer> min = null;

    static List<Integer> path = new ArrayList<>();

    public static void method(int[] nums,int size) {
        if (size <= 0) {
            if (min == null) {
                min = new ArrayList<>(path);
                return;
            }
            if (isPre(min, path)) {
                min = new ArrayList<>(path);
            }
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            if (isPre(min, path)) {
                continue;
            }
            path.add(nums[i]);
            method(nums, size - 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * a 的字典序列>b
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isPre(List<Integer> a, List<Integer> b) {
        if (a == null || b == null) {
            return false;
        }
        int minLen = Math.min(a.size(), b.size());
        for (int i = 0; i < minLen; i++) {
            Integer c1 = a.get(i);
            Integer c2 = b.get(i);
            // -1 2
            if (c1 < 0 && c2 > 0) {
                return false;
            } else if (c1 > 0 && c2 < 0) {
                return true;
            }

            if (c1 > c2) {
                return true;
            } else if (c1 < c2) {
                return false;
            }
        }
        return false;
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(-1, 1, 2);
        List<Integer> list1 = Arrays.asList(-1, 2, 1);
        System.out.println(isPre(list1, list));
    }




}

class p2{



    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        long sum = in.nextLong();
        long[] a = new  long[num];
        for (int i = 0; i < num; i++) {
            a[i] = in.nextInt();
        }

        long[] b = new long[num];
        for (int i = 0; i < num; i++) {
            b[i] = in.nextInt();
        }

        method(a, b, sum);
    }

    /**
     *
     * dp
     *
     * @param A
     * @param B
     * @param sum
     */
    public static void method(long[] A, long[] B, long sum) {

        /**
         * 二分查找
         *
         */

        int left = 0;
        int right = A.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (maxP(A, B, left, right)>sum) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }


        int minLen = 0;

        for (int i = 0; i < A.length; i++) {
            long x = maxP(A, B, 0, i);
            if (x >= sum) {
                minLen = Math.min(minLen, i + 1);
                break;
            }
        }
        System.out.println(minLen == Integer.MAX_VALUE ? -1 : minLen);

    }

    /**
     * 检查
     * @param A
     * @param B
     * @param left
     * @param right
     * @return
     */
    public static long maxP(long[] A, long[] B, int left, int right) {
        long[] tmpA = Arrays.copyOfRange(A, left, right + 1);
        long[] tmpB = Arrays.copyOfRange(B, left, right + 1);
        Arrays.sort(tmpA);
        Arrays.sort(tmpB);
        long sum = 0;
        for (int i = 0; i < tmpA.length; i++) {
            sum += tmpA[i] * tmpB[i];
        }
        return sum;
    }



}

class p3{

    public static void main(String[] args) {
        System.out.println((int)'9'-'0');

    }

    /**
     * 有多少个子串，子串内部每个数字的数量都相等数量相等
     * @param string
     */
    public static void method(String string) {
        char[] charArray = string.toCharArray();
        /**
         * 截止key每个位置
         * value，对应数字的数量
         */
        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < charArray.length; i++) {
            if (map.containsKey(i)) {
                map.get(i)[charArray[i] - '0'] += 1;
            }else {
                int[] value = new int[10];
                value[charArray[i] - '0'] = 1;
                map.put(i, value);
            }
        }
    }


}
