package com.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFanoutConfig {
    @Bean
    public Queue queueA() {
        return new Queue("queueA", true);
    }

    @Bean
    public Queue queueB() {
        return new Queue("queueB", true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }
    //将queueA队列绑定到fanoutExchange交换机上面
    @Bean
    Binding bindingExchangeMessageFanoutA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    //将queueB队列绑定到fanoutExchange交换机上面
    @Bean
    Binding bindingExchangeMessageFanoutB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

}
