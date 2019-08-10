package com.integration.user;

import com.integration.user.controller.UserController;
import com.integration.user.gateway.UserGateway;
import com.integration.user.gateway.UserGatewayRestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ImportResource(locations =  {
		"classpath*:/META-INF/spring/integration/spring-integration-context-users.xml",
})
public class UserApplicationTests {

//	@Mock
//	UserGateway userGateway;
//
//	@Mock
//	UserGatewayRestTemplate userGatewayRestTemplate;

//	@Mock
//	UserController userController;

	@Test
	public void contextLoads() {
	}

}
