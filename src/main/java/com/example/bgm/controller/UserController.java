package com.example.bgm.controller;

import com.example.bgm.dto.response.UserRegisterResponse;
import com.example.bgm.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // finalなフィールドを引数に取るコンストラクタを生成
public class UserController {
  private final UserService userService;

  @Operation(summary = "ユーザーを登録", description = "uidからユーザーの存在チェックを行い、存在しなければユーザーを新規作成する")
  @ApiResponse(responseCode = "200", description = "内部ユーザーIDを返却")
  @PostMapping("/register_user")
  public ResponseEntity<UserRegisterResponse> registerUser(HttpServletRequest req) {
    try {
      var header = req.getHeader("Authorization");

      if (header == null || !header.startsWith("Bearer ")) {
        return ResponseEntity.status(401).build();
      }

      var idToken = header.substring(7);
      var decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

      var uid = decodedToken.getUid();
      var email = decodedToken.getEmail();

      var user = userService.registerIfNotExists(uid, email);

      var response = UserRegisterResponse.builder().id(user.getId()).build();

      return ResponseEntity.ok(response);
    } catch (FirebaseAuthException e) {
      return ResponseEntity.status(401).build();
    }
  }
}
