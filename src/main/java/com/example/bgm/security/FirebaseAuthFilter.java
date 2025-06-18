package com.example.bgm.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Firebase Authentication用カスタムフィルター
 *
 * <p>クライアントから送られてくる Firebase IDトークンを検証する トークンが有効であれば、Spring Securityに認証情報を登録する
 * トークンが無効or無い場合は、認証せずにそのまま処理を渡す(認証なしAPI用)
 *
 * <p>OncePerRequestFilter: 1リクエストに1回だけ実行
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FirebaseAuthFilter extends OncePerRequestFilter {

  private final HandlerExceptionResolver handlerExceptionResolver;

  @Override
  public void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    // クライアントから送られてきたAuthorizationヘッダーを取得
    var header = request.getHeader("Authorization");

    // ヘッダーがない、もしくは"Bearer "で始まっていない場合は、
    // Firebaseによる認証はスキップして次の処理に進む
    // 例: BGM取得系API、ログイン前のリクエスト
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    // "Bearer "を除去してFirebase IDトークンを取り出す
    var idToken = header.substring(7);

    try {
      // IDトークンを検証
      // 成功すればuidなどを含むFirebaseトークンが返却される
      var decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

      // Spring Security用の認証情報オブジェクトを作成
      // 今回はFirebaseで認証するのでパスワードは必要ない
      var authentication =
          new UsernamePasswordAuthenticationToken(
              // UserControllerにDecoded ID Tokenをそのまま渡す
              decodedToken,
              // パスワード
              null,
              // 権限
              Collections.emptyList()
              // TODO: ロールを作成
              );

      // 認証情報をSecurityContextに登録することで、
      // この後のリクエスト処理で「認証済みユーザー」として扱えるようにする
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (FirebaseAuthException e) {
      // 例外処理をGlobalExceptionHandlerに任せる
      handlerExceptionResolver.resolveException(request, response, null, e);
      return;
    }
    // 認証済み状態でフィルター処理を続ける
    filterChain.doFilter(request, response);
  }
}
