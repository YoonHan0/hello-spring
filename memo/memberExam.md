## 회원 관리 예제 - 백엔드 개발

<br />

#### 일반적인 웹 애플리케이션 구조
<img width="563" alt="Image" src="https://github.com/user-attachments/assets/a2637fdb-8fb1-453b-837b-5e865056aed1" /> <br />
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

- 컨트롤러: 웹 MVC의 컨트롤러 역할
- 서비스: 핵심 비즈니스 로직 구현
- 리포지토리: 데이터베이스 접근, 도메인 객체를 DB에 저장하고 관리
- 도메인: 비즈니스 도메인 객체, ex) 회원, 주문, 쿠폰 등 주로 데이터베이스에 저장하고 관리됨

<br />

#### 클래스 의존관계
<img width="559" alt="Image" src="https://github.com/user-attachments/assets/859aafe1-0939-4c35-8cc8-8d8de3511fb8" /> <br /> 
<small>_이미지 출처: 인프런 강의(스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술)_</small>

