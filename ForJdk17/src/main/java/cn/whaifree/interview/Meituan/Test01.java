package cn.whaifree.interview.Meituan;

import org.junit.Test;

import java.util.Scanner;

/**
 *
 * <a href="https://www.yuque.com/taxing-qarr6/hxitgt/acebypk1uc68zhi2">...</a>

 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/19 11:10
 * @注释
 */
public class Test01 {

    @Test
    public void test() {
        String s = "CSDFMTMTDSA";
        int k = 2;
        System.out.println(mt(k, s));
    }

    /**
     *  * MT 是美团的缩写，因此小美很喜欢这两个字母。
     *  * 现在小美拿到了一个仅由大写字母组成字符串，她可以最多操作k次，每次可以修改任意一个字符。小美想知道，操作结束后最多共有多少个"M'和'T'字符?
     *  * 输入描述
     *  * 第一行输入两个正整数n,k，代表字符串长度和操作次数。第二行输入一个长度为n的、仅由大写字母组成的字符串。1<=k<=n<=10^5
     *  * 输出描述
     *  * 输出操作结束后最多共有多少个'M'和'T'字符。
     *  * 示例 1
     *  * 输入
     *  * C++
     *  * 5 2
     *  * M鉀锯撂駙拳顰棛薪廈阗摟粒锟惠拱➋壢草麦耸蟭骚殝騶乳
     *  * 输出
     *  * Plain Text
     *  * A
     *  * A
     *  * 说明
     *  * 修改第三个和第五个字符，形成的字符串为 MTTAM，这样共有 4 个'M'和'T'。
     *
     * @param k
     * @param s
     * @return
     */
    public int mt(int k, String s) {

        int isMt = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == 'M' || aChar == 'T') {
                isMt++;
            }
        }

        int isNotMt = chars.length - isMt;
        if (isNotMt > k) {
            return k + isMt;
        }else{
            return chars.length;
        }
    }

    public static void main(String[] args) {
        new Test01().mt2();

//        new Test01().mt4();

    }


    /**
     * 小美拿到了一个由正整数组成的数组，但其中有一些元素是未知的(用 0 来表示)。
     * 现在小美想知道，如果那些未知的元素在区间[,]范围内随机取值的话，数组所有元素之和的最小值和最大值分别是多少?
     * 共有q次询问。
     * 输入描述
     * 第一行输入两个正整数n,q，代表数组大小和询问次数。
     * 第二行输入n个整数ai，其中如果输入ai的为 0，那么说明ai是未知的。
     * 接下来的q行，每行输入两个正整数1r，代表一次询问。
     * Plain Text
     * 1<=n,q<=10^5
     * 0<=ai<=10^9
     * 31<=l<=r<=10^9
     * 输出描述
     * 输出q行，每行输出两个正整数，代表所有元素之和的最小值和最大值。
     */
    public void mt2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int query = scanner.nextInt();

        int[] nums = new int[n];
        int sum = 0;
        int isZero = 0;
        for (int i = 0; i < nums.length; i++) {
            int inputNumber = scanner.nextInt();
            if (inputNumber==0) isZero++;
            nums[i] = inputNumber;
            sum += inputNumber;
        }

        for (int i = 0; i < query; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            System.out.println("=====");
            System.out.print(sum + left * isZero + " " + (sum + right * isZero));
        }

    }


    public void mt4(int[] nums, int k) {
        int sum = 1;
        for (int num : nums) {
            sum *= num;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sub = sum / nums[i];
            if (nums.length - String.valueOf(sub).lastIndexOf("0") == k) {
                ans++;
            }
        }
        System.out.println(ans);

    }



}
