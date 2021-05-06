package com.hz.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1",5672);
        //我这里直接在构造方法传入了
        //        connectionFactory.setHost();
        //        connectionFactory.setPort();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        //是否开启消息确认机制
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    //不同类型的交换机 具有不同的路由规则
    // 1.DirectExchange.    一对一 根据routeKey
    // 2.TopicExchange.     模糊匹配原则  星号代表过滤一单词，#代表过滤后面所有单词， 用.隔开
    // 3.fanoutExchange     只要与他绑定了的队列， 他就会吧消息发送给对应队列（与routingKey没关系）
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Queue queue() {
        //名字  是否持久化
        return new Queue("testQueue", true);
    }



    @Bean
    public Binding binding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(topicExchange()).with("*.key");
//        return BindingBuilder.bind(queue()).to(fanoutExchange());

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        //注意  这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的  如果是yml配置的连接的话是不需要的
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //开启mandatory模式（开启失败回调）
        template.setMandatory(true);
        //指定失败回调接口的实现类
        template.setReturnCallback(new MyReturnCallback());


        template.setConfirmCallback(new MyConfirmCallback());
        return template;
    }
}
