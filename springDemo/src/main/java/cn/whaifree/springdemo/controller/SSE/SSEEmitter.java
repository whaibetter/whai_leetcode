package cn.whaifree.springdemo.controller.SSE;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 21:44
 * @注释
 */
@RestController
public class SSEEmitter {

    public static void main(String[] args) {
        int a = 127;
        byte c = (byte) a;
        System.out.println(Integer.toBinaryString(c));
        System.out.println(c);
    }
    Map<String, SseEmitter> sseEmitterMap = new java.util.HashMap<>();

    @GetMapping(value = "/sseStart", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse(String key) {
        System.out.println(key);
        if (!sseEmitterMap.containsKey(key)) {
            SseEmitter sseEmitter = new SseEmitter();
            sseEmitterMap.put(key, sseEmitter);
        }

        return sseEmitterMap.get(key);
    }

    @PostMapping("sendSSE")
    public void send(String key, String message) {
        if (sseEmitterMap.containsKey(key)) {
            SseEmitter sseEmitter = sseEmitterMap.get(key);
            try {
                System.out.println(StrUtil.format("send message to {}:{}", key, message));
                sseEmitter.send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new IllegalArgumentException("No such key");
        }
    }
}
