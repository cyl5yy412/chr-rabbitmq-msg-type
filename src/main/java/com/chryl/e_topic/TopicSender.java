package com.chryl.e_topic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者通过send方法向交换机exchange.topic中发送消息，消息中包含不同的路由键；
 */
public class TopicSender {

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.topic";

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSender.class);


    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % keys.length;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        LOGGER.info(" [x] Sent '{}'", message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}
