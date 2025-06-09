package com.example.bgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.bgm.security.FirebaseAuthFilter;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
          .requestMatchers(
            "/public/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
          ).permitAll()
          .anyRequest().authenticated()
      )
      .addFilterBefore(new FirebaseAuthFilter(), UsernamePasswordAuthenticationFilter.class)
      .build();
  }
}
