# fastlaneTest
fastlane 백엔드개발자(주니어) 사전 테스트

### 1. API URL 확인 페이지
http://localhost:18090/swagger-ui.html

### 2. Local API Test
src/test/http-request/api-user.http


### 3. DB CREATE QUERY
```
CREATE TABLE user
(
    id     varchar(255) NOT NULL,
    password     varchar(255) NOT NULL,

    CONSTRAINT uix_username UNIQUE (id)
);
```

