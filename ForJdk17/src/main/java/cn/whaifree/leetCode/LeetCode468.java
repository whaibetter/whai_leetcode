package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/19 22:08
 * @注释
 */
public class LeetCode468 {

    @Test
    public void test() {

        System.out.println(5 & 0xFF);

        Solution solution = new Solution();
        System.out.println(solution.validIPAddress("20EE:Fb8:85a3:0:0:8A2E:0370:7334:12"));
    }

    class Solution {
        public String validIPAddress(String queryIP) {
            boolean ipv6 = queryIP.contains(":");
            if (ipv6 && isIpv6(queryIP)) {
                return "IPv6";
            } else if (!ipv6 && isIpv4(queryIP)){
                return "IPv4";
            }
            return "Neither";
        }


        public boolean isIpv6(String s) {
            String[] split = s.split(":", -1);
            if (split.length != 8) {
                return false;
            }
            for (String item : split) {
                if (item.length() > 4 || item.isEmpty()) {
                    return false;
                }

                try {
                    int i = Integer.parseInt(item, 16);// 16进制

                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }

        public boolean isIpv4(String s) {
            String[] split = s.split("\\.");
            if (split.length != 4) {
                return false;
            }
            for (String item : split) {
                if (item.length() > 3 || item.isEmpty()) {
                    return false;
                }
                try {
                    int i = Integer.parseInt(item);
                    if (i == 0 && item.length() != 1) {
                        return false;
                    }
                    if (item.startsWith("0") && i != 0) { // 00.0.0.0.
                        return false;
                    }
                    if (i < 0 || i > 255) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }

            }
            return true;
        }
    }
}
