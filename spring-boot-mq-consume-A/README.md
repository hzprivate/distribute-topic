# spring-boot-rabbitmq
1. springboot 与 rabbitmq整合  消费端实战
2. 消费端 手动确认
3. 设置消息预取的数量（在批量处理时，大大减少时间性能。
   比如A，B两个消费端，如果不设置 预取数量，就会各自给A、B分配一定数量的消息，
   设置预取以后，只要谁先完成了，还可以继续向消息队列拿取）