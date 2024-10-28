package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/5 11:03
 * @注释
 */
public class LeetCode763 {
    @Test
    public void test() {
        new Solution1().partitionLabels("ababcbacadefegdehijhklij").forEach(
                i -> {
                    System.out.println(i);
                }
        );

    }
    // 即最多能砍s多少刀？使得同一字母都出现在一个片段。
    public List<Integer> partitionLabels(String s) {
        // 统计 每个字符最远出现的位置，
        char[] chars = s.toCharArray();
        int[] map = new int[26];
        for (int i = 0; i < chars.length; i++) {
            map[chars[i] - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();

        // 再遍历idx（最远的下标）前面找到最远出现的位置==当前i 就是一个切断点
        int left = -1; // 标记左边界
        int far = 0; // 标记最远出现的
        for (int i = 0; i < chars.length; i++) {
            far = Math.max(far, map[chars[i] - 'a']); // far 表示最远边界，
            // 最远出现的 和 当前索引一样，证明找到分割点
            if (far == i) {
                res.add(i - left);
                left = i;
            }
        }

        return res;
    }


    class Solution1 {

        /**
         * 先统计每个字符最早最晚出现的位置
         * @param s
         * @return
         */
        public  List<Integer> partitionLabels(String s) {
            int[][] partitions = staticLabel(s.toCharArray());
            List<Integer> res = new ArrayList<>();
            Arrays.sort(partitions, (o1, o2) -> Integer.compare(o1[0], o2[0]));
            int right = partitions[0][1];
            int left = 0;
            for (int i = 0; i < partitions.length; i++) {
                if (partitions[i][0] > right) {
                    //左边界大于右边界即可纪委一次分割
                    res.add(right - left + 1);
                    left = partitions[i][0];
                }
                right = Math.max(right, partitions[i][1]);

            }
            //最右端
            res.add(right - left + 1);
            return res;
        }

        public int[][] staticLabel(char[] chars) {
            int[][] map = new int[26][2];
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (map[c - 'a'][0] == 0) {
                    map[c - 'a'][0] = i;
                }
                map[c - 'a'][1] = i;//最远出现

                //第一个元素区别对待一下
                map[chars[0] - 'a'][0] = 0;
            }

            // 去除字符串中未出现的字母所占用区间
            List<Integer> temp = new ArrayList<>();
            List<List<Integer>> h = new LinkedList<>();
            //组装区间
            for (int i = 0; i < 26; i++) {
                temp.clear();
                temp.add(map[i][0]);
                temp.add(map[i][1]);
                h.add(new ArrayList<>(temp));
            }
            int[][] res = new int[h.size()][2];
            for (int i = 0; i < h.size(); i++) {
                List<Integer> list = h.get(i);
                res[i][0] =  list.get(0);
                res[i][1] =  list.get(1);
            }

            return map;
        }
    }
}
