package com.example.bgm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`user`") // userはDBで予約語のため、バッククォートで囲まないとエラーが発生する
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Lombokがビルダーパターンを自動生成→builder()でインスタンス生成できる
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String uid;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Boolean isBanned;

  @Column(nullable = false)
  private LocalDateTime createdAt;
}