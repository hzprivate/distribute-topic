package com.hz.config;

@Config
public class RabbitmqConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost",5672);
        //我这里直接在构造方法传入了
        //        connectionFactory.setHost();
        //        connectionFactory.setPort();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("testhost");
        //是否开启消息确认机制
        //connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public DirectExchange  defaultExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue queue() {
        //名字  是否持久化
        return new Queue("testQueue", true);
    }

    @Bean
    public Binding binding() {
        //绑定一个队列  to: 绑定到哪个交换机上面 with：绑定的路由建（routingKey）
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("direct.key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        //注意  这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的  如果是yml配置的连接的话是不需要的
        RabbitTemplate template = new RabbitTemplate(connectionFactory)；
        return template;
    }
}
