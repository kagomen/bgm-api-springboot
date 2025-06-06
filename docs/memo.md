## コマンド

- yaml 形式の OpenAPI 定義ファイルを md 形式に変換

```bash
widdershins --search false --language_tabs 'shell:Shell' --summary ./docs/openapi.yaml -o ./docs/openapi.md
```

### トークンの期限が切れた時の Firebase の内部フロー

```mermaid
sequenceDiagram
  participant FE as フロントエンド
  participant BE as バックエンド
  participant Firebase as Firebase Auth

  FE->>BE: APIリクエスト(Authorization: Bearer {ID Token})
  BE->>Firebase: ID Tokenを検証
  Firebase->>BE: Tokenが期限切れのためエラー
  BE->>FE: 401 Unauthorized

  FE->>Firebase: Refresh Tokenを使って新しいID Tokenを取得
  Firebase->>FE: 新しいID Tokenを返却

  FE->>BE: APIリクエスト(Authorization: Bearer {新しいID Token})
  BE->>Firebase: 新しいID Tokenで検証
  Firebase->>BE: 検証成功, uidを返却
  BE->>FE: レスポンス
```
