package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 11:32
 * @注释
 */
public class LeetCode13 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("MCMXCIV"));
    }

    class Solution {
        static Map<Character, Integer> map;
        static {
            map = new HashMap<>();
            map.put('I', 1);
            map.put('V', 5);
            map.put('X', 10);
            map.put('L', 50);
            map.put('C', 100);
            map.put('D', 500);
            map.put('M', 1000);
        }
        public int romanToInt(String s) {
            char[] charArray = s.toCharArray();

            int res = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = charArray[i];
                Integer v = map.get(c);
                if (i > 0 && v > map.get(charArray[i - 1])) {
                    v -= map.get(charArray[i - 1]);
                    i--;
                }
                res += v;
            }
            return res;
        }
    }

}
