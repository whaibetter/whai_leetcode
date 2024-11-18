package cn.whaifree.tmp.project;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/17 11:07
 * @注释
 */
public class Tencent {
    private static final Logger log = LoggerFactory.getLogger(Tencent.class);

   /* 以 Consul 配置中心为基础，开发出预分配、上限资源分配接口，成功让运营端控制分配的tsf-agent资
    源与租户端默认值实现同步。*/

    /*当实例或集群规模扩大时，Pod相关数据查询调用K8sAPI会显著延迟，平均时延高达15秒。采用多线程
    处理策略，提升了接口的并发处理能力，成功地将在40个Pod上查询时延从大约15秒降低至3秒内,显著
    提升用户体验。*/
    /*实施请求域名兜底策略，把请求通过 DNS 获取 IP 的操作迁移至 Polaris 北极星注册中心，同时将
    Consul 配置设定为兜底策略，以此保障请求在不同场景下都能顺利进行，提升系统的稳定性和可靠性。*/

    /*申请对 CVM 服务器的重置接口限频进行处理，一旦超过限频则响应报错，通过获取窗口大小分片的方
    式来解决 CVM 重置接口限频的问题，及时响应至前端。*/


    /*
    导入容器到集群时，下游处理时间过长无法及时响应导致断开，
    - 异步
    - 超时再异步 10s超时候先返回临时响应 ，数据不一致
    - 超时候
    - 超时再异步 10s超时候先返回临时响应 ，提供接口对结果进行查询

    申请对 CVM 服务器的重置接口限频进行处理，一旦超过限频则响应报错，通过获取窗口大小分片的方
    式来解决 CVM 重置接口限频的问题，及时响应至前端。*/



    class Consul开发预分配上下限资源接口{

    }



    class 请求域名兜底策略_DNS获取IP操作改成从Polaris{


    }




}




