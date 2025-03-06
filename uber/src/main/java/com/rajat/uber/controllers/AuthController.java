package com.rajat.uber.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.uber.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.rajat.uber.dto.DriverDto;
import com.rajat.uber.dto.LoginRequestDto;
import com.rajat.uber.dto.LoginResponseDto;
import com.rajat.uber.dto.OnboardDriverDto;
import com.rajat.uber.dto.SignUpDto;
import com.rajat.uber.dto.UserDto;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {

        return new ResponseEntity<>(authService.signup(signUpDto), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId,
            @RequestBody OnboardDriverDto onboardDriverDto) {
        return new ResponseEntity<>(authService.onBoardNewDriver(userId,
                onboardDriverDto.getVehicleId()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest HttpServeletRequest, HttpServletResponse httpServletResponse) {
        String tokens[] = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(true);// refresh in cookie
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0])); // access in response
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));

        String accessToken = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }

}
