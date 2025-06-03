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

- ★マークのついたエンドポイントは実装の優先度が低い

Base URLs:

* <a href="http://localhost:8080">http://localhost:8080</a>

# Authentication

- HTTP Authentication, scheme: bearer 

<h1 id="bgm-api-bgm">bgm</h1>

## BGM一覧を取得・タグ検索

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/bgm \
  -H 'Accept: application/json'

```

`GET /bgm`

<h3 id="bgm一覧を取得・タグ検索-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|tags|query|array[string]|false|タグ検索用クエリ(小文字の英字のみ、複数の場合はカンマで区切る)|

> Example responses

> 200 Response

```json
{
  "isSuccess": true,
  "data": [
    {
      "id": 0,
      "title": "string",
      "url": "http://example.com",
      "createdAt": "2019-08-24T14:15:22Z",
      "tags": [
        "string"
      ]
    }
  ]
}
```

<h3 id="bgm一覧を取得・タグ検索-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|BGM一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|

<h3 id="bgm一覧を取得・タグ検索-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» data|[[BgmResponse](#schemabgmresponse)]|false|none|none|
|»» id|integer|false|none|none|
|»» title|string|false|none|none|
|»» url|string(uri)|false|none|none|
|»» createdAt|string(date-time)|false|none|none|
|»» tags|[string]|false|none|none|

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## BGMを投稿

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/bgm \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /bgm`

投稿者データ(createdBy)はJWTトークンからサーバ側で判別するため、リクエストボディには含めない

> Body parameter

```json
{
  "title": "string",
  "url": "http://example.com",
  "tags": [
    "rain",
    "cafe"
  ]
}
```

<h3 id="bgmを投稿-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[BgmRequest](#schemabgmrequest)|true|none|

> Example responses

> 400 Response

```json
{
  "isSuccess": false,
  "message": "不正なリクエストです"
}
```

<h3 id="bgmを投稿-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|BGMを投稿しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|

<h3 id="bgmを投稿-responseschema">Response Schema</h3>

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## 指定したBGMの情報を取得

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/bgm/{id} \
  -H 'Accept: application/json'

```

`GET /bgm/{id}`

<h3 id="指定したbgmの情報を取得-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|

> Example responses

> 200 Response

```json
{
  "isSuccess": true,
  "data": {
    "id": 0,
    "title": "string",
    "url": "http://example.com",
    "createdAt": "2019-08-24T14:15:22Z",
    "tags": [
      "string"
    ]
  }
}
```

<h3 id="指定したbgmの情報を取得-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|BGMのデータを取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="指定したbgmの情報を取得-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» data|[BgmResponse](#schemabgmresponse)|false|none|none|
|»» id|integer|false|none|none|
|»» title|string|false|none|none|
|»» url|string(uri)|false|none|none|
|»» createdAt|string(date-time)|false|none|none|
|»» tags|[string]|false|none|none|

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## 指定したBGMを削除【投稿ユーザと管理者のみ】★

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/bgm/{id} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`DELETE /bgm/{id}`

<h3 id="指定したbgmを削除【投稿ユーザと管理者のみ】★-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|

> Example responses

> 400 Response

```json
{
  "isSuccess": false,
  "message": "不正なリクエストです"
}
```

<h3 id="指定したbgmを削除【投稿ユーザと管理者のみ】★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|投稿を削除しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="指定したbgmを削除【投稿ユーザと管理者のみ】★-responseschema">Response Schema</h3>

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-bookmark">bookmark</h1>

## ブックマーク一覧を取得・タグ検索

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/bookmark \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`GET /bookmark`

<h3 id="ブックマーク一覧を取得・タグ検索-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|tags|query|array[string]|false|タグ検索用クエリ(小文字の英字のみ、複数の場合はカンマで区切る)|

> Example responses

> 200 Response

```json
{
  "isSuccess": true,
  "data": [
    {
      "id": 0,
      "title": "string",
      "url": "http://example.com",
      "createdAt": "2019-08-24T14:15:22Z",
      "tags": [
        "string"
      ]
    }
  ]
}
```

<h3 id="ブックマーク一覧を取得・タグ検索-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|ブックマーク一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="ブックマーク一覧を取得・タグ検索-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» data|[[BgmResponse](#schemabgmresponse)]|false|none|none|
|»» id|integer|false|none|none|
|»» title|string|false|none|none|
|»» url|string(uri)|false|none|none|
|»» createdAt|string(date-time)|false|none|none|
|»» tags|[string]|false|none|none|

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## ブックマークに登録

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/bookmark \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /bookmark`

> Body parameter

```json
{
  "bgmId": 0
}
```

<h3 id="ブックマークに登録-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[BookmarkRequest](#schemabookmarkrequest)|true|none|

> Example responses

> 400 Response

```json
{
  "isSuccess": false,
  "message": "不正なリクエストです"
}
```

<h3 id="ブックマークに登録-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|ブックマークに追加しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="ブックマークに登録-responseschema">Response Schema</h3>

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## ブックマークの削除

> Code samples

```shell
# You can also use wget
curl -X DELETE http://localhost:8080/bookmark/{bgmId} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`DELETE /bookmark/{bgmId}`

<h3 id="ブックマークの削除-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|bgmId|path|integer|true|none|

> Example responses

> 400 Response

```json
{
  "isSuccess": false,
  "message": "不正なリクエストです"
}
```

<h3 id="ブックマークの削除-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|ブックマークを削除しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="ブックマークの削除-responseschema">Response Schema</h3>

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-tag">tag</h1>

## 利用可能なタグ一覧を取得

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/tag \
  -H 'Accept: application/json'

```

`GET /tag`

> Example responses

> 200 Response

```json
{
  "isSuccess": true,
  "data": [
    {
      "id": 0,
      "title": "string"
    }
  ]
}
```

<h3 id="利用可能なタグ一覧を取得-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|タグ一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|

<h3 id="利用可能なタグ一覧を取得-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» data|[[TagResponse](#schematagresponse)]|false|none|none|
|»» id|integer|false|none|none|
|»» title|string|false|none|none|

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## 指定したBGMにタグを追加

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/bgm/{id}/tag \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /bgm/{id}/tag`

> Body parameter

```json
{
  "tag": "happy"
}
```

<h3 id="指定したbgmにタグを追加-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|
|body|body|[TagRequest](#schematagrequest)|true|none|

> Example responses

> 400 Response

```json
{
  "isSuccess": false,
  "message": "不正なリクエストです"
}
```

<h3 id="指定したbgmにタグを追加-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|タグを追加しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="指定したbgmにタグを追加-responseschema">Response Schema</h3>

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

<h1 id="bgm-api-report">report</h1>

## 違反報告一覧を取得【管理者のみ】★

> Code samples

```shell
# You can also use wget
curl -X GET http://localhost:8080/report \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`GET /report`

> Example responses

> 200 Response

```json
{
  "isSuccess": true,
  "data": [
    {
      "id": 0,
      "bgmId": 0,
      "reportedBy": "string",
      "reason": "string",
      "createdAt": "2019-08-24T14:15:22Z"
    }
  ]
}
```

<h3 id="違反報告一覧を取得【管理者のみ】★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|報告一覧を取得しました|Inline|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|

<h3 id="違反報告一覧を取得【管理者のみ】★-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» data|[[ReportResponse](#schemareportresponse)]|false|none|none|
|»» id|integer|false|none|none|
|»» bgmId|integer|false|none|none|
|»» reportedBy|string|false|none|none|
|»» reason|string|false|none|none|
|»» createdAt|string(date-time)|false|none|none|

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

## 違反BGMを通報★

> Code samples

```shell
# You can also use wget
curl -X POST http://localhost:8080/report \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

`POST /report`

> Body parameter

```json
{
  "bgmId": 0,
  "reason": "string"
}
```

<h3 id="違反bgmを通報★-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[ReportRequest](#schemareportrequest)|true|none|

> Example responses

> 400 Response

```json
{
  "isSuccess": false,
  "message": "不正なリクエストです"
}
```

<h3 id="違反bgmを通報★-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|違反報告を送信しました|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|不正なリクエストです|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|認証が必要です|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|権限がありません|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|該当リソースが存在しません|Inline|

<h3 id="違反bgmを通報★-responseschema">Response Schema</h3>

Status Code **400**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **401**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **403**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

Status Code **404**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|» isSuccess|boolean|false|none|none|
|» message|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
firebase
</aside>

# Schemas

<h2 id="tocS_BgmRequest">BgmRequest</h2>
<!-- backwards compatibility -->
<a id="schemabgmrequest"></a>
<a id="schema_BgmRequest"></a>
<a id="tocSbgmrequest"></a>
<a id="tocsbgmrequest"></a>

```json
{
  "title": "string",
  "url": "http://example.com",
  "tags": [
    "rain",
    "cafe"
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|title|string|true|none|none|
|url|string(uri)|true|none|none|
|tags|[string]|false|none|none|

<h2 id="tocS_TagRequest">TagRequest</h2>
<!-- backwards compatibility -->
<a id="schematagrequest"></a>
<a id="schema_TagRequest"></a>
<a id="tocStagrequest"></a>
<a id="tocstagrequest"></a>

```json
{
  "tag": "happy"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|tag|string|true|none|none|

<h2 id="tocS_BookmarkRequest">BookmarkRequest</h2>
<!-- backwards compatibility -->
<a id="schemabookmarkrequest"></a>
<a id="schema_BookmarkRequest"></a>
<a id="tocSbookmarkrequest"></a>
<a id="tocsbookmarkrequest"></a>

```json
{
  "bgmId": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|bgmId|integer|true|none|none|

<h2 id="tocS_ReportRequest">ReportRequest</h2>
<!-- backwards compatibility -->
<a id="schemareportrequest"></a>
<a id="schema_ReportRequest"></a>
<a id="tocSreportrequest"></a>
<a id="tocsreportrequest"></a>

```json
{
  "bgmId": 0,
  "reason": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|bgmId|integer|true|none|none|
|reason|string|true|none|none|

<h2 id="tocS_BgmResponse">BgmResponse</h2>
<!-- backwards compatibility -->
<a id="schemabgmresponse"></a>
<a id="schema_BgmResponse"></a>
<a id="tocSbgmresponse"></a>
<a id="tocsbgmresponse"></a>

```json
{
  "id": 0,
  "title": "string",
  "url": "http://example.com",
  "createdAt": "2019-08-24T14:15:22Z",
  "tags": [
    "string"
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer|false|none|none|
|title|string|false|none|none|
|url|string(uri)|false|none|none|
|createdAt|string(date-time)|false|none|none|
|tags|[string]|false|none|none|

<h2 id="tocS_TagResponse">TagResponse</h2>
<!-- backwards compatibility -->
<a id="schematagresponse"></a>
<a id="schema_TagResponse"></a>
<a id="tocStagresponse"></a>
<a id="tocstagresponse"></a>

```json
{
  "id": 0,
  "title": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer|false|none|none|
|title|string|false|none|none|

<h2 id="tocS_ReportResponse">ReportResponse</h2>
<!-- backwards compatibility -->
<a id="schemareportresponse"></a>
<a id="schema_ReportResponse"></a>
<a id="tocSreportresponse"></a>
<a id="tocsreportresponse"></a>

```json
{
  "id": 0,
  "bgmId": 0,
  "reportedBy": "string",
  "reason": "string",
  "createdAt": "2019-08-24T14:15:22Z"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer|false|none|none|
|bgmId|integer|false|none|none|
|reportedBy|string|false|none|none|
|reason|string|false|none|none|
|createdAt|string(date-time)|false|none|none|

