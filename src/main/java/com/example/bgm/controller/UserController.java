package com.example.bgm.controller;

import com.example.bgm.dto.ApiResponseDto;
import com.example.bgm.dto.UserRequestDto;
import com.example.bgm.dto.UserResponseDto;
import com.example.bgm.service.UserService;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
// finalのついたフィールドのコンストラクターを自動生成
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/register_user")
  public ResponseEntity<ApiResponseDto<UserResponseDto>> registerUser(
      Authentication authentication, @RequestBody UserRequestDto requestBody) {

    var decodedToken = (FirebaseToken) authentication.getPrincipal();

    var userName = requestBody.getName();

    var user = userService.findOrRegisterUser(decodedToken, userName);

    var data = UserResponseDto.builder().id(user.getId()).build();

    var response = ApiResponseDto.success(data);

    return ResponseEntity.ok(response);
  }
}
