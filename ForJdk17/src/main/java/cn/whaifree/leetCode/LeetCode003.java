package cn.whaifree.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/15 19:01
 */
public class LeetCode003 {


    /**
     * 使用一个Map key为字符的值，value为字符的下标
     * 每次遇到没有的就往Map添加，并且右指针移动
     * 遇到有的，就左指针指向前一个的value
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        //获得s每个字符
        char[] chars = s.toCharArray();
        //lengthIndex作为当前长度的标志
        //maxLength用于给出最后的结果
        int maxLength = 0;
        //需要两个左右指针
        int leftIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])){
                //出现过了 左指针右移到它上次出现地方的下一个位置
                //但如abba，移动到最后一个a时，left应该指向第二个b，而不是a的位置+1
                leftIndex = Math.max(leftIndex,map.get(chars[i])+1);
            }

            map.put(chars[i], i);
            maxLength = Math.max(maxLength, i - leftIndex + 1);

        }

        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(new LeetCode003().lengthOfLongestSubstring1("abba"));
    }


    /**
     * 使用Map   abbcd
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {


        //key为char字符，value为下标。
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0,left = 0;
        for (int i = 0; i < s.length(); i++) {
            //如果出现过
            if (map.containsKey(s.charAt(i))){
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            //加入队列
            map.put(s.charAt(i),i);
            //更新maxLength
            maxLength = Math.max(maxLength, i - left + 1);
        }

        return maxLength;

    }
}
