package com.example.foodapi.service.rabbitmq;

import com.example.foodapi.auth.AuthResponse;
import com.example.foodapi.dto.LogLoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchange_Name;
    @Value("${rabbitmq.routing.key}")
    private String routing_Key;


    public void loginLog(String email, LocalDateTime time) {
        LogLoginDTO message = LogLoginDTO.builder()
                .email(email)
                .dateTime(time)
                .build();;
        send(exchange_Name,routing_Key,message);
    }
    private void send(String exchange, String routing_Key, Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        byte[] payload;
        try {
            payload = objectMapper.writeValueAsBytes(object);
        }catch (JsonProcessingException e) {
            LOGGER.error("Error converting logData to JSON: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message message = new Message(payload, messageProperties);
        LOGGER.info("Message sent -> " + object.toString());
        rabbitTemplate.convertAndSend(exchange, routing_Key, message);
    }
}
