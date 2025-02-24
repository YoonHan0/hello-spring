
## 빌드하고 실행하기

### 빌드하기

- **jar 파일 생성 ( `./gradlew build`)**

    터미널에서 `./gradlew build` 명령어를 치게 되면 `jar 파일` 형태로 만들어준다. <br />
    또 에러가 발생했다.. 쉽지않네
  ![Image](https://github.com/user-attachments/assets/3707cee9-239e-4779-8ef1-207ef2d74478)

    <br /> 오류 내용을 보면 Build를 하려는데 JDK 버전이 맞지 않다고 하는 것 같아 JDK 버전을 수정하기로 했음.
    
    JDK 버전을 수정하는 방법은 여러 가지가 있는데
    1. 환경변수 수정
    2. 해당 프로젝트만 버전 수정 
    
    <br />
    나는 회사에서 **Java 8 버전** 을 사용하기 때문에 환경변수를 수정하게 되면 다른 프로젝트들에게도 영향을 줄 것 같아 2번 방법으로 수정하기로 했음.
  
    <br />
 
    [여기](https://velog.io/@newlysyl1107/Gradle-build-%EC%8B%9C-JVM-%EB%B2%84%EC%A0%84-%EC%88%98%EC%A0%95%ED%95%98%EA%B8%B0)를 참고해서 문제 해결👏
  ![Image](https://github.com/user-attachments/assets/f901c212-2657-4ef2-bd54-68c24671f4a9)



### 실행하기

빌드를 하게 되면 Spring 프로젝트의 `build/libs` 경로에 `.jar 파일`이 생기게 된다. <br />
그리고 `.jar`파일이 있는 경로에서 <br />
`java -jar hello-spring-0.0.1-SNAPSHOT.jar` 명령어를 치게 되면..

![Image](https://github.com/user-attachments/assets/75578147-bfd0-485e-9cc7-d7fd595e16bf)

이런 에러가 뜨게 된다...

에러 내용을 보면ㅠ
- 61.0 → JDK 17으로 컴파일됨 (즉, JAR 파일은 JDK 17에서 만들어짐)
- 52.0 → JDK 8에서 실행 중 (현재 실행 중인 Java 버전이 JDK 8임)

    즉, JDK 17에서 빌드된 JAR 파일을 JDK 8에서 실행하려고 해서 오류가 발생한 것이야! 🚨

라고 GPT가 알려주신다!

실행하는 자바 버전을 변경하는 방법을 찾아보니 가장 간단한 방법이 <br />
``` bash
# 원하는 Java 버전이 있는 경로/bin/java -jar Build를 통해 생성된 .jar 파일
/opt/homebrew/opt/openjdk@17/bin/java -jar hello-spring-0.0.1-SNAPSHOT.jar
```

원하는 버전의 Java가 저장되어 있는 경로를 입력하여 직접 실행하는 방법이었다.

이런 식으로 실행이 가능하게 된다.
![Image](https://github.com/user-attachments/assets/bc821fb9-3aff-4797-8b02-adbd21205f33)

`.jar`파일을 실행했으니 `localhost:8080`에 접속해서 제대로 실행됐는지 확인하면 성공~
![Image](https://github.com/user-attachments/assets/e9a38e69-d4f1-44f7-b43a-797bb2f9f7e0)

종료할 때는 `Ctrl + c`