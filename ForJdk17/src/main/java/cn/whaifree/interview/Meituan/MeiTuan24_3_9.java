package cn.whaifree.interview.Meituan;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/23 14:28
 * @注释
 */
public class MeiTuan24_3_9 {

    /**
     * MT 是美团的缩写，因此小美很喜欢这两个字母。
     * 现在小美拿到了一个仅由大写字母组成字符串，她可以最多操作k次，每次可以修改任意一个字符。
     * 小美想知道，操作结束后最多共有多少个'M'和''字符?
     *
     * 输入描述
     *
     * 第一行输入两个正整数n,k，代表字符串长度和操作次数。第二行输入一个长度为n的、仅由大写字母组成的字符串。1<=k<=n<=10^5输出描述
     *
     * 输出操作结束后最多共有多少个'M'和'T'字符
     */
    static class Problem_1{

        public static void main(String[] args) {

            /**
             * next()方法读取到 空白符（空格，回车，tab） 就结束l；
             * nextLine()读取到 回车 结束也就是“\r”；
             */
            Scanner scanner = new Scanner(System.in);
            int n, k;
            n = scanner.nextInt(); //读取int 自动跳过空格、换行、制表符
            k = scanner.nextInt(); // 读取int
            String s = scanner.next(); // 读取空格、制表符和换行符
            // nextLine读取 回车 ；nextLine()自动读取了被next()去掉的Enter作为它的结束符
            // 解决方法：在每一个 next()、nextDouble() 、 nextFloat()、nextInt() 等语句之后加一个nextLine()语句，将被next()去掉的Enter等结束符过滤掉
            int isMT = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'M' || s.charAt(i) == 'T') {
                    isMT++;
                }
            }

            System.out.println(Math.min(isMT + k, s.length()));
        }



    }

    static class Problem_2{
        /**
         * 小美拿到了一个由正整数组成的数组，但其中有一些元素是未知的(用0来表示)。
         * 现在小美想知道，如果那些未知的元素在区间[l,r]范围内随机取值的话，数组所有元素之和的最小值和最大值分别是多少?
         * 共有q次询问。
         * 输入描述
         * 第一行输入两个正整数n,q，代表数组大小和询问次数。
         * 第二行输入n个整数ai，其中如果输入ai的为 0，那么说明ai是未知的。
         * 接下来的q行，每行输入两个正整数r，代表一次询问。
         *
         * @param args
         */
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int q = scanner.nextInt();
            int isUnKnow = 0;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                int j = scanner.nextInt();
                if (j == 0) {
                    isUnKnow++;// 未知个数
                }
                sum += j;
            }


            for (int i = 0; i < q; i++) {
                long left = scanner.nextInt();
                long right = scanner.nextInt();
                System.out.println(sum + left * isUnKnow + " " +(sum + right * isUnKnow));
            }
        }
    }

    static class Problem_3 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[][] ints = new int[n][n];
            for (int i = 0; i < n; i++) {
                String next = scanner.next();
                for (int j = 0; j < n; j++) {
                    ints[i][j] = next.charAt(j) - 48;
                }
            }
            for (int i = 1; i <= n; i++) {
                int cnt = 0;
                for (int x = 1; x <= n - i + 1; x++) {
                    for (int y = 1; y <= n - i + 1; y++) {
                        int sum = ints[x + i - 1][y + i - 1] - ints[x + i - 1][y - 1] - ints[x - 1][y + i - 1] + ints[x - 1][y - 1];
                        if (sum * 2 == i * i) {
                            cnt++;
                        }
                    }
                }
                System.out.println(cnt);
            }
        }

    }


    static class Problem_4 {

        /**
         * 区间删除
         * 小美拿到了一个大小为n的数组，
         * 她希望删除一个区间后，
         * 使得剩余所有元素的乘积末尾至少有k个 0。
         * 小美想知道，一共有多少种不同的删除方案?
         * @param args
         */
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] ints = new int[n];
            for (int i = 0; i < n; i++) {
                int j = scanner.nextInt();
                ints[i] = j;
            }

            staticYZ(ints, k);

        }

        public static void staticYZ(int[] ints,int k) {

            /**
             * 起始只要统计2和5的数量，用双指针，只要2和5的因子数量满足(2^p*5^p)>10^k p=min(i,j)
             * 即p>k就满足条件，并且此时指针变大一定会更大
             */
            // 统计全部的因子数量，和每个元素的2和5的因子数量
            int[] two = new int[ints.length];
            int[] five = new int[ints.length]; // 表示每个元素对应因子为5的数量
            int twoESum = 0;
            int fiveESum = 0;
            for (int i = 0; i < ints.length; i++) {
                int twoE = calcYZSL(ints[i], 2);
                int fiveE = calcYZSL(ints[i], 5);
                twoESum += twoE;
                fiveESum += fiveE;
                two[i] += twoE;
                five[i] += fiveE;
            }

//            for (int i = 0; i < ints.length; i++) {
//                int anInt = ints[i];
//                while (anInt % 2 == 0) {
//                    anInt /= 2;
//                    twoESum++;
//                    two[i]++;
//                }
//                while (anInt % 5 == 0) {
//                    anInt /= 5;
//                    fiveESum++;
//                    five[i]++;
//                }
//            }

            long res = 0;
            int left = 0;
            int right = 0;
            while (right < ints.length) {
                twoESum -= two[right]; // twoESum表示不在滑动窗口区间的2总因子数
                fiveESum -= five[right];
                while (Math.min(twoESum, fiveESum) < k && left <= right) {
                    // 如果右边存在的因子总数太小，则左边因子很大，
                    //          可以让Left指针右移动，直到满足小的范围，则此时的r-l+1就是一次res
                    twoESum += two[left];
                    fiveESum += five[left];
                    left++;
                }

                res +=(long) (right - left + 1);
                right++;
            }
            System.out.println(res);
        }

        /**
         * 计算num，因子k的数量
         * @param num
         * @param k
         * @return -1表示不是因子，否则返回其因子 10,5返回2
         */
        public static int calcYZSL(int num, int k) {
            int res = 0;
            while (num % k == 0) {
                num /= k;
                res++;
            }
            return res;
        }


        public  void delete(int[] nums, int k) {
            int left = 0;
            int right = 0;
            int pro = 1;
            int res = 0;
            while (right < nums.length) {
                pro *= nums[right];
                while (isHasKZero(pro, k)) {
                    res++;
                    pro /= nums[left];
                    left++;
                }
                right++;
            }
            System.out.println(res);
        }

//        public static void backTracking(int[] ints, int start, int sum, int k) {
////            if (isTrue(sum, k)) {
////                res++;
////            }else {
////                return;
////            }
//            if (start > ints.length) {
//                return;
//            }
//
//            for (int i = start; i < ints.length; i++) {
//                backTracking(ints, i + 1, sum / ints[i], k);
//            }
//        }

        public static boolean isHasKZero(int sum, int k) {
            for (int i = 0; i < k; i++) {
                if (sum % 10 == 0) {
                    sum /= 10;
                }else {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 关键在于数组能不能通过i直达j
     */
    static class Problem_5{

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int numberPerson = scanner.nextInt();
            int relation = scanner.nextInt();
            int operation = scanner.nextInt();
            boolean[][] isKnow = new boolean[numberPerson][numberPerson];
            for (int i = 0; i < relation; i++) {
                int j = scanner.nextInt()-1;
                int e = scanner.nextInt()-1;
                if (j > e) {
                    isKnow[e][j] = true;
                }else {
                    isKnow[j][e] = true;
                }
            }

            for (int i = 0; i < operation; i++) {
                int opr = scanner.nextInt();//操作类型
                int j = scanner.nextInt()-1;
                int e = scanner.nextInt()-1;
                if (opr == 1) {
                    if (j>e){
                        isKnow[e][j] = false;
                    }else {
                        isKnow[j][e] = false;
                    }
                }else{
                    if (judgeIsKnow(isKnow, j, e, 0) || judgeIsKnow(isKnow, e, j, 0)) {
                        System.out.println("yes");
                    } else {
                        System.out.println("No");
                    }
                }
            }
        }


        /**
         *  i能不能到达j
         * @param know
         * @param i 出发节点
         * @param j 结束节点
         * @param k 记录次数，防止
         * @return
         */
        public static boolean judgeIsKnow(boolean[][] know, int i, int j, int k) {
            if (k == know.length) {
                return false;
            }
            if (know[i][j]) {
                return true;
            }
            for (int l = 0; l < know.length; l++) {
                if (know[i][l]){
                    if (judgeIsKnow(know,l, j, l + 1)||judgeIsKnow(know,j, l, l + 1)) {
                        return true;
                    }
                }
            }


            return false;
        }



    }
}
