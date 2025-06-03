## DTO 定義書

各 API エンドポイント で使用する リクエスト DTO の定義を記述。レスポンスは Swagger UI を参照してください。

### BgmRequest

`POST /bgm`

| フィールド名 | 型          | 必須 | バリデーション            | 制約   | 説明           |
| ------------ | ----------- | ---- | ------------------------- | ------ | -------------- |
| title        | String      | ○    | @NotBlank, @Size(max=100) |        | BGM のタイトル |
| url          | String      | ○    | @NotBlank, @URL           | UNIQUE | BGM の URL     |
| tags         | List\<Tag\> |      | @Size(max=5)              |        | タグ名リスト   |
| ┗ title      | String      | ○    | @NotBlank, @Size(max=10)  | UNIQUE | タグ名         |

### TagRequest

`POST /bgm/{id}/tag`

| フィールド名 | 型     | 必須 | バリデーション           | 制約   | 説明   |
| ------------ | ------ | ---- | ------------------------ | ------ | ------ |
| title        | String | ○    | @NotBlank, @Size(max=10) | UNIQUE | タグ名 |

### BookmarkRequest

`POST /bookmark`

| フィールド名 | 型  | 必須 | バリデーション | 制約 | 説明                       |
| ------------ | --- | ---- | -------------- | ---- | -------------------------- |
| bgmId        | int | ○    | @Positive      |      | ブックマークする BGM の ID |

### ReportRequest

`POST /report`

| フィールド名 | 型     | 必須 | バリデーション            | 制約 | 説明               |
| ------------ | ------ | ---- | ------------------------- | ---- | ------------------ |
| bgmId        | int    | ○    | @Positive                 |      | 通報する BGM の ID |
| reason       | String | ○    | @NotBlank, @Size(max=300) |      | 通報理由           |
