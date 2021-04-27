package com.hz.test;

@Component
public class TestListener {
    @RabbitListener(queues = "testQueue")
    public void get(String message) throws Exception{
        System.out.println(message);
    }
}
