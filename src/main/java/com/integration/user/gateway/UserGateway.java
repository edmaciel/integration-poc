package com.integration.user.gateway;

import org.springframework.messaging.Message;

public interface UserGateway {

    void send(Message<?> message);

    Message<?> receive();
}
