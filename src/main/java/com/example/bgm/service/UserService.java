package com.example.bgm.service;

import com.example.bgm.entity.User;
import com.example.bgm.repository.UserSRepository;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
// finalのついたフィールドのコンストラクターを自動生成
@RequiredArgsConstructor
public class UserService {

  private final UserSRepository userRepository;

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
