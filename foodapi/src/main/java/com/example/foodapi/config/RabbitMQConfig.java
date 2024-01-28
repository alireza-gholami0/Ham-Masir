package com.example.foodapi.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue_Name;
    @Value("${rabbitmq.exchange.name}")
    private String exchange_Name;
    @Value("${rabbitmq.routing.key}")
    private String routing_Key;
    @Bean
    public Queue queue(){
        return new Queue(queue_Name);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange_Name);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.
                bind(queue()).
                to(exchange()).
                with(routing_Key);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
