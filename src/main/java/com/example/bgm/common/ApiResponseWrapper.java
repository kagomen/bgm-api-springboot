package com.example.bgm.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponseWrapper<T> {
  private boolean isSuccess;
  private T data;
  private String message;

  public static <T> ApiResponseWrapper<T> success(T data) {
    return ApiResponseWrapper.<T>builder().isSuccess(true).data(data).build();
  }

  public static <T> ApiResponseWrapper<T> error(String message) {
    return ApiResponseWrapper.<T>builder().isSuccess(false).message(message).build();
  }
}
