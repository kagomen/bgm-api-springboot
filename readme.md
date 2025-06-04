## ドキュメント

- [要件定義書](./docs/requirements.md)
- [API 設計書](./docs/openapi.md)
  - [Swagger Editor](https://editor.swagger.io/)に[openapi.yaml](./docs/openapi.yaml)のコードを入力すると、Swagger UI で閲覧可能
- [DTO 定義書](./docs/dto.md)
- [ER 図](./docs/er.md)
- [シーケンス図](./docs/sequence.md)

## コマンドメモ

- yaml 形式の OpenAPI 定義ファイルを md 形式に変換

```bash
widdershins --search false --language_tabs 'shell:Shell' --summary ./docs/openapi.yaml -o ./docs/openapi.md
```
