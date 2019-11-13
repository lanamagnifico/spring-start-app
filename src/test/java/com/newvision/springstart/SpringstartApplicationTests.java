package com.newvision.springstart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newvision.springstart.entity.User;
import com.newvision.springstart.entity.Role;
import com.newvision.springstart.entity.RolesEnum;
import com.newvision.springstart.dao.UserRepository;
import com.newvision.springstart.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.nio.charset.Charset;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class SpringstartApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	URL path;
	@LocalServerPort
	int port;
	@Autowired
	UserService userService;
	@MockBean
	UserRepository userRepository;
	static final ObjectMapper om = new ObjectMapper();

	@BeforeEach
	private void initEach() throws Exception {
		restTemplate = new TestRestTemplate();
		path  = new URL("http://localhost:" + port + "/spring-start-app/api");
	}

	@Test
	public void whenUserWithAuthority_GetApi_ThenSuccess() throws Exception {
        User admin = new User();
		admin.setUserName("admin");
		admin.setUserPass("$2a$04$zx9aPTS3QijGucMfmj4rG.6SPvtBmmMiRzKGxSrIeu9fGeUhzldcS");
		admin.addRole(new Role(RolesEnum.ROLE_ADMIN.toString()));
        Mockito.when(userRepository.findByUserName("admin")).thenReturn(admin);

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("admin", "admin-password")
				.getForEntity(path.toString(), String.class);

		printJSON(response);

        assertThat(userService.findByUserName("admin")).isNotNull();
		assertEquals(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")),
				response.getHeaders().getContentType());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response
				.getBody()
				.contains("Hello, Spring Rest!"));
	}

	@Test
	public void whenUserWithoutAuthority_GetApi_ThenForbidden403() throws Exception {
		User user = new User();
		user.setUserName("user");
		user.setUserPass("$2a$04$c48Jc5iIqtg1pcM/yMd.SO0U6qKgEhRCkOe.NDdVj4hBb.au9LwyK");
		user.addRole(new Role(RolesEnum.ROLE_USER.toString()));
		Mockito.when(userRepository.findByUserName("user")).thenReturn(user);

		String expected = "{\"status\":403,\"error\":\"Forbidden\",\"message\":\"Forbidden\",\"path\":\"/spring-start-app/api\"}";

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("user", "user-password")
				.getForEntity(path.toString(), String.class);

		printJSON(response);

		assertThat(userService.findByUserName("user")).isNotNull();
		assertEquals(MediaType.APPLICATION_JSON_UTF8,
				response.getHeaders().getContentType());
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void whenNoLoggedUser_GetApi_ThenUnauthorized401() throws Exception {

		String expected = "{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Unauthorized\",\"path\":\"/spring-start-app/api\"}";

		ResponseEntity<String> response = restTemplate
				.getForEntity(path.toString(), String.class);

		printJSON(response);

		assertEquals(MediaType.APPLICATION_JSON_UTF8,
				response.getHeaders().getContentType());
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private static void printJSON(Object object) {
		String result;
		try {
			result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
