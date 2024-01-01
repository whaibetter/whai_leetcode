package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 *
 *
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class LeetCode242 {

    @Test
    public void test() {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.isAnagram("abcde", "ecbad"));

    }

    class Solution {
        public boolean isAnagram(String s, String t) {
            Map<Byte, Integer> S = getMap(s);
            Map<Byte, Integer> T = getMap(t);
            for (Byte aByte : S.keySet()) {
                if (!S.get(aByte).equals(T.get(aByte))) {
                    return false;
                }
            }
            for (Byte aByte : T.keySet()) {
                if (!T.get(aByte).equals(S.get(aByte))) {
                    return false;
                }
            }
            return true;
        }


        public Map<Byte, Integer> getMap(String s) {
            HashMap<Byte, Integer> mapS = new HashMap<>();
            for (byte aByte : s.getBytes()) {
                if (mapS.containsKey(aByte)) {
                    mapS.put(aByte, mapS.get(aByte) + 1);
                } else {
                    mapS.put(aByte, 1);
                }
            }
            return mapS;
        }
    }


    //  s 和 t 仅包含小写字母
    // 小写字母的Unicode范围从a（U+0061）到z（U+007A）。以下是这些字符的具体Unicode码：
    class Solution1 {

        public boolean isAnagram(String s, String t) {

            if (s.length() != t.length()) {
                return false;
            }

            int[] S = new int[26];
            for (byte aByte : s.getBytes()) {
                S[aByte-97]++;
            }
            for (byte aByte : t.getBytes()) {
                S[aByte-97]--;
            }

            for (int i = 0; i < S.length; i++) {
                if (S[i] != 0) {
                    return false;
                }
            }
            return true;
        }
    }

}
