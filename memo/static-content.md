# Static Content
말그대로 정적인 페이지를 사용자에게 보여주는 방식이다. <br />

<br />

## 동작 방식
<img width="880" alt="Image" src="https://github.com/user-attachments/assets/40e87f64-2073-4f2f-bd26-66456ed1ab38" />

_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_

<br />

웹 브라우저에서 `localhost:8080/hello-static.html`과 같은 요청이 오면 톰캣이 먼저 받습니다.


톰캣은 해당 요청이 `Controller`에 매핑된 경로인지 확인합니다.
- 경로가 존재하면, 해당 경로에 맞는 처리를 반환합니다. (뒤에서 학습할 내용이므로 자세한 설명은 뒤에서 하도록 하겠습니다.)
- 경로가 존재하지 않으면, `resources/static/` 디렉터리에서 요청된 파일을 찾습니다. [Spring 공식문서](https://docs.spring.io/spring-boot/3.4-SNAPSHOT/reference/web/reactive.html#web.reactive.webflux.static-content)
  
<br />

## 테스트 방법
1. 파일생성(hello-static.html)
```html
<!-- src/main/resources/static/ 아래의 경로에 파일 생성 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>static content</title>
</head>
<body>
정적 컨텐츠입니다.
</body>
</html>

```
2. 프로젝트 실행
3. `localhost:8080/hello-static.html` 경로로 이동
4. 결과확인 <br />
![Image](https://github.com/user-attachments/assets/7ee85653-ee4b-4706-9dcf-b20fced7a413)

