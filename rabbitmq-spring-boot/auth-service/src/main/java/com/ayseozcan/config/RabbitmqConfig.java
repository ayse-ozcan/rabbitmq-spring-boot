package com.ayseozcan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private final String exchange = "auth-exchange";
    private final String authQueue = "auth-to-user-register-queue";
    private final String authKey = "auth-to-user-register-key";

    @Bean
    DirectExchange authExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue registerQueue() {
        return new Queue(authQueue);
    }

    @Bean
    public Binding registerBinding(final DirectExchange authExchange, final Queue registerQueue) {
        return BindingBuilder.bind(registerQueue).to(authExchange).with(authKey);
    }
}
