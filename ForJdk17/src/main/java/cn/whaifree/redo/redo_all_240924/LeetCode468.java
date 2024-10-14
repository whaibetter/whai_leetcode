package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/13 11:39
 * @注释
 */
public class LeetCode468 {
    @Test
    public void test() {
        System.out.println(Integer.parseInt("b", 16));
        System.out.println(new Solution().validIPAddress("2001:0db8:85a3:0000:0:8A2E:0370:733a"));
    }

    class Solution {
        public String validIPAddress(String queryIP) {
            int i = queryIP.indexOf(".");
            if (i == -1) {
                return isV6(queryIP)? "IPv6" : "Neither";
            }else {
                return isV4(queryIP)? "IPv4" : "Neither";
            }
        }
        public boolean isV4(String queryIp){
            try {
                String[] split = queryIp.split("\\.", -1);
                if (split.length != 4) {
                    return false;
                }
                for (String s : split) {
                    if (s.length() > 1 && s.startsWith("0")) {
                        return false;
                    }
                    int i = Integer.parseInt(s);
                    if (i < 0 || i > 255) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public boolean isV6(String queryIp){
            try {
                String[] split = queryIp.split(":", -1);
                if (split.length != 8) {
                    return false;
                }
                for (String s : split) {
                    int i = Integer.parseInt(s, 16);
                    if (s.length() > 4) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

}
