package com.example.bgm.security;

import com.example.bgm.dto.ApiResponseDto;
import com.example.bgm.dto.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  /**
   * 未認証のユーザーが保護されたリソースにアクセスしようとした際に呼び出される、Spring Securityの認証エントリーポイントのカスタム実装
   *
   * <p>このメソッドは、認証が必要であることを示すために、401 (Unauthorized)とJSONエラーレスポンスをクライアントに返却する
   */
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // GlobalExceptionHandlerと同じ形式のエラーレスポンスを作成
    var errorResponseDto = new ErrorResponseDto("認証が必要です", Collections.emptyMap());
    var apiResponse = ApiResponseDto.error(errorResponseDto);

    var jsonResponse = objectMapper.writeValueAsString(apiResponse);

    response.getWriter().write(jsonResponse);
  }
}
