## 🚀 발생했던 오류 정리

JDBC Template을 사용하면서 다시 h2 데이터베이스를 사용해서 테스트 해보았는데 몇몇 에러가 발생하여 기록하려고 합니다.

<br />

### 1. org.h2.jdbc.JdbcSQLInvalidAuthorizationSpecException: Wrong user name or password [28000-232]

- 오류 상황
    
    `./h2.sh` (DB 실행) > 프로젝트 실행 > DB 연결을 하는 시점에서 오류 발생

- 해결 방안

    - H2 데이터 베이스가 켜져 있는지 확인을 합니다.
    - `application.properties` 파일에서 아래 구문을 추가 ( sa: 초기 DB 접속 시 사용자명 )
      
        ```
      spring.datasource.username=sa
      ```
      
<br />
<br />

### 2. org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "MEMBER" not found (this database is empty); SQL statement:

말 그대로 연결된 DB에 `MEMBER` 라는 테이블이 없다고 하는건데

- 오류 상황

  `./h2.sh` (DB 실행) > 프로젝트 실행 > DB 연결 후 호출 시에 발생

- 해결 방안

    - [블로그 해결 방안](https://c-omealong.tistory.com/43)
    

저는 위 해결 방안으로 해결이 되질 않아 다른 방법을 찾아보았습니다.

먼저 `application.properties` 파일에 아래 코드를 추가하고
```
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

`http://localhost:8080/h2-console` 에 접속합니다. ( 8080: SpringBoot를 실행한 Tomcat 포트 )

그럼 이렇게 생성했던 `MEMBER` 테이블이 안 보이는 경우가 있습니다. <br />
<img width="401" alt="Image" src="https://github.com/user-attachments/assets/d2f5d7a4-2e9b-44bd-8979-a234c6aef7ee" />

이러면 'DB 연결이 잘못된건가?'라는 생각이 들게 됩니다. <br />
h2를 실행했을 때 띄워지는 DB가 아닌 인메모리에 연결되어 있을 수도 있다는 겁니다.

<br />

해결방안은 
1. DB를 연결할 때 입력하는 `JDBC URL`을 먼저 확인해 보고, 다르다면 해당 부분을 수정해 주면 됩니다. 
2. 경로가 동일하다면 `./h2.sh` 명령어로 데이터베이스를 **먼저** 실행하고 프로젝트를 실행하면 됩니다.
