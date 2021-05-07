package com.hz;

import com.hz.test.TestSend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppStartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(AppStartApplication.class, args);
        TestSend bean = run.getBean(TestSend.class);
        bean.testSendBatch();
    }
}
