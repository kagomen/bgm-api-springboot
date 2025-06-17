package com.example.bgm.config;

import com.example.bgm.security.FirebaseAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/public/**", "/swagger-ui/**", "/v3/api-docs/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(new FirebaseAuthFilter(), UsernamePasswordAuthenticationFilter.class)
        // 未認証のユーザーが保護されたエンドポイントにアクセスした場合、401を返す
        .exceptionHandling(
            ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint((HttpStatus.UNAUTHORIZED))))
        .build();
  }
}
