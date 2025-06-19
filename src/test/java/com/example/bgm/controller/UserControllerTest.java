package com.example.bgm.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bgm.dto.UserRequestDto;
import com.example.bgm.entity.User;
import com.example.bgm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; // ★ インポートを追加
import org.springframework.boot.test.context.SpringBootTest; // ★ WebMvcTestから変更
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private UserService userService;

  // UserControllerに渡されるauthenticationオブジェクトを生成するヘルパーメソッド
  private Authentication getMockAuthentication() {
    var mockToken = mock(FirebaseToken.class);
    when(mockToken.getUid()).thenReturn("test-uid");
    when(mockToken.getEmail()).thenReturn("hoge@hoge.com");
    return new UsernamePasswordAuthenticationToken(mockToken, null, null);
  }

  @Test
  @DisplayName("/register_user,正常系")
  void registerUser_success() throws Exception {

    // リクエストボディを作成
    var requestDto = UserRequestDto.builder().name("John").build();

    // id:1, name:John の偽のユーザーデータを作成
    var fakeUser = User.builder().id(1).uid("test-uid").name("John").build();

    // findOrRegisterUserが呼ばれたら、上記で作成した偽のユーザーデータを返す
    when(userService.findOrRegisterUser(any(FirebaseToken.class), any(String.class)))
        .thenReturn(fakeUser);

    mockMvc
        .perform(
            post("/register_user")
                .with(authentication(getMockAuthentication()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value(1))
        .andDo(print());
  }

  @Test
  @DisplayName("/register_user,異常系,名前が空のときバリデーションエラーを返す")
  void registerUser_whenNameIsBlank_shouldReturn400BadRequest() throws Exception {

    var invalidRequestDto = UserRequestDto.builder().name("").build();

    mockMvc
        .perform(
            post("/register_user")
                .with(authentication(getMockAuthentication()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequestDto)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.error.message").value("入力内容にエラーがあります"))
        .andExpect(jsonPath("$.error.details.name").value("ユーザー名は必須です"))
        .andDo(print());
  }

  @Test
  @DisplayName("/register_user,異常系,無効なIDトークンのとき認証エラーを返す")
  void registerUser_whenTokenIsInvalid_shouldReturn401Unauthorized() throws Exception {

    var requestDto = UserRequestDto.builder().name("John").build();

    mockMvc
        .perform(
            post("/register_user")
                .header("Authorization", "Bearer INVALID_TOKEN")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.error.message").value("認証に失敗しました"))
        .andDo(print());
  }

  @Test
  @DisplayName("/register_user,異常系,予期せぬエラーが発生したときサーバーエラーを返す")
  void registerUser_whenServiceThrowsException_shouldReturn500Error() throws Exception {

    var requestDto = UserRequestDto.builder().name("John").build();

    // Serviceが呼び出された際に、RuntimeExceptionを強制的に投げる
    when(userService.findOrRegisterUser(any(FirebaseToken.class), any(String.class)))
        .thenThrow(new RuntimeException());

    mockMvc
        .perform(
            post("/register_user")
                .with(authentication(getMockAuthentication()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.error.message").value("サーバー内部で予期せぬエラーが発生しました"))
        .andDo(print());
  }
}
