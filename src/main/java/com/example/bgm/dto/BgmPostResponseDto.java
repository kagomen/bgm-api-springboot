package com.example.bgm.dto;

import com.example.bgm.entity.Bgm;
import com.example.bgm.entity.Tag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BgmPostResponseDto {

  private final Integer id;
  private final String title;
  private final String url;
  private final List<String> tagList;
  private final LocalDateTime createdAt;

  public static BgmPostResponseDto fromEntity(Bgm bgm) {

    var tagList = new ArrayList<String>();
    for (Tag tag : bgm.getTagList()) {
      tagList.add(tag.getTitle());
    }

    return BgmPostResponseDto.builder()
        .id(bgm.getId())
        .title(bgm.getTitle())
        .url(bgm.getUrl())
        .tagList(tagList)
        .createdAt(bgm.getCreatedAt())
        .build();
  }
}
