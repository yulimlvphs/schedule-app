
1. 일정 생성

항목	내용
Method	POST
URL	/schedules
설명	새로운 일정을 생성합니다.

Request Body

{
  "title": "자바 공부",
  "content": "JPA 공부하기",
  "author": "작성자",
  "password": "1234"
}

Response Body

{
  "id": 1,
  "title": "자바 공부",
  "content": "JPA 공부하기",
  "author": "작성자",
  "createdAt": "2026-06-28T15:30:00",
  "modifiedAt": "2026-06-28T15:30:00"
}

⸻

2. 전체 일정 조회

항목	내용
Method	GET
URL	/schedules
설명	전체 일정을 수정일 기준 내림차순으로 조회합니다.

Query Parameter

이름	필수 여부	설명
author	선택	작성자명으로 일정 조회

Request Example

GET /schedules
GET /schedules?author=작성자

Response Body

[
  {
    "id": 2,
    "title": "스프링 공부",
    "content": "Controller와 Service 공부하기",
    "author": "작성자",
    "createdAt": "2026-06-28T16:00:00",
    "modifiedAt": "2026-06-28T16:00:00"
  },
  {
    "id": 1,
    "title": "자바 공부",
    "content": "JPA 공부하기",
    "author": "작성자",
    "createdAt": "2026-06-28T15:30:00",
    "modifiedAt": "2026-06-28T15:30:00"
  }
]

⸻

3. 선택 일정 조회

항목	내용
Method	GET
URL	/schedules/{scheduleId}
설명	선택한 일정과 해당 일정의 댓글 목록을 조회합니다.

Path Variable

이름	설명
scheduleId	조회할 일정 ID

Response Body

{
  "id": 1,
  "title": "자바 공부",
  "content": "JPA 공부하기",
  "author": "작성자",
  "createdAt": "2026-06-28T15:30:00",
  "modifiedAt": "2026-06-28T15:30:00",
  "comments": [
    {
      "id": 1,
      "content": "댓글입니다.",
      "author": "댓글작성자",
      "scheduleId": 1,
      "createdAt": "2026-06-28T16:10:00",
      "modifiedAt": "2026-06-28T16:10:00"
    }
  ]
}

⸻

4. 일정 수정

항목	내용
Method	PUT
URL	/schedules/{scheduleId}
설명	선택한 일정의 제목과 작성자명을 수정합니다.

Path Variable

이름	설명
scheduleId	수정할 일정 ID

Request Body

{
  "title": "수정된 제목",
  "author": "수정된 작성자",
  "password": "1234"
}

Response Body

{
  "id": 1,
  "title": "수정된 제목",
  "content": "JPA 공부하기",
  "author": "수정된 작성자",
  "createdAt": "2026-06-28T15:30:00",
  "modifiedAt": "2026-06-28T17:00:00"
}

⸻

5. 일정 삭제

항목	내용
Method	DELETE
URL	/schedules/{scheduleId}
설명	선택한 일정을 삭제합니다.

Path Variable

이름	설명
scheduleId	삭제할 일정 ID

Request Body

{
  "password": "1234"
}

Response Body

응답 Body 없음

⸻

6. 댓글 생성

항목	내용
Method	POST
URL	/schedules/{scheduleId}/comments
설명	선택한 일정에 댓글을 생성합니다.

Path Variable

이름	설명
scheduleId	댓글을 작성할 일정 ID

Request Body

{
  "content": "댓글입니다.",
  "author": "댓글작성자",
  "password": "1234"
}

Response Body

{
  "id": 1,
  "content": "댓글입니다.",
  "author": "댓글작성자",
  "scheduleId": 1,
  "createdAt": "2026-06-28T16:10:00",
  "modifiedAt": "2026-06-28T16:10:00"
}

⸻

공통 예외 응답

Response Body

{
  "message": "비밀번호가 일치하지 않습니다."
}

## ERD

```text
┌─────────────────────────────┐
│          Schedule            │
├─────────────────────────────┤
│ PK  id        BIGINT         │
│     title     VARCHAR(30)    │
│     content   VARCHAR(200)   │
│     author    VARCHAR        │
│     password  VARCHAR        │
│     createdAt DATETIME       │
│     modifiedAt DATETIME      │
└─────────────────────────────┘
              │
              │ 1
              │
              │ N
┌─────────────────────────────┐
│           Comment            │
├─────────────────────────────┤
│ PK  id         BIGINT        │
│     content    VARCHAR(100)  │
│     author     VARCHAR       │
│     password   VARCHAR       │
│ FK  scheduleId BIGINT        │
│     createdAt  DATETIME      │
│     modifiedAt DATETIME      │
└─────────────────────────────┘