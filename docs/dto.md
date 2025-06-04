## DTO 定義書

各 API エンドポイントのリクエストボディの構造とバリデーションを記述します。レスポンスは Swagger UI を参照してください。

### BgmRequest

`POST /bgm`

| フィールド名 | 型         | 必須 | バリデーション            | 説明           |
| ------------ | ---------- | ---- | ------------------------- | -------------- |
| title        | String     | ○    | @NotBlank, @Size(max=100) | BGM のタイトル |
| url          | String     | ○    | @NotBlank, @URL           | BGM の URL     |
| tags         | List\<Tag> |      | @Size(max=5)              | タグ名リスト   |
| ┗ title      | String     | ○    | @NotBlank, @Size(max=10)  | タグ名         |

### TagRequest

`POST /bgm/{id}/tag`

| フィールド名 | 型     | 必須 | バリデーション           | 説明   |
| ------------ | ------ | ---- | ------------------------ | ------ |
| title        | String | ○    | @NotBlank, @Size(max=10) | タグ名 |

### ReportRequest

`POST /report`

| フィールド名 | 型     | 必須 | バリデーション            | 説明               |
| ------------ | ------ | ---- | ------------------------- | ------------------ |
| bgmId        | int    | ○    | @Positive                 | 通報する BGM の ID |
| reason       | String | ○    | @NotBlank, @Size(max=300) | 通報理由           |
