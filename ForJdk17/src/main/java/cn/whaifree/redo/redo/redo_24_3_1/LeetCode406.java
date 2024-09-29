package cn.whaifree.redo.redo.redo_24_3_1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/3 12:30
 * @注释
 */
public class LeetCode406 {




    @Test
    public void test() {
        String a = "d";
        String b = "d";
        System.out.println(a == b);
        a = new String("d");
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println(a == b);

        new Solution().reconstructQueue(
                new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}}
        );
    }

    class Solution {
        public int[][] reconstructQueue(int[][] people) {

            LinkedList<int[]> e = new LinkedList<>();
            // 1. 安升高先排序，同身高k多的放后面
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }else {
                        return o2[0] - o1[0];
                    }
                }
            });


            for (int[] person : people) {
                e.add(person[1], person);
            }


            return e.toArray(new int[people.length][]);
        }
    }

}


//class StringExer{
//    StringBuilder str = new StringBuilder("good");
//    char[] chars = {'a', 'b', 'c', 'd'};
//
//    public void change(StringBuilder str, char[] chars) {
//        str = str.append("bad");
//        chars[0] = 'e';
//    }
//
//    public static void main(String[] args) {
//        StringExer stringExer = new StringExer();
//        stringExer.change(stringExer.str, stringExer.chars);
//        System.out.println(stringExer.str.toString());
//        System.out.println(stringExer.chars);
//
//        // 代码如下：
//        char[] f = {'a', 's', 'd'};
//
//        String a = "asd"; // 常量池
//        String b = new String("asd"); // 字符串形式new
//        String c = new String(f); // 数组形式new
//        String d = new String(f).intern(); // 数组形式new，使用intern方法
//        String b2 = new String("asd").intern(); // 字符串形式new
//
//        System.out.println(a + b + c + d); // 断点打在这里，只是为了防止jvm编译对代码优化
//
//        System.out.println(a == b); // false
//        System.out.println(a == b2); // true
//        System.out.println(a == d); // true
//        System.out.println(b == d); // false
//        System.out.println(c == d); // false
//
//
//        String a1 = new String("abc");
//        String a2 = "abc";
//        System.out.println(a1 == a2);
//
//    }
//
//}

//class ff {
//    @Test
//    public void main211() {
//
//        /**
//         * ① String s = new String("1")
//         * 创建了两个对象
//         * 		堆空间中一个new对象
//         * 		字符串常量池中一个字符串常量"1"（注意：此时字符串常量池中已有"1"）
//         * ② s.intern()由于字符串常量池中已存在"1"
//         * s指向的是堆空间中的对象地址
//         * s2 指向的是堆空间中常量池中"1"的地址
//         * 所以不相等
//         */
//        String s = new String("1");
//        s.intern(); // 这里 常量池已经存在1
//        String s2 = "1"; // 使用还是前面那个1
//        System.out.println(s==s2); // 一指向堆、一个指向常量池
//// jdk1.6 false jdk7/8 false
//
//        /*
//         * ① String s3 = new String("1") + new String("1")
//         * 等价于new String（"11"），但是，常量池中并不生成字符串"11"；
//         *
//         * ② s3.intern()
//         * 由于此时常量池中并无"11"，所以把s3中记录的对象的地址存入常量池
//         * 所以s3 和 s4 指向的都是一个地址
//         */
//        String s3 = new String("1") + new String("1");
//// 执行完后 常量池中不存在11
//        s3.intern(); // 让常量池中存在11；
//// 1. 在jdk6中，intern() 如果没有，会复制一份放入
//// 2. 在jdk7之后，intern() 如果没有，会把对象引用地址复制一份放入串池，不会创建对象
//        String s4 = "11"; // 使用上一行代码生成的11
//        System.out.println(s3==s4);
////jdk1.6 false jdk7/8 true
//
//    }
//
//
//}
