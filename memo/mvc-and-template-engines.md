## MVC와 템플릿 엔진

- MVC: Model, View, Controller



#### 패턴을 가지고 역할을 나누는 이유

- **관심사의 분리(Separation of Concerns, SoC)**

    각각의 역할을 독립적으로 나누어 코드가 서로 영향을 주지 않도록하는 원칙을 말함. <br /><br />
    
    예를 들어

    - UI(화면 표시) → 사용자 인터페이스 관련 코드만 다룸
    - 비즈니스 로직 → 데이터 처리, 계산, 규칙 적용 등 핵심 로직 담당
    - 데이터 접근 → DB와의 연결 및 데이터 저장 처리 <br /><br />

    장점
    - 유지보수성 증가
    - 재사용성 증가
    - 테스트 용이성 증가 ...


<br /><br />

#### 동작방식
<img width="910" alt="Image" src="https://github.com/user-attachments/assets/7da00d12-3b4d-4d8c-a640-5fa6a4fd3fcd" />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

<br />

1. 웹 브라우저에서 `request`이 오면 먼저 톰캣에서 받게 됨
2. `Controller`에 맵핑된 주소가 있는지 확인
3. 있으면 `return 이동할 템플릿`으로 리턴
4. `viewResolver`에서 HTML 변환 후 웹 브라우저에 출력

<br />

``` java
/* 테스트 방법 */
// 1. src/main/resources/templages/원하는이름의파일.html
// 2. Controller.java 에 코드 추가
@GetMapping("hello-mvc")
public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
}
// 3. localhost:8080/hello-mvc?name=원하는이름 호출
```

<br /><br />

#### 결과확인

![Image](https://github.com/user-attachments/assets/7125b06b-9cc0-4a7d-beac-53fcf6854172)
