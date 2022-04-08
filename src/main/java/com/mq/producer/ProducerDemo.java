package com.mq.producer;

import com.mq.config.RabbitMQTopicConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ProducerDemo {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public String send() {
        String context = "hello==========" + new Date();
//        工作模式
//        rabbitTemplate.convertAndSend("queueName", context);

//        订阅与发布模式
//        rabbitTemplate.convertAndSend("fanoutExchange", "", context);

//        路由模式
//        rabbitTemplate.convertAndSend(RabbitMQRoutingConfig.DIRECT_EXCHANGE , "direct.b.key", context);

//        主题模式
        rabbitTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "topic.b.key", context);

        return "success";
    }
}
