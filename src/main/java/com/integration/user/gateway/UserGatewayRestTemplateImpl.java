package com.integration.user.gateway;

import model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Service("UserGatewayRestTemplateImpl")
public class UserGatewayRestTemplateImpl implements UserGatewayRestTemplate {

    @Value("${user.endpoint}")
    private String endpoint;

    @Override
    public List<User> getUsers(String id) {
        RestTemplate restTemplate = new RestTemplate();

        List<User> users = new ArrayList<>();
        ResponseEntity<List<User>> response = restTemplate.exchange(endpoint + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });

        users = response.getBody();

        return users;
    }
}
