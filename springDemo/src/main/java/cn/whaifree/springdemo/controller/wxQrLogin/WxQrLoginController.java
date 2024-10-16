package cn.whaifree.springdemo.controller.wxQrLogin;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.jwt.JWT;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 17:07
 * @注释
 */
@Slf4j
@RestController
@RequestMapping("/wxQrLogin")
public class WxQrLoginController {

    /**
     * 1. 客户端请求网站，/subscribe，响应SSEEmitter
     *      获取客户端deviceId
     *      生成验证码numCode
     *          存放Cache < deviceId,numCode >
     *          存放Cache < numCode, SSEEmitter>
     *      SSE发送验证码给客户端
     *
     * 2. 客户端扫描公众号，发送numCode到公众号
     *      wx会请求/callback接口，带有numCode
     *      使用numCode找到deviceId，再找到SSEEmitter，发送生成的最新Token
     *
     */


    /**
     * sse的超时时间，默认15min
     */
    private final static Long SSE_EXPIRE_TIME = 15 * 60 * 1000L;
    private final RedisTemplate redisTemplate;


    /**
     * key = 验证码, value = 长连接
     */
    private LoadingCache<String, SseEmitter> verifyCodeCache;
    /**
     * key = 设备 value = 验证码
     */
    private LoadingCache<String, String> deviceCodeCache;

    public WxQrLoginController(@Qualifier("redisTemplate") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @PostConstruct
    public void init() {
        // 验证码，SSE
        verifyCodeCache = CacheBuilder.newBuilder().build(new CacheLoader<String, SseEmitter>() {
            @Override
            public SseEmitter load(String key) throws Exception {
                // 如果缓存未命中，则抛出异常，提示缓存未命中
                throw new RuntimeException("no val: " + key);
            }
        });
        // 设备，验证码
        deviceCodeCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                // 生成id
                int cnt = 0;
                while (true) {
                    String code = "deviceId#" + cnt++; // 可以是其他生成算法
                    // 如果verifyCodeCache中已经有这个缓存，证明这个Code已经被使用了
                    if (!verifyCodeCache.asMap().containsKey(code)) {
                        return code;
                    }
                }
            }
        });
    }


    /**
     * deviceId code
     * code sse
     * @return
     * @throws IOException
     */
    @GetMapping(path = "subscribe", produces = {org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter subscribe() throws IOException {
        String deviceId = String.valueOf(RandomUtil.randomInt(100)); // 随机生成一个UUID

        String realCode = deviceCodeCache.getUnchecked(deviceId) ;
        // 生成验证码


        // fixme 设置15min的超时时间, 超时时间一旦设置不能修改；因此导致刷新验证码并不会增加连接的有效期
        SseEmitter sseEmitter = new SseEmitter(SSE_EXPIRE_TIME);
        SseEmitter oldSse = verifyCodeCache.getIfPresent(realCode); // 是否已经存在旧的半长连接

        if (oldSse != null) {
            oldSse.complete(); // 旧的长连接
        }


        verifyCodeCache.put(realCode, sseEmitter);
        sseEmitter.onTimeout(() -> {
            log.info("sse 超时中断 --> {}", realCode);
            verifyCodeCache.invalidate(realCode);
            sseEmitter.complete();
        });
        sseEmitter.onError((e) -> {
            log.warn("sse error! --> {}", realCode, e);
            verifyCodeCache.invalidate(realCode);
            sseEmitter.complete();
        });
        // 若实际的验证码与前端显示的不同，则通知前端更新
        sseEmitter.send("initCode!");
        sseEmitter.send("init#" + realCode);
        return sseEmitter;
    }


    /**
     * fixme: 需要做防刷校验
     *
     *
     * 微信的响应返回
     * 本地测试访问:
     * curl -X POST 'http://localhost:8080/wx/callback'
     * -H 'content-type:application/xml' -d
     * '<xml>
     *     <URL><![CDATA[https://hhui.top]]></URL>
     *     <ToUserName><![CDATA[一灰灰blog]]></ToUserName>
     *     <FromUserName><![CDATA[demoUser1234]]></FromUserName>
     *     <CreateTime>1655700579</CreateTime>
     *     <MsgType><![CDATA[text]]></MsgType>
     *     <Content><![CDATA[login]]></Content>
     *     <MsgId>11111111</MsgId>
     * </xml>' -i
     *
     * @param msg
     * @return 返回给微信，微信会给客户端
     */
    @PostMapping(path = "callback",
            consumes = {"application/xml", "text/xml"},
            produces = "application/xml;charset=utf-8")
    public BaseWxMsgResVo callBack(@RequestBody WxTxtMsgReqVo msg) throws IOException {
        BaseWxMsgResVo res = new BaseWxMsgResVo();
        res.setToUserName(msg.getFromUserName());
        res.setFromUserName(msg.getToUserName());
        res.setCreateTime(System.currentTimeMillis());

        String content = msg.getContent();
        if ("subscribe".equals(msg.getEvent()) || "scan".equalsIgnoreCase(msg.getEvent())) {
            String key = msg.getEventKey();
            if (StringUtils.isNotBlank(key) || key.startsWith("qrscene_")) {

                // 带参数的二维码，扫描、关注事件拿到之后，直接登录，省却输入验证码这一步
                // fixme 带参数二维码需要 微信认证，个人公众号无权限
                String code = key.substring("qrscene_".length());


                // TODO sessionService.autoRegisterWxUserInfo(msg.getFromUserName());
                //  自动注册一个用户，获得用户ID

                // 找到对应的SSE，实现登录
                SseEmitter sseEmitter = verifyCodeCache.getIfPresent(code);

                // 生成Token
                String session = genSession(100L);
                // 登录成功，写入session
                sseEmitter.send(session);

                // 设置cookie的路径
                sseEmitter.send("login#Session=" + session + ";path=/;"); // session告诉前端，跳转到/根目录


                return res;
            }
        }
        return res;
    }



    public String genSession(Long userId) {
        // 1.生成jwt格式的会话，内部持有有效期，用户信息
        String session = String.valueOf(userId);
        String token = JWT.create()
                .setIssuer("issuer")
                .setPayload("session", session)
                .setExpiresAt(new Date(System.currentTimeMillis() + 2592000000L)).sign();

        // 2.使用jwt生成的token时，后端可以不存储这个session信息, 完全依赖jwt的信息
        // 但是需要考虑到用户登出，需要主动失效这个token，而jwt本身无状态，所以再这里的redis做一个简单的token -> userId的缓存，用于双重判定
        redisTemplate.opsForValue().set("UserId:Session:" + session, token);
        return token;
    }


    @Data
    @JacksonXmlRootElement(localName = "xml")
    public class WxTxtMsgReqVo {
        @JacksonXmlProperty(localName = "ToUserName")
        private String toUserName;
        @JacksonXmlProperty(localName = "FromUserName")
        private String fromUserName;
        @JacksonXmlProperty(localName = "CreateTime")
        private Long createTime;
        @JacksonXmlProperty(localName = "MsgType")
        private String msgType;
        @JacksonXmlProperty(localName = "Event")
        private String event;
        @JacksonXmlProperty(localName = "EventKey")
        private String eventKey;
        @JacksonXmlProperty(localName = "Ticket")
        private String ticket;
        @JacksonXmlProperty(localName = "Content")
        private String content;
        @JacksonXmlProperty(localName = "MsgId")
        private String msgId;
        @JacksonXmlProperty(localName = "MsgDataId")
        private String msgDataId;
        @JacksonXmlProperty(localName = "Idx")
        private String idx;
    }


    @Data
    @JacksonXmlRootElement(localName = "xml")
    public class BaseWxMsgResVo {

        @JacksonXmlProperty(localName = "ToUserName")
        private String toUserName;
        @JacksonXmlProperty(localName = "FromUserName")
        private String fromUserName;
        @JacksonXmlProperty(localName = "CreateTime")
        private Long createTime;
        @JacksonXmlProperty(localName = "MsgType")
        private String msgType;
    }


//
//    /**
//     * 初始化设备id
//     *
//     * @return
//     */
//    private String getOrInitDeviceId(HttpServletRequest request, HttpServletResponse response) {
//        String deviceId = request.getParameter("deviceId");
//        if (StringUtils.isNotBlank(deviceId) && !"null".equalsIgnoreCase(deviceId)) {
//            return deviceId;
//        }
//
//        Cookie device = SessionUtil.findCookieByName(request, LoginOutService.USER_DEVICE_KEY);
//        if (device == null) {
//            deviceId = UUID.randomUUID().toString();
//            if (response != null) {
//                response.addCookie(SessionUtil.newCookie(LoginOutService.USER_DEVICE_KEY, deviceId));
//            }
//            return deviceId;
//        }
//        return device.getValue();
//    }



}
