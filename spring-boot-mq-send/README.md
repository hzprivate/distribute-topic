# spring-boot-rabbitmq
1. springboot 与 rabbitmq整合代码
2. AMQP协议中的基本概念 ：
    - Broker: 接收和分发消息的应用，我们在介绍消息中间件的时候所说的消息系统就是Message Broker。
    - Connection: publisher／consumer和broker之间的TCP连接。断开连接的操作只会在client端进行，
      Broker不会断开连接，除非出现网络故障或broker服务出现问题。
    - Channel: Channel作为轻量级的Connection极大减少了操作系统建立TCP connection的开销。
    - Queue: 消息最终被送到这里等待consumer取走。一个message可以被同时拷贝到多个queue中。
    - Binding: exchange和queue之间的虚拟连接，binding中可以包含routing key。
      Binding信息被保存到exchange中的查询表中，用于message的分发依据。
    - Virtual host: 出于多租户和安全因素设计的，把AMQP的基本组件划分到一个虚拟的分组中，类似于网络中的namespace概念。
       当多个不同的用户使用同一个RabbitMQ server提供的服务时，可以划分出多个vhost，每个用户在自己的vhost创建exchange／queue等。


3. 不同的交换机类型具有不同的路由规则
     - DirectExchange.    一对一 根据routeKey
     - TopicExchange.     模糊匹配原则  星号代表过滤一单词，#代表过滤后面所有单词， 用.隔开
     - FanoutExchange     只要与他绑定了的队列， 他就会吧消息发送给对应队列（与routingKey没关系）
4. 简单的总结一下就是 confirm机制是确认我们的消息是否投递到了 RabbitMq（Broker）上面 而mandatory是在我们的消息进入队列失败时候不会被遗弃（让我们自己进行处理）
