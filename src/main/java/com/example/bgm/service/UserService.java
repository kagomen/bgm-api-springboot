package com.example.bgm.service;

import com.example.bgm.entity.User;
import com.example.bgm.repository.UserRepository;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// finalのついたフィールドのコンストラクターを自動生成
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public User findOrRegisterUser(FirebaseToken decodedToken, String userName) {
    var uid = decodedToken.getUid();

    return userRepository
        .findByUid(uid)
        .orElseGet(
            () -> {
              var newUser =
                  User.builder()
                      .uid(uid)
                      .email(decodedToken.getEmail())
                      .name(userName)
                      .isBanned(false)
                      .build();
              return userRepository.save(newUser);
            });
  }
}
