package com.gibbsdevops.springdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestClientConfig {

    @Autowired
    Tracer tracer;

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new TracingRestTemplateInterceptor(tracer)));
        // MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }
}
