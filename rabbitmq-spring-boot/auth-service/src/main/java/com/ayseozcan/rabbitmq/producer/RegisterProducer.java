package com.ayseozcan.rabbitmq.producer;

import com.ayseozcan.rabbitmq.model.RegisterModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisterProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "auth-exchange";
    private final String authKey = "auth-to-user-register-key";

    public RegisterProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRegisterMessage(RegisterModel registerModel) {
        rabbitTemplate.convertAndSend(exchange, authKey, registerModel);
    }

}
