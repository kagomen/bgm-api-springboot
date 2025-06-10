package com.example.bgm.service;

import com.example.bgm.entity.User;

public interface UserService {
  User registerIfNotExists(String uid, String email);

  void banUser(Integer id); // TODO: ユーザーを論理削除
}
