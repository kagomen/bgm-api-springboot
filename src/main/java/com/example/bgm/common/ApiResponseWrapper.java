package com.example.bgm.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // nullのフィールドを返却しない
public class ApiResponseWrapper<T> {
  private boolean success;

  private T data;
  private String message;

  public static <T> ApiResponseWrapper<T> success(T data) {
    return ApiResponseWrapper.<T>builder().success(true).data(data).build();
  }

  public static <T> ApiResponseWrapper<T> error(String message) {
    return ApiResponseWrapper.<T>builder().success(false).message(message).build();
  }
}
