## ドキュメント

- [要件定義書](./docs/requirements.md)
- [ER 図](./docs/er.md)
- [API 設計書](./docs/openapi.md)

## コマンドメモ

- yaml 形式の OpenAPI 定義ファイルを md 形式に変換

```bash
widdershins --search false --language_tabs 'ruby:Ruby' 'python:Python' --summary ./docs/openapi.yaml -o ./docs/openapi.md
```
