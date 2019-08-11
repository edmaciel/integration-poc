package com.integration.user.controller;

import com.integration.user.gateway.UserGateway;
import com.integration.user.gateway.UserGatewayRestTemplate;
import model.Company;
import org.jvnet.hk2.internal.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ResponseEntity<List<Company>> getUserList(@PathVariable("id") String id) {
        LOGGER.info("chegou o id {}", id);
        List<Company> companyList = new ArrayList<>();
        companyList.add(getCompany("2345"));
        companyList.add(getCompany("4532"));
        companyList.add(getCompany("75678"));
        companyList.add(getCompany("22876"));
        try {
            if (id.equals("integration")) {
//                Message payload = MessageBuilder.withPayload(id).build();
//                userGateway.send(payload);
//                users = (List<User>) userGateway.receive();
            } else {
                companyList = companyList
                        .parallelStream()
                        .map(i -> {
                            try {
                                i.setUsers(userGatewayRestTemplate.getUsers(i.getId()));
                                return i;
                            } catch (Exception e) {
                                return i;
                            }
                        }).collect(Collectors.toList());
            }


            return ResponseEntity.ok().body(companyList);
        } catch (Exception e) {
            return ResponseEntity.ok().body(companyList);
        }
    }

    private Company getCompany(String s) {
        return new Company(s);
    }
}
