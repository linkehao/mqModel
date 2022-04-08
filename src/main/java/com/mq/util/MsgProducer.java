package com.mq.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgProducer {
    public static void publishMsg(String exchange, BuiltinExchangeType exchangeType, String queueName, String routingKey, String message) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();
        // 声明exchange中的消息为可持久化，不自动删除
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);
        //消息队列
        channel.queueDeclare(queueName, true, false, false, null);
        //绑定队列到交换机
        channel.queueBind(queueName, exchange, routingKey);
        // 发布消息
        channel.basicPublish(exchange, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
