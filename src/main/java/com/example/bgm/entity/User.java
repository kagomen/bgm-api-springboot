package com.example.bgm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
// JPA監査機能を有効化し、@CreatedDate等の使用を可能にする
@EntityListeners(AuditingEntityListener.class)
// userはDBで予約語のため、バッククォートで囲まないとエラーが発生する
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(nullable = false, unique = true)
  private String uid;

  @NotBlank
  @Email
  @Column(nullable = false)
  private String email;

  @NotBlank
  @Size(max = 100)
  @Column(length = 100, nullable = false)
  private String name;

  // ビルダーでisBanned()が呼び出されなくても、自動的にfalseを設定
  @Builder.Default
  @Column(nullable = false)
  private Boolean isBanned = false;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
