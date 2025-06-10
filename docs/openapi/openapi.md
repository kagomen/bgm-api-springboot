---
title: BGM API v1.0.0
language_tabs:
  - shell: Shell
language_clients:
  - shell: ""
toc_footers: []
includes: []
search: false
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="bgm-api">BGM API v1.0.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

- ★のついたエンドポイントは実装の優先度低
- 🔓のついたエンドポイントはログインユーザーのみ使用可
  - その他のロール権限があるエンドポイントは【】内にその旨を記述

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

# Authentication

- HTTP Authentication, scheme: bearer 

<h1 id="bgm-api-auth">auth</h1>

## ユーザー登録と内部IDの発行(初回ログイン時のみ)

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/register_user \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /register_user`

- ユーザーがFirebaseでログインした後、初回だけ呼び出される
- ID Tokenを検証し、該当するユーザーが存在しなければUserテーブルを作成しIDを返却、存在すればIDだけ返却
  - 別端末の同Googleアカウントからのログインなどの可能性
- 投稿者データ(userId)はJWTトークンからサーバ側で判別するため、リクエストボディには含めない

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": null
}
```

<h3 id="ユーザー登録と内部idの発行(初回ログイン時のみ)-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|ユーザーを確認しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<h3 id="ユーザー登録と内部idの発行(初回ログイン時のみ)-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|any|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-bgm">bgm</h1>

## BGM一覧を取得(タグ検索)

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/get_bgm_list \
  -H 'Accept: application/json'

```

`GET /get_bgm_list`

<h3 id="bgm一覧を取得(タグ検索)-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|tags|query|array[string]|false|タグ検索用クエリ(小文字の英字のみ、複数の場合はカンマで区切る)|

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": []
}
```

<h3 id="bgm一覧を取得(タグ検索)-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|BGM一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|

<h3 id="bgm一覧を取得(タグ検索)-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|array|false|none|none|
|»» *anonymous*|any|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## 指定したユーザーが投稿したBGM一覧を取得

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/get_bgm_list/{userId} \
  -H 'Accept: application/json'

```

`GET /get_bgm_list/{userId}`

<h3 id="指定したユーザーが投稿したbgm一覧を取得-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|userId|path|integer|true|none|

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": []
}
```

<h3 id="指定したユーザーが投稿したbgm一覧を取得-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|BGM一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<h3 id="指定したユーザーが投稿したbgm一覧を取得-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|array|false|none|none|
|»» *anonymous*|any|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## 指定したBGMの情報を取得

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/get_bgm/{bgmId} \
  -H 'Accept: application/json'

```

`GET /get_bgm/{bgmId}`

<h3 id="指定したbgmの情報を取得-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": null
}
```

<h3 id="指定したbgmの情報を取得-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|BGMのデータを取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<h3 id="指定したbgmの情報を取得-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|any|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## BGMを投稿

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/post_bgm \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /post_bgm`

投稿者データ(userId)はJWTトークンからサーバ側で判別するため、リクエストボディには含めない

> Body parameter

```json
false
```

<h3 id="bgmを投稿-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|BGMを投稿しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## 指定したBGMを論理削除【投稿ユーザと管理者のみ】★

> Code samples

```shell
# You can also use wget
curl -X PATCH http://localhost:8080/delete_bgm/{bgmId} \
  -H 'Authorization: Bearer {access-token}'

```

`PATCH /delete_bgm/{bgmId}`

<h3 id="指定したbgmを論理削除【投稿ユーザと管理者のみ】★-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|

<h3 id="指定したbgmを論理削除【投稿ユーザと管理者のみ】★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|投稿を削除しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-tag">tag</h1>

## 利用可能なタグ一覧を取得

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/get_tag_list \
  -H 'Accept: application/json'

```

`GET /get_tag_list`

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": []
}
```

<h3 id="利用可能なタグ一覧を取得-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|タグ一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|

<h3 id="利用可能なタグ一覧を取得-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|array|false|none|none|
|»» *anonymous*|any|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## 指定したBGMにタグを追加

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/add_tag/{bgmId} \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /add_tag/{bgmId}`

> Body parameter

```json
false
```

<h3 id="指定したbgmにタグを追加-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|

<h3 id="指定したbgmにタグを追加-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|タグを追加しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## 指定したBGMから指定したタグを物理削除

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/remove_tag/{bgmId}/{tagId} \
  -H 'Authorization: Bearer {access-token}'

```

`DELETE /remove_tag/{bgmId}/{tagId}`

タグは複数個同時に削除できない

<h3 id="指定したbgmから指定したタグを物理削除-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|
|tagId|path|integer|true|none|

<h3 id="指定したbgmから指定したタグを物理削除-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|タグを削除しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-bookmark">bookmark</h1>

## ブックマーク一覧を取得(タグ検索)

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/get_bookmark_list \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`GET /get_bookmark_list`

<h3 id="ブックマーク一覧を取得(タグ検索)-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|tags|query|array[string]|false|タグ検索用クエリ(小文字の英字のみ、複数の場合はカンマで区切る)|

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": []
}
```

<h3 id="ブックマーク一覧を取得(タグ検索)-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|ブックマーク一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<h3 id="ブックマーク一覧を取得(タグ検索)-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|array|false|none|none|
|»» *anonymous*|any|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## ブックマークに登録

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/add_bookmark/{bgmId} \
  -H 'Authorization: Bearer {access-token}'

```

`POST /add_bookmark/{bgmId}`

<h3 id="ブックマークに登録-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|

<h3 id="ブックマークに登録-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|ブックマークに追加しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## ブックマークの物理削除

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/delete_bookmark/{bgmId} \
  -H 'Authorization: Bearer {access-token}'

```

`DELETE /delete_bookmark/{bgmId}`

<h3 id="ブックマークの物理削除-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|

<h3 id="ブックマークの物理削除-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|ブックマークを削除しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-report">report</h1>

## 違反BGMを通報★

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/post_report \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /post_report`

> Body parameter

```json
false
```

<h3 id="違反bgmを通報★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|違反報告を送信しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## 違反報告一覧を取得【管理者のみ】★

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/get_report_list \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`GET /get_report_list`

> Example responses

> 200 Response

```json
{
  "success": true,
  "data": []
}
```

<h3 id="違反報告一覧を取得【管理者のみ】★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|報告一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|

<h3 id="違反報告一覧を取得【管理者のみ】★-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» success|boolean|false|none|none|
|» data|array|false|none|none|
|»» *anonymous*|any|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## ユーザーの論理削除【管理者のみ】★

> Code samples

```shell
# You can also use wget
curl -X PATCH http://localhost:8080/ban_user/{userId} \
  -H 'Authorization: Bearer {access-token}'

```

`PATCH /ban_user/{userId}`

アカウントBAN用

<h3 id="ユーザーの論理削除【管理者のみ】★-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|userId|path|integer|true|none|

<h3 id="ユーザーの論理削除【管理者のみ】★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|ユーザーをBANしました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## 違反報告への対応内容を記録する【管理者のみ】★

> Code samples

```shell
# You can also use wget
curl -X PATCH http://localhost:8080/update_report/{reportId} \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`PATCH /update_report/{reportId}`

> Body parameter

```json
false
```

<h3 id="違反報告への対応内容を記録する【管理者のみ】★-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|reportId|path|integer|true|none|

<h3 id="違反報告への対応内容を記録する【管理者のみ】★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|対応内容を記録しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|none|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|none|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|none|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|none|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

