# API 방식

## ⚙️ 동작방식
<img width="646" alt="Image" src="https://github.com/user-attachments/assets/b68250c4-11ae-4561-b68e-a4c3a45953cf" /> <br />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

1. 사용자가 웹 브라우저에서 `request`를 보냄
2. 톰켓 서버가 `Controller`에 해당 `URL`이 있는지 확인
3. 매핑된 메서드에 `@ResponseBody` 어노테이션이 붙어 있으면 <br />
    → `ViewResolver`가 아닌 `HttpMessageConverter`로 데이터 전송
4. 반환되는 데이터 타입에 따라 처리 방식이 달라지게 되는데,
    - **문자 형태**이면
        - `StringConverter`가 처리 (StringHttpMessageConverter)
        - **문자 형태** 그대로 웹 브라우저로 반환
    - **객체 형태**이면
        - `JsonConverter`가 처리 (MappingJackson2HttpMessageConverter)
        - **객체 → JSON** 형태로 변경하여 웹 브라우저로 반환

<br />

---

## ResponseBody 사용

- `Http Response Body`에 **직접 데이터를 반환**
- 템플릿을 사용하지 않고, **데이터 자체를 응답**하는 방식 (`ViewResolver` 대신에 `HttpMessageConverter`가 동작)
- 기본 문자처리: `StringHttpMessageConverter`
- 기본 객체처리: `MappingJacson2HttpMessageConverter`
- byte 처리 등등 기타 여러 `HttpMessageConverter`가 등록되어 있음

<br />

### 📦 기본 동작하는 HttpMessageConverters
| 타입 | Converter |
|------|-----------|
| `String` | `StringHttpMessageConverter` |
| `Object` (ex. DTO) | `MappingJackson2HttpMessageConverter` |
| 기타 바이너리 등 | 필요 시 추가 등록 가능 (`ByteArrayHttpMessageConverter` 등) |

<br />

---

## 🛠️ 테스트 방법

1. Controller에 코드 추가(HelloController.java)
```java
/* 1. API 방식 (반환 형식이 문자일 때) */
@GetMapping("hello-string")
@ResponseBody       // HTML이 아닌 HTTP에서 Head/Body의 그 body 부분에 내가 직접 값을 넣어주겠다~ 라는 의미
public String helloString(@RequestParam("name") String name) {
    return "hello " + name;
}

/* 2. API 방식 (반환 형식이 JSON일 때) */
@GetMapping("hello-api")
@ResponseBody
public Hello helloApi(@RequestParam("name") String name) {
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
}

static public class Hello {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
2. 프로젝트 실행 후, 반환 형식이 문자일 때를 먼저 테스트 해보자면 `http://localhost:8080/hello-string?name=원하는이름` 호출
3. 결과확인 <br />
![Image](https://github.com/user-attachments/assets/420db34c-feff-46c5-a562-d693f329916a)

4. 반환 형식이 객체일 때 테스트를 해보면, `http://localhost:8080/hello-api?name=원하는이름` 호출
5. 결과확인 <br />
![Image](https://github.com/user-attachments/assets/af003b62-9fa6-4030-a4cf-d50d24bbdb25)

<br />

### ❌ 테스트 중 발생했던 오류
Spring 강의에서는 아래와 같이 코드를 작성했었는데 *( class 앞에 public 없이 )*

``` java
/* hello.hello_spring.controller.HelloController.java */

static class Hello {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
`hello` 객체가 반환되는 부분에서 실행은 되지만<br />
`Class 'Hello' is exposed outside its defined visibility scope`와 같은 오류가 발생할 수 있다.

<br />

#### 💡 오류가 발생하는 이유는

Spring이 객체를 JSON으로 변환할 때 클래스의 접근 제한자를 엄격하게 체크하기 때문이라고 한다.

Spring에서 `@ResponseBody`를 사용하면 객체(`Hello`)를 JSON으로 변환하는 과정이 필요한데, <br />
이 변환을 담당하는게 위에서 말한 **Jackson**이라는 라이브러리이다.

<br />

Jackson은 객체를 JSON으로 변환할 때,
1. 기본 생성자가 존재하는지 확인
2. 필드에 접근할 수 있는 Getter 메서드가 있는지 확인
3. 클래스가 직렬화 가능하고, 접근할 수 있는 범위인지 확인을 한다.

<br />

Spring은 기본적으로 객체를 반환할 때 JSON 형태로 반환한다고 한다. *xml형태도 있지만*

<br />
<br />

---

## 💭 같이 공부해 볼 개념

### ✅ JSON이란?

**J**ava **S**cript **O**bject **N**otation의 약자이다. <br />
데이터를 쉽게 **교환**하고 **저장**하기 위한 텍스트 기반의 데이터 교환 표준이다.

#### 기본 형태

```xml
{ key : value }
```
JSON의 형태는 키(key), 값(value)의 쌍으로 이루어진 구조이다. [참고](https://codingazua.tistory.com/4)

<br />


### ✅ 자바빈 프로퍼티란?
Spring 프레임워크에서는 자바빈 프로퍼티를 활용하여 객체의 상태를 캡슐화하고, 쉽게 접근하고 조작할 수 있는 기능을 제공한다. [참고](https://jjangadadcodingdiary.tistory.com/entry/Spring-%EC%9E%90%EB%B0%94%EB%B9%88-%ED%94%84%EB%A1%9C%ED%8D%BC%ED%8B%B0Property%EC%9D%98-%EA%B0%9C%EB%85%90%EA%B3%BC-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)

**자바빈 프로퍼티는** <br/>
- 객체의 필드에 접근하기 위한 Getter, Setter 메서드를 통칭하는 용어이다.
- Spring에서는 자바빈 프로퍼티를 활용하여 객체의 상태를 캡슐화하고, 외부에서 안전하게 필드에 접근하고 조작할 수 있도록 지원한다.
- 자바빈 프로퍼티의 역할로는
  - 캡슐화
  - 접근 제어
  - 데이터 바인딩

 <br />
 
 
### ✅ JsonConverter? MappingJackson2HttpMessageConverter?는 같은 것일까?
- `JsonConverter`는 일반적인 개념 또는 약칭이고,
- `MappingJackson2HttpMessageConverter`는 Spring이 실제로 사용하는 구현체 클래스입니다.
- `StringConverter`와 `StringHttpMessageConverter`도 같은 개념입니다.

