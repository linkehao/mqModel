package com.mq.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumerDemo {

    @RabbitListener(queues = "queueA")//监听hello这个队列
    @RabbitHandler
    public void processA(String hello) {
        System.out.println("Receiver1  ===================: " + hello);
    }

    @RabbitListener(queues = "queueB")//监听hello这个队列
    @RabbitHandler
    public void processB(String hello) {
        System.out.println("Receiver2  ===================: " + hello);
    }
}
