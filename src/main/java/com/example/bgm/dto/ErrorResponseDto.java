package com.example.bgm.dto;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;

@Getter
public class ErrorResponseDto {
  private final String message;
  private final Map<String, String> details;
  private final LocalDateTime timestamp;

  /**
   * コンストラクター
   *
   * @param message エラーの全体メッセージ
   * @param details 個々のフィールドのエラー詳細
   */
  public ErrorResponseDto(String message, Map<String, String> details) {
    this.message = message;
    this.details = details;
    // 呼び出し側で現在時刻をセットするのを避け、
    // オブジェクト生成時に現在時刻を自動でセットする
    this.timestamp = LocalDateTime.now();
  }
}
