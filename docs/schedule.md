## 全 API 数

17 本

## 必ず実装する必要のある API

| API エンドポイント                | API の説明                | 予想実装時間 | 予想テストコード作成時間 |
| --------------------------------- | ------------------------- | ------------ | ------------------------ |
| POST `/register_user`             | ユーザー登録              | 5h           | 3h                       |
| GET `/get_bgm_list?tagList=…`     | タグ検索付き BGM 一覧取得 | 5h           | 3h                       |
| POST `/post_bgm`                  | BGM の投稿（タグの付与）  | 5h           | 3h                       |
| GET `/get_bookmark_list`          | ブックマーク一覧取得      | 2h           | 1h                       |
| POST `/add_bookmark/{bgmId}`      | ブックマーク追加          | 3h           | 2h                       |
| DELETE `/delete_bookmark/{bgmId}` | ブックマーク削除          | 2h           | 1h                       |

## テスト方針

- Service 層作成時
  - JUnit、Mockito を用いて単体テストを作成
- Controller 層作成時
  - mockMvc を用いて各 API エンドポイントごとに統合テストを作成
- 実装完了後
  - Postman で総合テスト（手動）を行う
  - スケジュール
    - 6/30：テスト仕様書を作成 → レビュー依頼・修正
    - 7/1：テスト実施

ガントチャートっぽく
