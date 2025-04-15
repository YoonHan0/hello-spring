# 회원 관리 예제 - 프로젝트 구조 및 개념 정리

<br />

# 🌐 Spring 웹 애플리케이션 구조
<img width="563" alt="Image" src="https://github.com/user-attachments/assets/a2637fdb-8fb1-453b-837b-5e865056aed1" /> <br />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small> <br /><br />
Spring을 사용한 일반적인 웹 애플리케이션은 **역할별로 계층을 나누어 구조화**되어 있습니다. <br />
이렇게 계층을 나누면 **유지보수성이 좋아지고, 확장성이 뛰어나며, 코드의 가독성이 향상**됩니다.

<br />

## 🏗️ 계층별 역할 정리

### 1. Controller (컨트롤러)
**"사용자 요청을 받아서 처리하는 역할"**  
- 웹 MVC의 컨트롤러 역할, 사용자가 웹에서 요청을 보내면, 컨트롤러가 그 요청을 받아 적절한 로직을 호출
- `@Controller` 또는 `@RestController` 어노테이션을 사용
- `Service` 계층을 호출해서 필요한 로직을 수행한 후, 결과를 반환

<br />

### 2. Service (서비스)
**"비즈니스 로직 구현하는 핵심 계층"**
- 데이터를 조작하거나, 여러 개의 리포지토리를 호출하는 등 핵심적인 로직을 처리
- `@Service` 어노테이션을 사용
- `Controller`와 `Repository` 사이에서 중간 역할을 수행

<br />

### 3. Repository (리포지토리)
**"데이터베이스 접근, 도메인 객체를 DB에 저장하고 관리하는 계층"**
- 데이터 저장, 조회, 수정, 삭제 등의 기능을 수행
- JPA, MyBatis, JDBC 등의 기술을 사용할 수 있음
- `@Repository` 어노테이션을 사용 (Spring Data JPA를 사용하면 생략 가능)

<br />

### 4. Domain (도메인)
**"비즈니스 도메인 객체"**
- 일반적으로 데이터베이스에 저장되는 회원, 주문, 상품, 쿠폰 등과 같은 개념
- JPA를 사용한다면 `@Entity` 어노테이션을 사용하여 JPA가 관리하도록 설정 가능
- 도메인 객체는 비즈니스 규칙을 포함할 수도 있음 (예: 할인 정책)

<br />

---

<br />

## 📁 프로젝트 구조
```
├── 📂 backend         
│   ├── 📂 src
│   │   ├── 📂 main
│   │   │   ├── 📂 java/com/hello/hello_spring  
│   │   │   │   ├── 📂 controller    # API 컨트롤러
│   │   │   │   │   ├── 📜 MemberController.java
│   │   │   │   │   ├── 📜 HomeController.java  
│   │   │   │   ├── 📂 service       # 비즈니스 로직
│   │   │   │   │   ├── 📜 MemberService.java  
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

### 개발할 예제의 클래스 의존관계
<img width="559" alt="Image" src="https://github.com/user-attachments/assets/859aafe1-0939-4c35-8cc8-8d8de3511fb8" /> <br /> 
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

<br />

---

### 테스트 코드 작성
[JUnit5를 이용한 테스트 케이스](createTestCase.md)

<br />
<br />

회원 관리 예제를 개발하면서 인터페이스를 사용하였는데, <br />
'개발 시 자주 사용하는 인터페이스, 추상클래스에 대해서 자세하게 정리를 한 번 해보자'라는 생각이 들어 확실하게 정리하게 되었습니다. <br />
[추상클래스 정리](abstractMethod.md) <br />
[인터페이스 정리](interface.md)

<br />
<br />

### 빈 등록하기 (Bean)
[1. 컴포넌트 스캔과 자동 의존관계 설정](Bean1.md) <br />
[2. 자바 코드로 직접 스프링 빈 등록하기](자바_코드로_직접_빈_등록.md)

<br />
<br />

### 의존성 관리(DI)
[의존성 관리](dependencyInjection.md)
