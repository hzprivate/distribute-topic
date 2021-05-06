package com.hz.test;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TestSend {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void testSend(){
        //至于为什么调用这个API 后面会解释
        //参数介绍： 交换机名字，路由建， 消息内容
        rabbitTemplate.convertAndSend("directExchange", "direct.key", "hello..... 1");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //发送消息的时候附带一个CorrelationData参数 这个对象可以设置一个id，可以是你的业务id 方便进行对应的操作
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("directExchange", "direct.key", "hello....2",correlationData);

    }
}
