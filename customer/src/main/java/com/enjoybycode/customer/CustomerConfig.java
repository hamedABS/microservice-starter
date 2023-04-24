package com.enjoybycode.customer;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class CustomerConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

/*    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }*/

/*
    @Bean
    public MessageConverter jacksonConverter() {
        MessageConverter jackson2JsonMessageConverter =
                new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }
*/

}
