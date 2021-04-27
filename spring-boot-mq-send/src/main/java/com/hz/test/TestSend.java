package com.hz.test;

@Component
public class TestSend {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void testSend() {
        //至于为什么调用这个API 后面会解释
        //参数介绍： 交换机名字，路由建， 消息内容
        rabbitTemplate.convertAndSend("directExchange", "direct.key", "hello");
    }
}
