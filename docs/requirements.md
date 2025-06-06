# 要件定義書

## プロジェクト概要

おすすめの BGM 情報(URL など) を投稿し、その投稿を閲覧できる API を作成する。
この API では、誰でも BGM 情報を取得できるが、ログインユーザーはそれに加え、 BGM の投稿、ブックマーク、タグ付けをすることができる。タグを使った絞り込み検索も実装する。

## 機能要件

- BGM 関連
  - 全ユーザーの投稿 BGM 一覧取得 ☆
  - 指定ユーザーの投稿 BGM 一覧取得 ☆
  - 指定 BGM 情報の取得 ☆
  - BGM 一覧からタグ検索 ☆
  - BGM の投稿
  - BGM の編集
    - タグの追加
    - タグの削除
      > ※投稿ユーザー本人による いたずら目的の BGM 情報すり替えを防ぐため、URL やタイトルの編集は不可(BGM 削除 →BGM 新規作成で対応)。
  - BGM の削除(投稿ユーザと管理者のみ)
- ブックマーク関連
  - ブックマーク一覧の取得
  - ブックマーク一覧からタグ検索
  - ブックマークの登録
  - ブックマークの削除
- 違反報告関連
  - 違反投稿の報告 ★
  - 違反報告一覧の取得(管理者のみ)★
  - 違反対応内容を記録(管理者のみ)★
  - ユーザーの BAN(管理者のみ)★
    > 違反報告の流れについては [フローチャート](./docs/flowchart.md)を要確認。フロントエンドを実装する際は利用規約に、管理者の判断で予告なくアカウントを削除する可能性があることを明記すること。
- Firebase Authentication によるログイン認証
  > フロント側で初回ログイン時に GET /auth を叩くこと(2 回目以降は不要)。

> ☆: 非ログインユーザーでも使用可能な機能
> ★: 実装優先度の低い機能

## 非機能要件

- GitHub によるプロジェクト管理
- プルリクエストベースの開発フローを採用
- Spring Boot(Java)による REST API の設計・実装
- Swagger による API 仕様書の自動生成
- JUnit、Mockito を用いたユニットテストの作成
- Postman による API の動作確認
- Firebase Authentication による API 認証の実装
- DB は H2 を使用
- ビルドツールは Gradle を使用
- Docker 上に環境構築(できれば)

## スケジュール

- 着手開始: 5/29
- 設計書作成: 6/4 締切
  - [x] 要件定義書
  - [x] ER 図
  - [x] API 仕様書
  - [x] DTO, Entity 定義書
  - [x] シーケンス図
- 開発環境構築: 6/6 締切
  - SpringBoot のセットアップ
    - [ ] Gradle で SpringBoot プロジェクトを作成
    - [ ] 依存追加
  - Firebase のセットアップ
    - [ ] Firebase Console でプロジェクト作成
    - [ ] 認証方法を有効化
    - [ ] サービスアカウントの秘密鍵(JSON)を作成し、resources/firebase/ 配下に保存
  - Firebase Admin SDK の組み込み
    - [ ] Firebase 初期化処理を構成クラスで作成(FirebaseConfig)
    - [ ] ID トークンを検証するための Filter を作成(FirebaseAuthFilter)
    - [ ] 検証成功時に SecurityContext へ認証情報を設定
  - 認証付き API のテスト環境のセットアップ
    - [ ] Firebase Console でテストユーザーを作成
    - [ ] Postman で ID トークンを取得する
    - [ ] Postman で Authorization: Bearer {ID Token} を付けて API を叩く
    - [ ] Swagger UI で同様に JWT を入れて試せるように設定する
  - [ ] Docker 上に環境構築
- 実装: 6/11 締切
- テスト: 6/13 締切
