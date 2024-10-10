package cn.whaifree.interview.Szml;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/8 19:50
 * @注释
 */
public class P1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (true) {
            String next = in.next("");
            System.out.print(next);
            System.out.print(" -> ");
            System.out.println(method(next));
        }
    }

    public static String method(String s) {
        char[] charArray = s.toCharArray();
        reverse(charArray, 0, charArray.length - 1); // 整体翻转
        for (int i = 0; i < s.length(); i++) {
            // 遇到连续的三个lia，对这个lia再次翻转
            if (i < s.length() - 2 && charArray[i] == 'i' && charArray[i + 1] == 'l' && charArray[i + 2] == 'a') {
                reverse(charArray, i, i + 2);
                i += 2; // 跳过这三个字符
            }else {
                charArray[i] = Character.toUpperCase(charArray[i]); // 变为大写
            }
        }
        return new String(charArray);
    }

    public static void reverse(char[] chars,int left ,int right) {
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
    }


}
