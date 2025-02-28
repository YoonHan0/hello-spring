## 회원 관리 예제 - 백엔드 개발

<br />

### 일반적인 웹 애플리케이션 구조
<img width="563" alt="Image" src="https://github.com/user-attachments/assets/a2637fdb-8fb1-453b-837b-5e865056aed1" /> <br />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

- 컨트롤러: 웹 MVC의 컨트롤러 역할
- 서비스: 핵심 비즈니스 로직 구현
- 리포지토리: 데이터베이스 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인: 비즈니스 도메인 객체, ex) 회원, 주문, 쿠폰 등 주로 데이터베이스에 저장하고 관리됨

<br />

### 개발할 예제의 클래스 의존관계
<img width="559" alt="Image" src="https://github.com/user-attachments/assets/859aafe1-0939-4c35-8cc8-8d8de3511fb8" /> <br /> 
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

### 프로젝트 구조
```
├── 📂 backend         
│   ├── 📂 src
│   │   ├── 📂 main
│   │   │   ├── 📂 java/com/hello/hello_spring  
│   │   │   │   ├── 📂 controller    # API 컨트롤러
│   │   │   │   │   ├── 📜 HelloController.java  
│   │   │   │   ├── 📂 service       # 비즈니스 로직
│   │   │   │   ├── 📂 domain        # 비즈니스 도메인 객체
│   │   │   │   │   ├── 📜 Member.java
│   │   │   │   ├── 📂 repository    # 데이터 액세스 계층
│   │   │   │   │   ├── 📜 MemberRepository.java         # 인터페이스
│   │   │   │   │   ├── 📜 MemoryMemberRepository.java   # 구현체
│   │   │   │   ├── 📜 HelloSpringApplication.java  # Spring Boot 실행 파일
│   │   ├── 📂 test  # 테스트 코드
│   ├── 📜 build.gradle
│   │
│   ├── ...
```

### 테스트 코드 작성
...

<br />
<br />

회원 관리 예제를 개발하면서 인터페이스를 사용하였는데, <br />
'개발 시 자주 사용하는 인터페이스, 추상클래스에 대해서 자세하게 정리를 한 번 해보자'라는 생각이 들어 확실하게 정리하게 되었습니다.

### 추상 클래스란?

#### 1. 추상 클래스와 추상화
우리가 지금까지 사용해왔던 일반적인 클래스는 구체적(concrete)으로 데이터를 담아 인스턴스화 하여 직접 다루는 클래스이다.
그 반대로 추상 클래스는 구체적이지 않은 추상적인(abstract) 데이터를 담고 있는 클래스이다.
그래서 추상 클래스는 일반 클래스와 달리 인스턴스 화가 불가능한 클래스이며, 추상 클래스를 선언할 때는 `abstract` 키워드를 사용한다는 차이점이 있다고 말하곤 한다.
하지만 우리가 알아야 할 것은 *문법적인 특징이나 객체 생성이 되고, 안되고와 같은 특징들이 아니다.*
추상 클래스가 무엇이고 왜 사용하는지 본질적인 개념을 알아야 자연스럽게 객체 지향 프로그래밍의 추상 클래스 용도를 이해할 수 있다.

블로그에서 예시로 들어준 코드가 있는데
문자를 대문자로 변환해주는 `String.toUpperCase()` 라는 메서드를 보면, 우리는 이 메서드가 내부적으로 어떻게 동작해서 소문자를 대문자로 뱐환해 주는지 로직을 몰라도 사용할 수 있다.
그저 이 메서드가 반환해 주는 문자열을 받아서 사용할 뿐이다.
즉, 우리는 구체적으로 알지도 못하는 `String.toUpperCase()`라는 클래스와 그 안에 있는 메서드를 생각없이, 추상적으로 사용하고 있었던 것이다.
``` java
String text = "Hello world";
String textUpper = text.toUpperCase(); 
// 우리는 메서드 내부가 어떤식으로 돌아가는지 생각 없이 결과값만 받을 뿐이다. 
// 그냥 메서드 내부에서 대충 알아서 잘 대문자로 마술같이 변환해주겠지 하고 막연하게 추상적으로 생각할 뿐이다.

System.out.println(textUpper); // "HELLO WORLD";
```
블로그에서는 추가로 `for`, `while` 문에 대해서도 짧게 언급하였습니다.

...
...


> **참고문헌** <br />
> [추상클래스-용도-완변-이해하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%B6%94%EC%83%81-%ED%81%B4%EB%9E%98%EC%8A%A4Abstract-%EC%9A%A9%EB%8F%84-%EC%99%84%EB%B2%BD-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0)