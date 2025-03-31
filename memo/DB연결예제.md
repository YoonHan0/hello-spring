## H2 데이터베이스 설치
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공 <br />
- [다운로드](https://www.h2database.com) <br />
- 다운로드 및 설치 <br />
- h2 데이터베이스 버전은 스프링 부트 버전에 맞춘다. <br />
- 권한 주기: `chmod 755 h2.sh` <br />
- 실행: `./h2.sh` <br />
- 데이터베이스 파일 생성 방법 <br />
    - `jdbc:h2:~/test` (최초 한번) <br />
    - `'~/test.mv.db` 파일 생성 확인 <br />
    - 이후부터는 `jdbc:h2: tcp://localhost/~/test` 이렇게 접속1
