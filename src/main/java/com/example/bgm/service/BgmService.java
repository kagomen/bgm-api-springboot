package com.example.bgm.service;

import com.example.bgm.dto.BgmRequestDto;
import com.example.bgm.entity.Bgm;
import com.example.bgm.entity.Tag;
import com.example.bgm.repository.BgmRepository;
import com.example.bgm.repository.TagRepository;
import com.example.bgm.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BgmService {

  private final BgmRepository bgmRepository;
  private final UserRepository userRepository;
  private final TagRepository tagRepository;

  @Transactional
  public Bgm createBgm(BgmRequestDto requestBody, String uid) {
    // UIDを使ってDBからUserエンティティを取得
    var user =
        userRepository
            .findByUid(uid)
            .orElseThrow(() -> new EntityNotFoundException("存在しないユーザーです UID: " + uid));

    // BGMデータを作成
    var bgm =
        Bgm.builder().title(requestBody.getTitle()).url(requestBody.getUrl()).user(user).build();

    // タグが設定されている場合、タグの処理をする
    if (!requestBody.getTagList().isEmpty()) {
      var tagList = new HashSet<Tag>();

      for (String title : requestBody.getTagList()) {

        // タグ名でDBを検索し、存在すればそのタグを、なければ新しいタグデータを作成
        var tag =
            tagRepository
                .findByTitle(title)
                .orElseGet(() -> Tag.builder().title(title).user(user).build());

        tagList.add(tag);
      }
      // 作成または取得したタグデータをBGMデータに関連付ける
      bgm.setTagList(tagList);
    }

    // 作成したBGMデータをDBに登録
    // Cascade設定により、Bgm、新規Tag、BgmTag中間テーブルへの書き込みがすべて行われる
    return bgmRepository.save(bgm);
  }
}
