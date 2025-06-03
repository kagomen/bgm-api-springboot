# テーブル設計（下書き）

## Must

### User テーブル

- id (String)
  - @PrimaryKey
- name (String)
  - @Nullable
- created_at (LocalDateTime)

### Bgm テーブル

- id (Integer)
  - @PrimaryKey
- title (String)
- url (String)
- created_by (String)
  - @ForeignKey(User.id)
- created_at (LocalDateTime)
- is_deleted (Boolean)

### Bookmark テーブル

- user_id (String)
  - @PrimaryKey
  - @ForeignKey(User.id)
- bgm_id (Integer)
  - @PrimaryKey
  - @ForeignKey(Bgm.id)
- created_at (LocalDateTime)

## Should

### Tag テーブル

- id (Integer)
  - @PrimaryKey
- title (String)
- created_by (String)
  - @ForeignKey(User.id)
- created_at (LocalDateTime)

### BgmTag テーブル

- bgm_id (Integer)
  - @PrimaryKey
  - @ForeignKey(Bgm.id)
- tag_id (Integer)
  - @PrimaryKey
  - @ForeignKey(Tag.id)
- created_at (LocalDateTime)

## Could

### Report テーブル

- id (Integer)
  - @PrimaryKey
- bgm_id (Integer)
  - @ForeignKey(Bgm.id)
- reported_by (String)
  - @ForeignKey(User.id)
- reason (String)
  - @Nullable
- created_at (LocalDateTime)
