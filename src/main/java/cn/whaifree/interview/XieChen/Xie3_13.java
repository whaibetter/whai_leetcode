package cn.whaifree.interview.XieChen;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/28 13:27
 * @注释
 */
public class Xie3_13 {


    static class Problem_1 {

        // 游游拿到了一个字符串，她想重排这个字符串后，使得该字符串包含尽可能多的"you"连续子串。你能帮帮她吗?
        public static void main(String[] args) {
            // 可以随意重排序
            // 输入数据 1
            // yyoouuuu
            // 输出数据 1
            // uyouyouu

            Scanner scanner = new Scanner(System.in);
            String s = scanner.next();

            char[] chars = s.toCharArray();

            int[] map = new int[26];

            // 统计次数
            for (char aChar : chars) {
                map[aChar - 'a'] += 1;
            }

            int minLength = Math.min(map['y' - 'a'], Math.min(map['o' - 'a'], map['u' - 'a']));
            map['y' - 'a'] -= minLength;
            map['o' - 'a'] -= minLength;
            map['u' - 'a'] -= minLength;

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i]; j++) {
                    stringBuilder.append((char)('a' + i));
                }
            }
            for (int i = 0; i < minLength; i++) {
                stringBuilder.append("you");
            }

            System.out.println(stringBuilder.toString());

        }


    }

    static class Problem_2{

        public static void main1(String[] args) {
            // 游游拿到了一个数组，她每次操作可以任选一个元素加 1或者减 1。
            // 游游想知道，将所有元素都变成和ai相等需要操作最少多少次?你需要回答ie[1,n]的结果。

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            for (int num : nums) {
                computeTime(nums, num);
            }

        }

        public static void computeTime(int[] nums, int target) {
            int res = 0;
            for (int num : nums) {
                res += Math.abs(num - target);
            }
            System.out.println(res);
        }

        /**
         * 如果 要求同时+1 -1
         * @param args
         */
        public static void main(String[] args) {
            // 游游拿到了一个数组，她每次操作可以任选一个元素加 1或者减 1。
            // 游游想知道，将所有元素都变成和ai相等需要操作最少多少次?你需要回答ie[1,n]的结果。

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }
            // 先排序，
            Arrays.sort(nums);
            // 对于nums内的元素，遍历target
            // 左边 部分+1，右边部分-1，直到左边活着右边全部=target时，就能知道次数,检查还有多少个达不到target的
            for (int i = 0; i < nums.length; i++) {
                change(nums, i);
            }
        }

        /**
         * @param ints
         * @param index 中间的位置
         */
        public static void change(int[] ints, int  index) {
            // 例如 1 2 3 4 5 选取target 为3

            // 统计左右距离ints[index]的数量
            int leftSub = 0;
            for (int i = 0; i < index; i++) {
                leftSub += ints[index] - ints[i];
            }
            int rightSub = 0;
            for (int i = index + 1; i < ints.length; i++) {
                rightSub += ints[i] - ints[index];
            }
            System.out.println(rightSub + leftSub);

        }

    }

    static class Problem_3{

        public static void main1(String[] args) {
            String input = "[1(1),2(2),-3(31),3(2222222222222222),2(12)]";
            Pattern pattern = Pattern.compile("(-?\\d+)\\((-?\\d+)\\)");

            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String beforeBracket = matcher.group(1);
                String insideBracket = matcher.group(2);
                System.out.println("括号前的数字: " + beforeBracket + ", 括号内部的数字: " + insideBracket);
            }
        }


        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            String s = scanner.next();
            List<long[]> list = new ArrayList<>();
            // 正则表达式 获取s
            // ()左右括号
            // -?表示可选符号，0或1此-号
            // \d 为十进制数字
            // \d+ 表示\d可以出现1次或多次
            // \\ 为\的转义
            // \\( 和 \\) 要匹配字面意义上的左括号和右括号
            Pattern pattern = Pattern.compile("(-?\\d+)\\((-?\\d+)\\)");
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                String beforeBracket = matcher.group(1); // 返回给定组在上一个匹配操作期间捕获的输入子序列。
                String insideBracket = matcher.group(2);
                list.add(new long[]{Long.parseLong(beforeBracket),Long.parseLong(insideBracket)});
            }
            compact(list);
        }

        public static void compact(List<long[]> list) {
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i)[0] == list.get(i - 1)[0]) {
                    list.set(i, new long[]{i, list.get(i)[1] + list.get(i - 1)[1]});
                    list.remove(i - 1);
                }
            }

            List<String> res = new ArrayList<>();
            for (long[] ints : list) {
                StringBuilder s1 = new StringBuilder();
                s1.append(ints[0]).append("(").append(ints[1]).append(")");
                res.add(s1.toString());
            }
            System.out.println(res.toString());
        }

    }



    class Problem_4{

    }

}

