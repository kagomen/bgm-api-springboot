package com.example.bgm.repository;

import com.example.bgm.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { // <対象Entity名, 主キーの型>
  Optional<User> findByUid(String uid); // uidでユーザーを検索
}
