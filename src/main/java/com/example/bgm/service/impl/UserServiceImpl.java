package com.example.bgm.service.impl;

import com.example.bgm.entity.User;
import com.example.bgm.repository.UserRepository;
import com.example.bgm.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User registerIfNotExists(String uid, String email) {
    var user = userRepository.findByUid(uid);

    if (user.isPresent()) {
      return user.get();
    }

    var newUser =
        User.builder().uid(uid).email(email).isBanned(false).createdAt(LocalDateTime.now()).build();

    return userRepository.save(newUser);
  }

  @Override
  public void banUser(Integer id) {
    // TODO: あとで実装
  }
}
