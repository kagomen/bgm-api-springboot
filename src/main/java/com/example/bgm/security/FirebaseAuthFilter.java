package com.example.bgm.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

// IDトークン検証を定義
public class FirebaseAuthFilter extends OncePerRequestFilter { // 1リクエストに1回だけ実行

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    // トークンがなければ認証せず、次へ処理を渡して終了
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String idToken = header.substring(7); // "Bearer " を除去

    try {
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

      var authentication =
          new UsernamePasswordAuthenticationToken(
              decodedToken.getUid(), // uid
              null, // パスワード(Firebaseで認証するのでパスワードは必要ない)
              Collections.emptyList() // 権限
              // TODO: ロールを作成
              );

      SecurityContextHolder.getContext().setAuthentication(authentication); // 認証登録
    } catch (FirebaseAuthException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    filterChain.doFilter(request, response); // 次に処理を渡す
  }
}
