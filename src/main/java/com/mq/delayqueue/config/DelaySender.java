package com.mq.delayqueue.config;

import com.mq.delayqueue.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class DelaySender {
    // AMQP 高级消息队列协议
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDelay(Order order) {
        log.info("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】" + order.toString() );
        MessagePostProcessor message = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("3000");  // 设置过期时间
                message.getMessageProperties().setContentEncoding("utf-8");
                return message;
            }
        };
//        使用匿名内部类也ok
        this.amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, order, message);
    }

}
