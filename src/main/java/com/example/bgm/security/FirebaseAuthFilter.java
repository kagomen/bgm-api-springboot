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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Firebase Authentication用カスタムフィルター
 *
 * <p>クライアントから送られてくる Firebase IDトークンを検証する トークンが有効であれば、Spring Securityに認証情報を登録する
 * トークンが無効or無い場合は、認証せずにそのまま処理を渡す(認証なしAPI用)
 *
 * <p>OncePerRequestFilter: 1リクエストに1回だけ実行
 */
public class FirebaseAuthFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(FirebaseAuthFilter.class);

  @Override
  public void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    // クライアントから送られてきたAuthorizationヘッダーを取得
    String header = request.getHeader("Authorization");

    // ヘッダーがない、もしくは"Bearer "で始まっていない場合は、
    // Firebaseによる認証はスキップして次の処理に進む
    // 例: BGM取得系API、ログイン前のリクエスト
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    // "Bearer "を除去してFirebase IDトークンを取り出す
    String idToken = header.substring(7);

    try {
      // IDトークンを検証
      // 成功すればuidなどを含むFirebaseトークンが返却される
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

      // Spring Security用の認証情報オブジェクトを作成
      // 今回はFirebaseで認証するのでパスワードは必要ない
      var authentication =
          new UsernamePasswordAuthenticationToken(
              decodedToken.getUid(), // uid
              null, // パスワード
              Collections.emptyList() // 権限
              // TODO: ロールを作成
              );

      // 認証情報をSecurityContextに登録することで、
      // この後のリクエスト処理で「認証済みユーザー」として扱えるようにする
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (FirebaseAuthException e) {
      logger.warn("Firebase認証に失敗しました", e);
      // トークン検証に失敗した場合（期限切れ、不正など）、認証エラーを返却
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    } catch (Exception e) {
      logger.error("Firebase認証中に予期せぬエラーが発生しました", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    // 認証済み状態でフィルター処理を続ける
    filterChain.doFilter(request, response);
  }
}
