### Welcome Page

Spring Boot는 `resources/static/index.html`을 가장 먼저 읽는다. [참고](https://docs.spring.io/spring-boot/reference/web/reactive.html#web.reactive.webflux.welcome-page)

#### 테스트하기

1. index.html 파일 생성
```dtd
// src/resources/static/index.html
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Hello</title>
    </head>
    <body>
        Hello
        <a href="/hello">hello</a>
    </body>
</html>
```
<br />

2. `localhost:8080` 접속

   결과확인

   ![Image](https://github.com/user-attachments/assets/a65c8216-53ea-4f5b-9a51-9b2cf597e7bf)


3. hello 링크 클릭을 하게 되면

   아래처럼 `Error Page`가 출력됨
   ![Image](https://github.com/user-attachments/assets/4b7ecd48-7f85-4370-aea2-3fcde5094b65)
