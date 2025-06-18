package com.example.bgm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
// nullのフィールドを返却しない
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {
  private boolean success;

  private T data;
  private String message;

  public static <T> ApiResponseDto<T> success(T data) {
    return ApiResponseDto.<T>builder().success(true).data(data).build();
  }

  public static <T> ApiResponseDto<T> error(String message) {
    return ApiResponseDto.<T>builder().success(false).message(message).build();
  }
}
