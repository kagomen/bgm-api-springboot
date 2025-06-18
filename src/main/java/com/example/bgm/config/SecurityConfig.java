package com.example.bgm.config;

import com.example.bgm.security.FirebaseAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        // CSRF保護を無効化
        .csrf(csrf -> csrf.disable())

        // トークン認証のためセッション管理が不要なので、セッションを作成しないように設定
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        // URLごとのアクセス制御
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/public/**", "/swagger-ui/**", "/v3/api-docs/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())

        // FirebaseAuthFilterをUsernamePasswordAuthenticationFilterの前に挿入
        .addFilterBefore(new FirebaseAuthFilter(), UsernamePasswordAuthenticationFilter.class)

        // 未認証のユーザーが保護されたエンドポイントにアクセスした場合、401を返す
        .exceptionHandling(
            ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint((HttpStatus.UNAUTHORIZED))));

    return http.build();
  }
}
