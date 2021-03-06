package com.mq.comsumer1;

import com.mq.util.MsgConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectConsumer {
    public void msgConsumer(String queueName, String routingKey) {
        try {
            MsgConsumer.consumerMsg( queueName, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DirectConsumer consumer = new DirectConsumer();
        String[] routingKey = new String[]{"aaa", "bbb", "ccc"};
        String[] queueNames = new String[]{"qa", "qb", "qc1"};

        for (int i = 0; i < 3; i++) {
            consumer.msgConsumer(queueNames[i], routingKey[i]);
        }
//        Thread.sleep(1000 * 6);
    }
}
