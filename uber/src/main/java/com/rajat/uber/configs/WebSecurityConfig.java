package com.rajat.uber.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.rajat.uber.handler.OAuthFailureHandler;
import com.rajat.uber.handler.OAuthSuccessHandler;
import com.rajat.uber.security.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)

public class WebSecurityConfig {


        private final JwtAuthFilter jwtAuthFilter;
        private static final String[] PUBLIC_ROUTES = {"/auth/**"};
        private final OAuthSuccessHandler oAuthSuccesshandler;
        private final OAuthFailureHandler oAuthFailurehandler;


        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .sessionManagement(sessionConfig -> sessionConfig
                                                .sessionCreationPolicy(
                                                                SessionCreationPolicy.STATELESS))
                                .csrf(csrfConfig -> csrfConfig.disable())
                                .cors(c -> c.configurationSource(corsConfigurationSource()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(PUBLIC_ROUTES).permitAll()
                                                .anyRequest().authenticated())
                                // .oauth2Login(oauth2config -> oauth2config
                                //                 .successHandler(oAuthSuccesshandler)
                                //                 .failureHandler(oAuthFailurehandler))
                                .addFilterBefore(jwtAuthFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return httpSecurity.build();
        }



        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Allow Next.js
                                                                                   // frontend
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                configuration.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

}
