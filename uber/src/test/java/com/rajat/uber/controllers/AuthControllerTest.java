package com.rajat.uber.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.http.HttpHeaders;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajat.uber.TestContainerConfiguration;
import com.rajat.uber.dto.LoginRequestDto;
import com.rajat.uber.dto.OnboardDriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.Role;
import com.rajat.uber.repositories.UserRepository;

@AutoConfigureWebTestClient(timeout = "100000")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
public class AuthControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;


    private static String token; // ✅ Store token for reuse
    private User user;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUpEach() {
        userRepository.deleteAll();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Create an Admin User for Authentication
        User adminUser = User.builder()
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin")) // Ensure password encoding
                .roles(Set.of(Role.ADMIN))  // ✅ This user is the logged-in Admin
                .build();
    
        userRepository.save(adminUser);
    
        // Create a Rider User (to be onboarded)
        user = User.builder()
                .email("rider@example.com")
                .password(passwordEncoder.encode("admin"))
                .roles(Set.of(Role.RIDER))  // ✅ This user is being onboarded
                .build();
    
        user = userRepository.save(user);

        token = loginAndGetToken(webTestClient, "admin@gmail.com", "admin"); 
    }

    @Test
    public void testSignUp_success() {
        SignUpDto signupDto = new SignUpDto();
        signupDto.setEmail("newuser@example.com");  // Use unique email to avoid conflict
        signupDto.setName("Test Name");
        signupDto.setPassword("password");
        signupDto.setRoles(Set.of(Role.RIDER));

        webTestClient.post()
                .uri("/auth/signup")
                .bodyValue(signupDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.data.email").isEqualTo(signupDto.getEmail())
                .jsonPath("$.data.name").isEqualTo(signupDto.getName());
    }

    @Test
    public void testOnboardDriver_success() {
        OnboardDriverDto onboardDriverDto = new OnboardDriverDto();
        onboardDriverDto.setVehicleId("ABC123");

        webTestClient
            .post()
            .uri("/auth/onBoardNewDriver/" + user.getId())
            .headers(headers -> headers.setBearerAuth(token))  
            .bodyValue(onboardDriverDto)
            .exchange()
            .expectStatus().isCreated();
    }

    @Test
    public void testLogin_Success() {
        LoginRequestDto loginDto = new LoginRequestDto();
        loginDto.setEmail("admin@gmail.com");
        loginDto.setPassword("admin");
        String responseBody = webTestClient.post()
                .uri("/auth/login")
                .bodyValue(loginDto)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().exists(HttpHeaders.SET_COOKIE) 
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody, "Login response body should not be null");

        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String accessToken = jsonNode.path("data").path("accessToken").asText();
            assertNotNull(accessToken, "Access token should not be null");
            System.out.println("Access Token: " + accessToken);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token from response", e);
        }
    }

    private static String loginAndGetToken(WebTestClient client, String email, String password) {
        LoginRequestDto loginDto = new LoginRequestDto(email, password);

        String responseBody = client.post()
                .uri("/auth/login")
                .bodyValue(loginDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody, "Login response should not be null");

        try {
            JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
            return jsonNode.path("data").path("accessToken").asText(); // ✅ Extract token
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token", e);
        }
    }
}
