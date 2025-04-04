# MVC와 템플릿 엔진
**MVC**는 `Model`, `View`, `Controller`의 약자로, 애플리케이션을 구성하는 주요 역할을 분리한 아키텍처 패턴입니다.

<br />

## 패턴을 가지고 역할을 나누는 이유

- **관심사의 분리(Separation of Concerns, SoC)**

    각각의 역할을 독립적으로 나누어, 코드 간의 영향도를 줄이는 것이 핵심입니다. <br /><br />
    
#### 예를 들어
- UI(화면 표시) → 사용자 인터페이스 관련 코드만 다룸
- 비즈니스 로직 → 데이터 처리, 계산, 규칙 적용 등 핵심 로직 담당
- 데이터 접근 → DB와의 연결 및 데이터 저장 처리 <br /><br />

#### ✅ 장점
- 유지보수성 증가
- 재사용성 증가
- 테스트 용이성 증가 ...


<br /><br />

## ⚙️ 동작방식
<img width="910" alt="Image" src="https://github.com/user-attachments/assets/7da00d12-3b4d-4d8c-a640-5fa6a4fd3fcd" /> <br />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

<br />

1. 사용자가 웹 브라우저에서 `localhost:8080/hello-mvc`와 같은 요청을 보냄  
2. **톰캣**이 요청을 받고, `Controller`에 매핑된 경로가 있는지 확인  
3. ✅ 경로가 있다면 → `return "템플릿 이름"`으로 반환  
4. **ViewResolver**가 해당 템플릿 파일을 찾아 HTML로 변환  
5. 변환된 HTML이 브라우저에 출력됨

<br />

## 🛠️ 테스트 방법
1. 파일 생성(hello-template.html)
```html
<!-- src/main/resources/templages/ 아래에 파일 생성 -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Hello Template</title>
</head>
<body>
<p th:text="'hello '+ ${name}">hello Empty</p>    <!-- 해당 코드는 Thymeleaf 문법인데 아래 참고 항목 링크 확인 부탁드립니다. -->
</body>
</html>
```
2. `controller` 패키지 생성 및 파일 생성
```java
/*
    1. 프로젝트 아래에 controller 패키지 생성
    2. 'HelloController'라는 이름으로 클래스 생성
*/
```
3. `Controller`에 코드 추가(HelloController.java)
```java
/* 2. 템플릿 엔진 방식 */
@GetMapping("hello-mvc")
public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
}
```
4. 브라우저에서 `localhost:8080/hello-mvc?name=원하는이름` 호출
5. 결과확인 <br />
![Image](https://github.com/user-attachments/assets/7125b06b-9cc0-4a7d-beac-53fcf6854172)

<br />
<br />
<br />

> 참고 <br />
> [Thymeleaf 사용법 정리](https://github.com/YoonHan0/hello-spring/blob/main/memo/thymeleaf-basics.md)
