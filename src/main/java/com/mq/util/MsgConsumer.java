package com.mq.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgConsumer {
    public static void consumerMsg(String queue, String routingKey)
            throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建消息信道
        final Channel channel = connection.createChannel();
        //消息队列
        channel.queueDeclare(queue, true, false, false, null);
        //绑定队列到交换机
//        channel.queueBind(queue, exchange, routingKey);
        System.out.println("[*] Waiting for message. To exist press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    System.out.println(" [x] Received '" + message);
                    System.out.println(" [x] Done");
//                    int i = 1/0;
//                    deliveryTag（唯一标识 ID）：当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag， 它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
//                    multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    System.out.println("错误了");
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        };

        // 取消自动ack
        channel.basicConsume(queue, false, consumer);
    }
}
