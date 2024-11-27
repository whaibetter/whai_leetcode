package cn.whaifree.springdemo.controller.TS.UDP;

import cn.whaifree.springdemo.controller.TS.common.ProcessStrategy;
import cn.whaifree.springdemo.controller.TS.common.ProcessTarget;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dispatcher.LoadBalancingStrategy;
import org.springframework.integration.dsl.*;
import org.springframework.integration.handler.MessageProcessor;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.transformer.AbstractMessageProcessingTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;


/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 20:32
 * @注释
 */
@Configuration
public class UDPConfig {

    static final String encoderNumber = "123";

      /*
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-integration</artifactId>
        </dependency>
            <dependency>
            <groupId>org.springframework.integration</groupId>
            <artifactId>spring-integration-ip</artifactId>
        </dependency>

*/

    /**
     * 接收
     *
     * @param udpClient
     * @return
     */
    @Bean
    public IntegrationFlow processUniCastUdpMessage(@Qualifier("UDPGet") MessageHandler udpClient) {
        UnicastReceivingChannelAdapter channelAdapter = new UnicastReceivingChannelAdapter(9030);
        channelAdapter.setReceiveBufferSize(4096);
        channelAdapter.setLengthCheck(false);
        return IntegrationFlow
                .from(channelAdapter)
                .handle(udpClient)
//                .transform(this, "encoderTransformer")
//                .channel("udpChannel")
                .get();
    }

    class Encryptor {

//        public static String encrypt(Object msg, String key) {
//            try {
//                SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
//                Cipher cipher = Cipher.getInstance("AES");
//                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//                byte[] encrypted = cipher.doFinal((byte[]) msg);
//                return Base64.getEncoder().encodeToString(encrypted);
//            } catch (Exception e) {
//                throw new RuntimeException("Encryption failed", e);
//            }
//        }
//
//        public static byte[] decrypt(byte[] encryptedMsg, String key) {
//            try {
//                SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
//                Cipher cipher = Cipher.getInstance("AES");
//                cipher.init(Cipher.DECRYPT_MODE, secretKey);
//                return cipher.doFinal(Base64.getDecoder().decode(encryptedMsg));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

        private static final String ALGORITHM = "AES";
        private static byte[] SECRET_KEY = null; // 替换为你自己的密钥，密钥长度必须符合算法要求

        public static byte[] encrypt(byte[] data) throws Exception {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        }

        public static byte[] decrypt(byte[] encryptedData) throws Exception {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptedData);
        }

    }

//
//    /**
//     * 转换器 在消息通道（Channel）之间传递消息时进行数据格式的转换
//     *
//     * @param payload
//     * @param headers
//     */
//    @Transformer(inputChannel = "channelAdapter", outputChannel = "udpChannel")
//    public byte[] encoderTransformer(@Payload byte[] payload, @Headers Map<String, Object> headers) {
//        return Encryptor.decrypt(payload, encoderNumber);
//    }

//    @Bean("udpChannel")
//    public MessageChannel udpChannel() {
//        return new DirectChannel();
//    }


//    /**
//     * 一对一
//     *
//     * @return
//     */
//    @Bean
//    @BridgeFrom("messageChannel2")
//    public MessageChannel directChannel2() {
//        return new DirectChannel();
//    }
//
//    /**
//     * 一对一
//     *
//     * @return
//     */
//    @Bean
//    @BridgeFrom("messageChannel2")
//    public MessageChannel directChannel() {
//        return MessageChannels.direct().getObject();
//    }
//
//    /**
//     * 发布订阅 一对多
//     */
//    @Bean
//    public MessageChannel messageChannel2() {
//        return MessageChannels.publishSubscribe().getObject();
//    }


    /**
     * 发送
     *
     * @return
     */
    @Bean
    public UnicastSendingMessageHandler sending() {
        return new UnicastSendingMessageHandler("localhost", 9030, false);
    }

}


@Component("UDPGet")
@Slf4j
class UdpGet implements MessageHandler {
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        byte[] frame = (byte[]) message.getPayload();
        StringBuilder result = new StringBuilder("16进制表示：");
        for (byte aByte : frame) {
            result.append(String.format("%02x ", aByte));
        }
        log.info(result.toString());
        ProcessStrategy processStrategy = ProcessTarget.getProcessStrategy(frame);
        processStrategy.process(frame);
    }
}
