package com.integration.user.gateway;

import model.User;

import java.util.List;

public interface UserGatewayRestTemplate {

    List<User> getUsers(String id);
}
