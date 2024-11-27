package cn.whaifree.springdemo.controller.TS.UDP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 20:24
 * @注释
 */
@Component
public class UDPSendUtils {

    private final UnicastSendingMessageHandler sender;

    @Autowired
    public UDPSendUtils(UnicastSendingMessageHandler sender) {
        this.sender = sender;
    }

    public void send(Object object) {
        Message<Object> msg = MessageBuilder.withPayload(object).build();
        sender.handleMessage(msg);
    }

}
