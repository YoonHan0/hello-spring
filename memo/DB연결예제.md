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
