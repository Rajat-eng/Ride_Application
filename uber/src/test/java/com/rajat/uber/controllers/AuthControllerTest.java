package com.rajat.uber.controllers;

import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rajat.uber.TestContainerConfiguration;
import com.rajat.uber.dto.OnboardDriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.enums.Role;
import com.rajat.uber.repositories.RiderRepository;
import com.rajat.uber.repositories.UserRepository;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfiguration.class)
public class AuthControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiderRepository riderRepository;

    private User user;

    void setUpEach(){
        user = User.builder().id(1L).email("test@example.com").password("password").roles(Set.of(Role.RIDER)).build();
    }

    @Test
    void testSignUp_success() {
        SignUpDto signupDto = new SignUpDto();
        signupDto.setEmail("test@example.com");
        signupDto.setName("Test name");
        signupDto.setPassword("password");

        webTestClient.post()
                .uri("/auth/signup")
                .bodyValue(signupDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.data.email").isEqualTo(signupDto.getEmail())
                .jsonPath("$.data.name").isEqualTo(signupDto.getName());
    }

    
    void testOnboardDriver_success() {

        if (!userRepository.existsById(1L)) {
            userRepository.save(user);
        }

        OnboardDriverDto onboardDriverDto = new OnboardDriverDto();
        onboardDriverDto.setVehicleId("ABC123");

        webTestClient
                .post()
                .uri("/auth/onBoardNewDriver/1")
                .bodyValue(onboardDriverDto)
                .exchange()
                .expectStatus().isCreated();
    }
}
