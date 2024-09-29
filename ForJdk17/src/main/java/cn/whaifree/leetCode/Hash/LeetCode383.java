package cn.whaifree.leetCode.Hash;

import cn.hutool.core.map.MapUtil;
import cn.whaifree.leetCode.utils.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. 赎金信

 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 */
public class LeetCode383 {

    @Test
    public void test() {
        boolean b = new Solution1().canConstruct("aabb", "aabbc");
        System.out.println(b);
    }

    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {

            Map<Character, Integer> mapR  = new HashMap<>();
            for (char c : ransomNote.toCharArray()) {
                mapR.put(c, mapR.getOrDefault(c, 0) + 1);
            }

            Map<Character, Integer> mapM = new HashMap<>();
            for (char c : magazine.toCharArray()) {
                mapM.put(c, mapM.getOrDefault(c, 0) + 1);
            }


            MapUtils.printMap(mapR);
            MapUtils.printMap(mapM);

            for (Character c : mapR.keySet()) {
                if (mapM.getOrDefault(c, 0) < mapR.getOrDefault(c, 0)) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution1 {

        // ransomNote 和 magazine 由小写英文字母组成
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] map = new int[26];
            // 大的先存入Map

            // 相比转换为Char更快
            for (int i = 0; i < magazine.length(); i++) map[magazine.charAt(i) - 'a'] += 1;
            // 减去小的如果小于0，如果不够减就返回false
            for (int i = 0; i < ransomNote.length(); i++) {
                int index = ransomNote.charAt(i) - 97;
                map[index] -= 1;
                if (map[index] < 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
