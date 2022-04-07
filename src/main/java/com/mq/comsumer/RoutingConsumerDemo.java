package com.mq.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoutingConsumerDemo {

    @RabbitListener(queues = "direct.A")
    @RabbitHandler
    public void processdirectA(String hello) {
        System.out.println("Receiver Exchanges direct.A  ===================: " + hello);
    }

    @RabbitListener(queues = "direct.B")
    @RabbitHandler
    public void processdirectB(String hello) {
        System.out.println("Receiver Exchanges direct.B  ===================: " + hello);
    }

}
