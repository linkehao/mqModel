package com.mq.product1;

import com.mq.util.MsgProducer;
import com.rabbitmq.client.BuiltinExchangeType;

public class DirectProducer {
    private static final String EXCHANGE_NAME = "direct.exchange";
    public void publishMsg(String queueName, String routingKey, String msg) {
        try {
            MsgProducer.publishMsg(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, queueName, routingKey, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        DirectProducer directProducer = new DirectProducer();
        String[] routingKey = new String[]{"aaa", "bbb", "ccc"};
        String[] queueNames = new String[]{"qa", "qb", "qc"};
        String msg = "hello >>> ";
        for (int i = 0; i < 1; i++) {
            directProducer.publishMsg(queueNames[i % 3],routingKey[i % 3], msg + i);
        }
        System.out.println("----over-------");
//        Thread.sleep(1000 * 60 * 100);
    }
}
