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