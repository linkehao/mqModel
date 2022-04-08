package com.mq.product1;

import com.mq.util.MsgProducer;
import com.rabbitmq.client.BuiltinExchangeType;

public class FanoutProducer {
    private static final String EXCHANGE_NAME = "fanout.exchange";
    public void publishMsg(String queueName, String routingKey, String msg) {
        try {
            MsgProducer.publishMsg(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, queueName, routingKey, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        FanoutProducer directProducer = new FanoutProducer();
        String msg = "hello >>> ";
        String[] queueNames = new String[]{"qa-2", "qb-2", "qc-2"};
        for (int i = 0; i < 3; i++) {
            directProducer.publishMsg(queueNames[i], "", msg + i);
        }
    }
}
