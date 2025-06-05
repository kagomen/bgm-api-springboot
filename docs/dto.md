## DTO 定義書

各 API エンドポイントのリクエストボディの構造とバリデーションを記述します。レスポンスは Swagger UI を参照してください。

### BgmRequest

> POST /postBgm

| フィールド名 | 型            | 必須 | バリデーション            | 説明           |
| ------------ | ------------- | ---- | ------------------------- | -------------- |
| title        | String        | ○    | @NotBlank, @Size(max=100) | BGM のタイトル |
| url          | String        | ○    | @NotBlank, @URL           | BGM の URL     |
| tags         | List\<String> |      |                           | タグ名リスト   |

タグ名リストの中身：

| フィールド名 | 型     | 必須 | バリデーション           | 説明   |
| ------------ | ------ | ---- | ------------------------ | ------ |
| title        | String | ○    | @NotBlank, @Size(max=10) | タグ名 |

### TagAddRequest

> POST /addTag/{bgmId}

| フィールド名 | 型     | 必須 | バリデーション           | 説明   |
| ------------ | ------ | ---- | ------------------------ | ------ |
| title        | String | ○    | @NotBlank, @Size(max=10) | タグ名 |

### ReportRequest

> POST /postReport

| フィールド名 | 型      | 必須 | バリデーション            | 説明               |
| ------------ | ------- | ---- | ------------------------- | ------------------ |
| bgmId        | Integer | ○    | @NotNull, @Positive       | 通報する BGM の ID |
| reason       | String  | ○    | @NotBlank, @Size(max=300) | 通報理由           |

## 補足

| アノテーション  | チェック内容               | 対象型                         |
| --------------- | -------------------------- | ------------------------------ |
| @NotNull        | null 禁止                  | なんでも                       |
| @NotBlank       | null・空文字・空白文字禁止 | String のみ                    |
| @NotEmpty       | null と空配列・空文字禁止  | String, Collection, Map, Array |
| @PositiveOrZero | 0 以上                     | Integer, Long, Double          |
