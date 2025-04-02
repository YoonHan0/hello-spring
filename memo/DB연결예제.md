## 🧑‍💻 H2 데이터베이스 설치
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공 <br />
[다운로드](https://www.h2database.com) <br />
- 다운로드 및 설치
- h2 데이터베이스 버전은 스프링 부트 버전에 맞춘다.
- 다운로드한 h2 폴더 내부에 bin 폴더에 `h2.sh` 파일이 존재 (h2/bin/)
- 권한 주기: `chmod 755 h2.sh`
- 실행: `./h2.sh`
- 데이터베이스 파일 생성 방법
    - `jdbc:h2:~/test` (최초 한번)
    - `'~/test.mv.db` 파일 생성 확인
    - 이후부터는 `jdbc:h2: tcp://localhost/~/test` 이렇게 접속


## ✅ H2 데이터베이스 사용하기

### build.gradle 파일에 jdbc, h2 데이터베이스 관련 라이브러리 추가
```xml
dependencies {
    ...
    implementation 'org.springframework.boot:spring-boot-starter-jdbc' /* JAVA에서 DB랑 연결을 하기 위해서는 반드시 JDBC가 필요하다. */ 
    runtimeOnly 'com.h2database:h2'     /* DB에서 필요한 클라이언트를 h2를 사용한다. */
}
```

### 스프링 부트 데이터베이스 연결 설정 추가
```xml
  <!-- resources/application.properties */ -->
  spring.datasource.url=jdbc:h2:tcp://localhost/~/test
  spring. datasource.driver-class-name=org.h2.Driver
```
DB 접속 정보를 Spring Boot에게 제공해서 Spring이 접속에 필요한 처리를 해주도록 하는 로직


<img width="666" alt="Image" src="https://github.com/user-attachments/assets/28c1c592-f40d-4085-b0e8-2c8fb7a9e5a8" />

<img width="661" alt="Image" src="https://github.com/user-attachments/assets/23c80c37-acc0-4112-b023-a20e4cfed240" />

<img width="641" alt="Image" src="https://github.com/user-attachments/assets/8fa2a9ef-f41e-4971-ab54-ab46980da93a" />