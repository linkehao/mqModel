package com.mq.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queueName")
public class ConsumerDemo {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  ===================: " + hello);
    }
}
