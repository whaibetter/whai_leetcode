package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/14 17:17
 * @注释
 */
public class LeetCode469_1 {

    @Test
    public void test() {
        String s = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        System.out.println(new Solution().validIPAddress(s));
    }

    class Solution {
        public String validIPAddress(String queryIP) {
            if (queryIP.contains(":") && isIpv6(queryIP.split(":", -1))) {
                return "IPv6";
            } else if (queryIP.contains(".") && isIpv4(queryIP.split("\\.", -1))) {
                return "IPv4";
            }
            return "Neither";
        }

        public boolean isIpv6(String[] ips) {
            if (ips.length != 8) {
                return false;
            }
            try {
                for (String ip : ips) {
                    if (ip.isEmpty() || ip.length() > 4) {
                        return false;
                    }
                    int i = Integer.parseInt(ip, 16);
                }
            }catch (Exception e){
                return false;
            }
            return true;
        }

        public boolean isIpv4(String[] ip) {
            if (ip.length != 4) {
                return false;
            }
            for (int i = 0; i < ip.length; i++) {
                String s = ip[i];
                try {
                    if (s.length() > 1 && s.startsWith("0")) {
                        return false;
                    }
                    Integer num = Integer.valueOf(s);
                    if (num < 0 || num > 255) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }

            }
            return true;
        }
    }
}
