package cn.whaifree.tmp.project;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/17 15:38
 * @注释
 */
public  class 请求域名兜底策略_DNS获取IP操作改成从Polaris{

    /*实施请求域名兜底策略，把请求通过 DNS 获取 IP 的操作迁移至 Polaris 北极星注册中心，同时将
    Consul 配置设定为兜底策略，以此保障请求在不同场景下都能顺利进行，提升系统的稳定性和可靠性。*/


    /**
     * 要求所有的注册中心都往北极星迁移，所以需要在之前的基础上做兼容
     */

    //csm风险项暴露，仅通过使用域名调用服务，无法自动容灾，受解析 TTL 影响，容灾切换时间超过10min甚至更长
    static class GetIp{

        static int retryTime = 10;
        public String getDomain() {
            int times = 1;
            while (times++ < retryTime) {
                String ip = Polaris.get("dns");
                if (!ip.isEmpty()) {
                    return ip;
                }
            }
            // 备用Consu接入
            return Consul.get("dns");
        }

    }

    static class Polaris{

        public static Map<String, String> config = Maps.newHashMap();
        static {
            config.put("dns", "Polaris IP");
        }

        public static String get(String key) {
            return config.get(key);
        }

    }

    static class Consul{

        public static Map<String, String> config = Maps.newHashMap();
        static {
            config.put("dns", "Consul IP");
        }

        public static String get(String key) {
            // 如果获取不到
            return config.get(key);
        }

    }


}
