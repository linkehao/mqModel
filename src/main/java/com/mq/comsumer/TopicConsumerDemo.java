package com.mq.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumerDemo {

    @RabbitListener(queues = "topic.A")
    @RabbitHandler
    public void processtopicA(String hello) {
        System.out.println("Receiver Exchanges topic.A  ===================: " + hello);
    }

    @RabbitListener(queues = "topic.B")
    @RabbitHandler
    public void processtopicB(String hello) {
        System.out.println("Receiver Exchanges topic.B  ===================: " + hello);
    }

}
