package com.example.bgm.repository;

import com.example.bgm.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUid(String uid);
}
