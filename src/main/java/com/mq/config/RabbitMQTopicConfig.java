package com.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String QUEUE_TOPIC_KEY = "topic.#";

    public static final String QUEUE_TOPIC_KEY_B = "topic.b.key";

    public static final String QUEUE_TOPIC_A = "topic.A";

    public static final String QUEUE_TOPIC_B = "topic.B";

    @Bean
    Queue queueTopicNameA() {
        return new Queue(QUEUE_TOPIC_A);
    }

    @Bean
    Queue queueTopicNameB() {
        return new Queue(QUEUE_TOPIC_B);
    }

    //创建一个topic交换机
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    //将direct.A队列绑定到directExchange交换机中，使用direct.a.key作为路由规则
    @Bean
    Binding bindingExchangeMessageTopicA(Queue queueTopicNameA, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueTopicNameA).to(topicExchange).with(QUEUE_TOPIC_KEY);
    }


    @Bean
    Binding bindingExchangeMessageTopicB(Queue queueTopicNameB, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueTopicNameB).to(topicExchange).with(QUEUE_TOPIC_KEY_B);
    }

}
