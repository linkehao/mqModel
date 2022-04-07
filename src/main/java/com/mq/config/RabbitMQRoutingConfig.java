package com.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQRoutingConfig {
    public static final String DIRECT_EXCHANGE = "directExchange";
    public static final String QUEUE_DIRECT_A = "direct.A";
    public static final String QUEUE_DIRECT_B = "direct.B";

    @Bean
    public Queue queueDirectNameA() {
        return new Queue(QUEUE_DIRECT_A, true);
    }

    @Bean
    public Queue queueDirectNameB() {
        return new Queue(QUEUE_DIRECT_B, true);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    //将direct.A队列绑定到directExchange交换机中，使用direct.a.key作为路由规则
    @Bean
    Binding bindingExchangeMessageDirectA(Queue queueDirectNameA, DirectExchange directExchange) {
        return BindingBuilder.bind(queueDirectNameA).to(directExchange).with("direct.a.key");
    }


    @Bean
    Binding bindingExchangeMessageDirectB(Queue queueDirectNameB, DirectExchange directExchange) {
        return BindingBuilder.bind(queueDirectNameB).to(directExchange).with("direct.b.key");
    }

}
