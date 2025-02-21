### Thymeleaf 사용하기

<br />

Thymeleaf 사용 방법을 알아보기 전에 스프링 부트에서의 전체적인 흐름을 알고 있는게 좋을 것 같아

이미지 첨부하였습니다.

![Image](https://github.com/user-attachments/assets/be94489c-80f4-43ba-a003-b5f486574293)
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

<br />

Spring Boot에서 Template Engines으로
- FreeMarker
- [Thymeleaf](https://www.thymeleaf.org/)
- Mustache
- ...

등을 사용하는데 [참고](https://docs.spring.io/spring-boot/3.4-SNAPSHOT/reference/web/reactive.html#web.reactive.webflux.template-engines)

Thymeleaf 사용법을 알아보면

1. `src/main/java/hello.hello_spring/controller/HelloController.java`


``` java
  // 1. hello_spring 아래에 controller package를 생성하고
  // 2. 생성한 package 아래에 HelloController.java 클래스를 생성하면 된다.
  package hello.hello_spring.controller;
  import org.springframework.stereotype.Controller;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.GetMapping;
  
  @Controller
  public class HelloController {
  
    @GetMapping("hello")                        // get 방식으로 접근하는 url 경로
    public String hello(Model model) {
      model.addAttribute("data", "Hello!!");    // model객체에 값을 담아서 hello.html의 ${data}값에 여기의 data 키의 값으로 바인딩 됨.
      return "hello";                           // resources/templates/hello.html 을 찾아서 반환해줌
    }
  }
```

2. `resources/templates/hello.html`

``` html
  <!DOCTYPE html>
  <html xmlns:th="http://www.thymeleaf.org">    <!-- import Thymeleaf, th로 타입리프 문법을 사용하겠다는 의미. -->
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Hello</title>
  </head>
  <body>
    <p th:text="'안녕하세요.  '+ ${data}">안녕하세요 손님</p>   <!-- Thymeleaf 문법이 문자열을 사용하려면 '안녕하세요' 이런 식으로 사용하여야 함. -->
  </body>
  </html>
```

3. 결과화면

   ![Image](https://github.com/user-attachments/assets/c1e4fc82-6171-4fd2-a030-86389527d645)
