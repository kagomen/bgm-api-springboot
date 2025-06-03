## ドキュメント

- [要件定義書](./docs/requirements.md)
- [ER 図](./docs/er.md)
- [API 設計書](./docs/openapi.md)
  - [Swagger UI ver](https://editor.swagger.io/?url=https://gist.githubusercontent.com/kagomen/6520ef0283e1e447535c69cbc6f1fae0/raw/eeab7a31b43f873fe295735b2d5ca43b031964bd/bgm-api-openapi.yaml)

## コマンドメモ

- yaml 形式の OpenAPI 定義ファイルを md 形式に変換

```bash
widdershins --search false --language_tabs 'shell:Shell' --summary ./docs/openapi.yaml -o ./docs/openapi.md
```
