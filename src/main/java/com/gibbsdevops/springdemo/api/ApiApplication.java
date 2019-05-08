package com.gibbsdevops.springdemo.api;

import io.opentracing.Tracer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.gibbsdevops.springdemo.model")
@ComponentScan({"com.gibbsdevops.springdemo.common","com.gibbsdevops.springdemo.api.controllers"})
@EnableJpaRepositories("com.gibbsdevops.springdemo.api.repo")
@EnableRabbit
public class ApiApplication {

    @Autowired
    Tracer tracer;

    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:common.properties,classpath:api.properties");
         SpringApplication.run(com.gibbsdevops.springdemo.api.ApiApplication.class, args);

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApiApplication.class);
//        for (String beanName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}
