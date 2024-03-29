package com.integration.user.gateway;

import model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("UserGatewayRestTemplateImpl")
public class UserGatewayRestTemplateImpl implements UserGatewayRestTemplate {

    @Value("${user.endpoint}")
    private String endpoint;

    @Override
    public List<User> getUsers(String id) {
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<List<User>> response = restTemplate.exchange(endpoint + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });

        return response.getBody();
    }

    @Override
    public User getUser(String id) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(endpoint + id, User.class);
        return user;
    }
}
