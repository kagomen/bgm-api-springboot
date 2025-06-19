package com.example.bgm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bgm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// repository.delete()が呼び出された際、物理削除ではなく論理削除（is_deletedをtrueにするUPDATE文）が実行されるよう設定
@SQLDelete(sql = "UPDATE bgm SET is_deleted = true WHERE id = ?")
// repository.findById()など、このエンティティに対する検索時に、
// 論理削除されていないレコード（is_deleted = false）のみを対象とするよう設定
@SQLRestriction("is_deleted = false")
public class Bgm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Size(max = 100)
  @Column(length = 100, nullable = false)
  private String title;

  @NotBlank
  @URL
  @Column(nullable = false)
  private String url;

  // FetchType.LAZYを設定することにより、パフォーマンスが向上
  // BgmEntityを取得した際すぐにUserEntityの情報をDBから取得せず、
  // bgm.getUser()が呼ばれたタイミングで取得する
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Builder.Default
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "bgm_tag",
      joinColumns = @JoinColumn(name = "bgm_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  // null例外回避のため、Setをnewする
  // DBから既存のtagListを読み込んだ際は、既存のデータで置き換えられる
  private Set<Tag> tagList = new HashSet<>();

  @Builder.Default
  @Column(nullable = false)
  private Boolean isDeleted = false;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
