package com.gibbsdevops.springdemo.handlers;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EntityScan("com.gibbsdevops.springdemo.model")
@EnableRabbit
public class HandlerApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:common.properties,classpath:handler.properties");
        SpringApplication.run(HandlerApplication.class, args);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("hello", false);
    }

    @RabbitListener(queues = "hello")
    public void listen(String in) throws InterruptedException {
        System.out.println(in);
        Thread.sleep(150);
    }

}
