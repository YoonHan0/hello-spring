## Static Content
말그대로 정적인 페이지를 사용자에게 보여주는 방식이다. <br />

<br />

#### 동작 방식
<img width="880" alt="Image" src="https://github.com/user-attachments/assets/40e87f64-2073-4f2f-bd26-66456ed1ab38" />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

웹 브라우저에서 `request`가 오면 먼저 톰캣이 받아서 
1. `Controller`에 맵핑된 경로가 존재하는지 확인
2. 없다면 `resources/static/` 아래에서 주소를 찾음 [참고](https://docs.spring.io/spring-boot/3.4-SNAPSHOT/reference/web/reactive.html#web.reactive.webflux.static-content)

    일치하는 주소가 있으면 웹 브라우저에 그대로 반환

```
- 테스트 방법
1. 파일생성: src/main/resources/static/출력할파일.html
2. 프로젝트 실행
3. `localhost:8080/출력할파일.html` 경로로 이동
```
- 결과확인 <br />
![Image](https://github.com/user-attachments/assets/7ee85653-ee4b-4706-9dcf-b20fced7a413)