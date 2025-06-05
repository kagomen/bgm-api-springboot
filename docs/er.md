## ER 図

DB に保存するデータ構造(Entity)とその関係(Relation)を記述します。

```mermaid
erDiagram

User {
String id PK "Firebase uid"
String name "ユーザー名"
LocalDateTime created_at "登録日時"
}

Bgm {
Integer id PK "BGM ID"
String title "BGMタイトル"
String url "BGM URL"
Integer user_id FK "ユーザーID"
LocalDateTime created_at "投稿日時"
LocalDateTime deleted_at "削除日時"
}

Bookmark {
Integer user_id PK,FK "ユーザーID"
Integer bgm_id PK,FK "BGM ID"
LocalDateTime created_at "登録日時"
}

Tag {
Integer id PK "タグID"
String title UK "タグ名"
Integer user_id FK "ユーザーID"
LocalDateTime created_at "投稿日時"
}

BgmTag {
Integer bgm_id PK,FK "BGM ID"
Integer tag_id PK,FK "タグID"
LocalDateTime created_at "登録日時"
}

Report {
Integer id PK "レポートID"
Integer bgm_id FK "BGM ID"
String reason "通報理由"
Integer user_id FK "ユーザーID"
LocalDateTime created_at "通報日時"
}

User ||--o{ Bgm : user_id
User ||--o{ Bookmark : user_id
User ||--o{ Tag : user_id
User ||--o{ Report : user_id
Bgm ||--o{ Bookmark : bgm_id
Bgm ||--o{ BgmTag : bgm_id
Bgm ||--o{ Report : bgm_id
Tag ||--o{ BgmTag : tag_id
```

## 補足

- BGM の削除について
  - BGM データは物理削除せず論理削除する
    - Report テーブルで BGM の id が紐づいている&記録として残しておくため
  - `Bgm` テーブルの `url` と `deleted_at` に対して複合ユニーク制約を持たせる
    - 一度論理削除した URL を別ユーザーが再投稿できるようにするため(`url` だけユニーク制約を持たせると、再投稿できなくなる)
- タグの削除について
  - 検索しやすいように、タグは他の曲とも共有させる
  - よって、タグの削除は BGM との紐付けを解除するだけで、タグ自体は削除しない
