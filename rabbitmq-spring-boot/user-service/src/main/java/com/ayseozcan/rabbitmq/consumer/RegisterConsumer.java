package com.ayseozcan.rabbitmq.consumer;

import com.ayseozcan.rabbitmq.model.RegisterModel;
import com.ayseozcan.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RegisterConsumer {
    private final UserService userService;

    public RegisterConsumer(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "auth-to-user-register-queue")
    public void registerConsumer(RegisterModel registerModel) {
        userService.save(registerModel);
    }
}
