package com.rajat.uber.handler;

import java.io.IOException;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
                                        org.springframework.security.core.AuthenticationException exception) 
                                        throws IOException, ServletException {
        log.error("OAuth authentication failed: {}", exception.getMessage());

        // Redirect to frontend error page or show a meaningful error
        response.sendRedirect("http://localhost:3000/oauth/error?message=" + exception.getMessage());
    }
}
 
