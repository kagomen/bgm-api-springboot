package com.example.bgm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BgmRequestDto {
  @NotBlank(message = "タイトルは必須です")
  @Size(max = 100, message = "タイトルは100文字以内で入力してください")
  private String title;

  @NotBlank(message = "URLは必須です")
  @URL(message = "URLの形式で入力してください")
  private String url;

  // リクエストはListで受け取り、データとしてはSetで扱う
  // クライアントが重複したタグを送ってきても、まずはそのまま受け取るため
  @Builder.Default private List<String> tagList = new ArrayList<>();
}
