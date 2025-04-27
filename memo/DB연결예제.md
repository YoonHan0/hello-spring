## 🧑‍💻 H2 데이터베이스 설치
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공 <br />
[다운로드](https://www.h2database.com) <br />
- 다운로드 및 설치
- h2 데이터베이스 버전은 스프링 부트 버전에 맞춘다.
- 다운로드한 h2 폴더 내부에 bin 폴더에 `h2.sh` 파일이 존재 (h2/bin/)
- 권한 주기: `chmod 755 h2.sh`
- 실행: `./h2.sh`

  실행 시 발생할 수 있는 오류
    ![Image](https://github.com/user-attachments/assets/f87c31f2-2eff-4fa0-bd21-43aec234e436)
  오류 내용을 보면 Java 버전이 잘못됐다는 오류인데 (`55 = Java 11, 52 = Java 8`) [참고](https://www.inflearn.com/community/questions/53693/jar-%EC%9D%84-%EC%8B%A4%ED%96%89%ED%95%A0%EB%95%8C-%EC%97%90%EB%9F%AC%EA%B0%80-%EB%B0%9C%EC%83%9D%ED%95%B4%EC%9A%94?srsltid=AfmBOorsVl6Y_udIqSIE1hRH1FOW3HsIF4WFTxonhdCp5_5tOFf6B1d_)
  ``` shell
  vi h2.sh    # shell 파일을 열어서
  
  # --- java -cp "$dir/h2-2.3.232.jar... 구문 상단에 작성해야 합니다. ---
  export JAVA_HOME=실행할 때 사용하고 싶은 JAVA 버전의 경로    # ex) /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home    
  export PATH=$JAVA_HOME/bin:$PATH
  # ---------------------------------------------------------------
  ```
  쉘 파일을 닫고 다시 실행 `./h2.sh`
- 데이터베이스 파일 생성 방법
    - JDBC URL: `jdbc:h2:~/test` (최초 한번)
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
