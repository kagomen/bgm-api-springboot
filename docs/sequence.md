## シーケンス図

### ユーザー情報の登録

> POST /register_user

```mermaid
sequenceDiagram
    participant FE as フロントエンド
    participant FB as Firebase Auth
    participant BE as バックエンド
    participant DB as データベース
    FE ->> FB: ログイン
    FB ->> FE: ID Tokenを返却
    FE ->> BE: POST /register_user<br />Authorization: Bearer {ID Token}
    BE ->> FB: ID Tokenを検証
    FB ->> BE: 検証OK, Decoded ID Tokenを返却
    BE ->> DB: Decoded ID Tokenからuidを取り出し、<br />Userの存在チェック(uid)

    alt Userが存在しない
        DB ->> BE: 存在しない
        BE ->> DB: Userの作成依頼
        DB ->> BE: 作成完了
    else Userが存在する
        DB ->> BE: 存在する
    end
    BE ->> FE: 200 OK {id}
```

#### 備考

Firebase Authenticationを使用した実装方法には以下の2つがあるが、今回はクライアント主導型を実装する。

> クライアント主導型：
>
> これは、特にフロントエンド（React, Vueなど）とバックエンドが分離した現代的なアプリケーションで標準的な手法です。
> 未登録のユーザーが、クライアント（Webやアプリ）の画面でメールアドレス・パスワード・名前などを入力する。
> クライアントは、Firebaseのクライアント用SDKを使って、まずFirebase Authenticationに直接ユーザーを登録する。
> Firebaseへの登録が成功すると、FirebaseはクライアントにID Tokenを返す。
> クライアントは、そのID TokenをAuthorizationヘッダーに付けて、Spring BootサーバーのAPI（例: POST
> /register_user）に送信する。
> APIを受け取ったバックエンドは、ID Tokenを検証し、「この人は正しくFirebaseに登録された（またはログインした）ユーザーだ」と確認する。
> バックエンドは、その確認が取れた情報を使って、自社のUserデータベースにレコードを作成（または検索）する。
> バックエンドは、クライアントに内部IDなどを返す。
>
> サーバー主導型：
>
> 未登録のユーザーが、クライアント（Webやアプリ）の画面でメールアドレス・パスワード・名前などを入力する。
> クライアントは、その情報をそのまま私たちのSpring BootサーバーのAPI（例: POST /users/register）に送信する。
> APIを受け取ったバックエンドは、Admin SDKを使って、受け取ったメールアドレスとパスワードでFirebaseに新しいユーザーを作成するよう命令する。
> Firebaseがユーザー作成に成功したら、そのユーザーのUIDをバックエンドに返す。
> バックエンドは、そのUIDと、クライアントから受け取った名前などを使って、自社のUserデータベースにレコードを作成する。
> バックエンドは、クライアントに「登録成功」のレスポンスを返す。
> このフローでは、APIが呼ばれた時点ではユーザーはまだFirebaseに存在しません。APIが、Firebaseへの登録と自社DBへの登録を両方とも行います。

[//]: # (TODO: APIごとにシーケンス図を作成)

### BGM を投稿

```mermaid
sequenceDiagram
    participant FE as フロントエンド
    participant BE as バックエンド
    participant FB as Firebase Auth
    participant DB as データベース
    FE ->> BE: POST /postBgm<br />Authorization: Bearer {ID Token}<br />{ title, url, tags }
    BE ->> FB: ID Tokenを検証
    FB ->> BE: uidを返却
    BE ->> DB: Bgmの存在チェック(url)

    alt Bgmが存在する
        DB ->> BE: 存在する
        BE ->> FE: 409 Conflict
    else Bgmが存在しない
        DB ->> BE: 存在しない
        BE ->> DB: Bgmの作成依頼
        DB ->> BE: 作成完了
        BE ->> FE: 201 Created
    end
```

### タグを追加

```mermaid
sequenceDiagram
    participant FE as フロントエンド
    participant BE as バックエンド
    participant FB as Firebase Auth
    participant DB as データベース
    FE ->> BE: POST /addTag/{bgmId}<br />Authorization: Bearer {ID Token}<br />{ "title": "happy" }
    BE ->> FB: ID Tokenを検証
    FB ->> BE: uid を返却
    BE ->> DB: Bgmの存在チェック(bgmId)
    alt Bgmが存在しない
        DB ->> BE: 存在しない
        BE ->> FE: 404 Not Found
    else Bgmが存在する
        DB ->> BE: 存在する
        BE ->> DB: Tagの存在チェック(title)
        alt Tagが存在しない
            DB ->> BE: 存在しない
            BE ->> DB: Tagの作成依頼
            DB ->> BE: 作成完了
        else Tagが存在する
            DB ->> BE: 存在する
        end

        BE ->> DB: BgmTagの存在チェック(bgmId, tagId)
        alt BgmTagが既に存在する
            DB ->> BE: 存在する
            BE ->> FE: 409 Conflict
        else BgmTagが存在しない
            DB ->> BE: 存在しない
            BE ->> DB: BgmTagの作成依頼
            DB ->> BE: 作成完了
            BE ->> FE: 201 Created
        end
    end
```

### BGM からタグを削除

```mermaid
sequenceDiagram
    participant FE as フロントエンド
    participant BE as バックエンド
    participant FB as Firebase Auth
    participant DB as データベース
    FE ->> BE: DELETE /removeTag/{bgmId}<br />Authorization: Bearer {ID Token}<br />{ tagId: 123 }
    BE ->> FB: ID Tokenを検証
    FB ->> BE: uid を返却
    BE ->> DB: Bgmの存在チェック(bgmId)
    alt Bgmが存在しない
        DB ->> BE: 存在しない
        BE ->> FE: 404 Not Found
    else Bgmが存在する
        DB ->> BE: 存在する
        BE ->> DB: Tagの存在チェック(tagId)
        alt Tagが存在しない
            DB ->> BE: 存在しない
            BE ->> FE: 404 Not Found
        else Tagが存在する
            DB ->> BE: 存在する
            BE ->> DB: BgmTagの存在チェック(bgmId, tagId)
            alt BgmTagが存在しない
                DB ->> BE: 存在しない
                BE ->> FE: 404 Not Found
            else BgmTagが存在する
                DB ->> BE: 存在する
                BE ->> DB: BgmTagの削除依頼
                DB ->> BE: 削除完了
                BE ->> FE: 204 No Content
            end
        end
    end
```

### 違反報告

```mermaid
sequenceDiagram
    participant FE as フロントエンド
    participant BE as バックエンド
    participant FB as Firebase Auth
    participant DB as データベース
    FE ->> BE: POST /postReport<br />Authorization: Bearer {ID Token}<br />{ "bgmId": 123, "reason": "不適切な内容" }
    BE ->> FB: ID Tokenを検証
    FB ->> BE: uid を返却
    BE ->> DB: Bgmの存在チェック(bgmId)
    alt Bgmが存在しない
        DB ->> BE: 存在しない
        BE ->> FE: 404 Not Found
    else Bgmが存在する
        DB ->> BE: 存在する
        BE ->> DB: Reportの作成依頼
        DB ->> BE: 作成完了
        BE ->> FE: 201 Created
    end
```
