package com.gibbsdevops.springdemo;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("com.gibbsdevops.springdemo.api.repo")
@EnableRabbit
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("hello", false);
    }

    @Bean
    public List<Declarable> fanoutBindings(Queue helloQueue) {
        Queue fanoutQueue1 = new Queue("fanout.queue1", false);
        FanoutExchange fanoutExchange = new FanoutExchange("fanout.exchange");

        return Arrays.asList(
                fanoutQueue1,
                fanoutExchange,
                BindingBuilder.bind(fanoutQueue1).to(fanoutExchange),
                BindingBuilder.bind(helloQueue).to(fanoutExchange));
    }

    @RabbitListener(queues = "hello")
    public void listen(String in) throws InterruptedException {
        System.out.println(in);
        Thread.sleep(150);
    }
}
