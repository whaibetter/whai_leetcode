package cn.whaifree.redo.redo_24_3_1;

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


class StringExer{
    String str = new String("good");
    char[] chars = {'a', 'b', 'c', 'd'};

    public void change(String str, char[] chars) {
        str = "bad";
        chars[0] = 'e';
    }

    public static void main(String[] args) {
        StringExer stringExer = new StringExer();
        stringExer.change(stringExer.str, stringExer.chars);
        System.out.println(stringExer.str);
        System.out.println(stringExer.chars);
    }

}
