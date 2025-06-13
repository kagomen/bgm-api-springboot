package com.example.bgm.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

/**
 * 初期化処理を定義
 */
@Configuration
public class FirebaseConfig {

  // @PostConstruct アプリ起動後一度だけ実行される
  @PostConstruct
  public void initialize() throws IOException {
    if (FirebaseApp.getApps().isEmpty()) {
      var serviceAccount = new ClassPathResource("firebase/serviceAccountKey.json").getInputStream();

      var options = FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.fromStream(serviceAccount))
              .build();

      FirebaseApp.initializeApp(options);
    }
  }
}
