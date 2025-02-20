# Spring Project 시작
- Gradle
- Spring Boot 3.4.2
- Java 17

### Build
시작부터 쉽지 않다

1. [Spring Project 생성](https://start.spring.io)<br />
접속 후 Spring 프로젝트 생성 <br /> intelliJ로 생성한 프로젝트 open

2. 아래와 같은 오류 발생 <br /> Spring 생성 시에 Java 버전을 17로 하였는데 실제로 컴퓨터에 다운되어 있는 JDK 버전과 맞지 않아서 발생한 오류인듯 <br /> JDK1.8 버전도 사용하여야 하므로 1.8, 17 version을 모두 사용할 수 있는 방법으로 해결
![Image](https://github.com/user-attachments/assets/b8bf8f36-5314-46fc-b1c9-a705db00a2d7)
3. brew를 이용해서 jdk@17 다운
4. [블로그](https://dev-emmababy.tistory.com/139) 참고해서 intelliJ가 바라보는 JDK를 수정
( finder에서 알아두면 좋을 단축키: cmd+shfit+g )
5. 성공!<br/>
   setting 수정 후 표시된 새로고침 버튼을 클릭하면 됨
   ![Image](https://github.com/user-attachments/assets/555b9173-5150-44d4-b54c-3f3b7485a53d) 
6. 이렇게 수정해도 기존 Java version은 여전히 1.8로 유지되는듯
   ![Image](https://github.com/user-attachments/assets/174ee60c-ccd7-463f-8f3b-1d58813514d5)
7. 세팅이 끝났다면 프로젝트가 올바르게 동작하는지 확인해봐야지 <br />
   역시 시작은 Hello World~!
![Image](https://github.com/user-attachments/assets/a544a6f1-c18b-4205-b545-814b6ce068a9)
8. 그런데 왼쪽 하단에 보면 이렇게 HelloSpringApplication.main()이 실행되고, <br />
   결과는 출력되지만 프로그레스바가 무한 로딩이 된다,,?
   ![Image](https://github.com/user-attachments/assets/1f85e189-d1bd-44cc-97a7-c90e9ccc1511)
9. 이것도 역시 구글링
   [링크](https://velog.io/@dghff/IntelliJ-IDEA-%EC%8B%A4%ED%96%89%EC%8B%9C-%EB%AC%B4%ED%95%9C%EB%A1%9C%EB%94%A9%EB%90%98%EB%8A%94-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0)
   참고하면 해결할 수 있다.
   ![Image](https://github.com/user-attachments/assets/31d1d8a3-d247-4872-b581-612335a54568)

---

<br />
<br />

### 프로젝트 구조
```
hello-spring
      | -- src
            | -- main
                   | -- java
                   | -- resources
      
``` 
> ✅ resources
> 
> 코드를 제외한 설정들이 들어감
> 
> - HTML 코드도 포함