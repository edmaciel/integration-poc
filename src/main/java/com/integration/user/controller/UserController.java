package com.integration.user.controller;

import com.integration.user.gateway.UserGateway;
import com.integration.user.gateway.UserGatewayRestTemplate;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserGateway userGateway;
    private UserGatewayRestTemplate userGatewayRestTemplate;

    @Autowired
    public UserController(@Qualifier("userGateway") UserGateway userGateway,
                          @Qualifier("UserGatewayRestTemplateImpl") UserGatewayRestTemplate userGatewayRestTemplate) {
        this.userGateway = userGateway;
        this.userGatewayRestTemplate = userGatewayRestTemplate;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> getUserList(@PathVariable("id") String id) {
        LOGGER.info("chegou o id {}", id);
        List<User> users = new ArrayList<>();
        try {
            if (id.equals("integration")) {
                Message payload = MessageBuilder.withPayload(id).build();
                userGateway.send(payload);
                users = (List<User>) userGateway.receive();
            } else {
                users = userGatewayRestTemplate.getUsers(id);
            }
            return ResponseEntity.ok().body(users);

        } catch (Exception e) {
            return ResponseEntity.ok().body(users);
        }
    }
}
