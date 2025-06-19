package com.example.bgm.exception;

import com.example.bgm.dto.ApiResponseDto;
import com.example.bgm.dto.ErrorResponseDto;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * リクエストボディのバリデーションエラーを処理
   *
   * @return 400 Bad Request
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleValidationException(
      MethodArgumentNotValidException ex) {

    // すべてのフィールドのバリデーションエラーを取得
    // 例: errors = {
    //      "name": "ユーザー名は必須です",
    //      "email": "メールアドレス形式で入力してください"
    //    }
    var errors = new HashMap<String, String>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              String fieldName = error.getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    log.warn("バリデーションエラーが発生しました: {}", errors);

    var errorResponse = new ErrorResponseDto("入力内容にエラーがあります", errors);
    var responseBody = ApiResponseDto.<Void>error(errorResponse);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
  }

  /**
   * Firebaseの認証エラーを処理
   *
   * @return 401 Unauthorized
   */
  @ExceptionHandler(FirebaseAuthException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleFirebaseAuthException(
      FirebaseAuthException ex) {
    log.warn("Firebase認証でエラーが発生しました: {}", ex.getMessage());

    var details = Map.of("firebase_error_code", ex.getErrorCode().toString());
    var errorResponse = new ErrorResponseDto("認証に失敗しました", details);
    var responseBody = ApiResponseDto.<Void>error(errorResponse);

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
  }

  /**
   * 指定されたエンティティが見つからないエラーを処理
   *
   * @return 404 Not Found
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiResponseDto<Void>> handleEntityNotFoundException(
      EntityNotFoundException ex) {
    log.warn("リソースが見つかりませんでした: {}", ex.getMessage());

    var errorResponse = new ErrorResponseDto("要求されたリソースが見つかりませんでした", Collections.emptyMap());
    var responseBody = ApiResponseDto.<Void>error(errorResponse);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
  }

  /**
   * サーバーエラーを処理
   *
   * @return 500 Internal Server Error
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDto<Void>> handleAllException(Exception ex) {
    log.error("予期せぬサーバーエラーが発生しました", ex);

    var errorResponse = new ErrorResponseDto("サーバー内部で予期せぬエラーが発生しました", Collections.emptyMap());
    var responseBody = ApiResponseDto.<Void>error(errorResponse);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
  }
}
