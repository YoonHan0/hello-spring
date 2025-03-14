회사 업무를 하면서 메서드 내부에서 예외처리를 하지 않은 부분들이 몇몇 있어서 `try...catch` 처리를 하던 중 <br />
어디서 에러가 먹히?는지 하는 궁금증이 생겨 찾아보다 결국 여기까지 왔다..

## Checked Exception / Unchecked Exception

자바에서 예외(Exception)는 **컴파일 에러** / **런타임 에러**로 구분이 된다. <br />
그리고 또 다시 예외 종류로써 `Checked Exception` / `Unchecked Exception` 으로 나뉜다.

복잡할 수 있지만
단순히 **Checked Exception은 컴파일 예외클래스들**을 가리키는 것이고, <br /> 
**Unchecked Exception은 런타임 예외클래스들**을 가리키는 것으로 보면 된다.

이렇게 재분류를 한 이유는 **코드적 관점에서 예외 처리 동작을 필수 지정 유무에 따라 나뉘기 때문**이라고 한다.


![Image](https://github.com/user-attachments/assets/cd7cfab0-aeb8-4289-8723-501c41e758e8) <br />
<small><a href="https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%90%EB%9F%ACError-%EC%99%80-%EC%98%88%EC%99%B8-%ED%81%B4%EB%9E%98%EC%8A%A4Exception-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC#checked_exception_/_unchecked_exception">이미지출처</a></small>

|        | Checked Exception                                                                                                                          | Unchecked Exception                                       |
|:-------|:-------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------|
| 처리 여부  | 반드시 예외를 처리해야 함                                                                                                                             | 명시적인 처리를 하지 않아도 됨                                         |
| 확인 시점  | 컴파일 단계                                                                                                                                     | 런타임 단계                                                    |
| 예외 종류  | RuntimeException을 제외한, <br /> Exception 클래스와 그를 상속받는 하위 예외 <br /><br /> - IOException <br /> - FileNotFoundException <br /> - SQLException | RuntimeException과 그 하위 예외 <br /><br /> - NullPointerException <br /> - IllegalArgumentException <br /> - IndexOutOfBoundException <br /> - SystemException

<br />
<br />

### 🚨 두 예외의 차이점
Checked / Unchecked Exception 의 가장 핵심적인 차이는 **"반드시 예외 처리를 해야 하는가?"** 이다.

<br />

#### Checked Exception

Checked Exception은 확인 시점이 컴파일 단계이기 때문에 **별도의 예외 처리를 하지 않는다면 컴파일 자체가 되지 않는다.**

![Image](https://github.com/user-attachments/assets/030bae51-dd5c-423b-b45a-dce632279858)

<br />

따라서 Checked Exception이 발생할 가능성이 있는 메서드라면 반드시 로직을 `try - catch`로 감싸거나 <br /> 
`throws`로 예외를 던져서 처리해야 한다.
```java
public static void main(String[] args) {
    // try - catch로 예외 처리
    // 파일을 열고 쓰고 닫는 아주 단순한 로직이어도 이에 대한 예외는 checked exception으로 분류 되기 때문에 반드시 try - catch로 감싸주어야 한다.
    try {
        System.out.println("Hello World!");
        FileWriter file = new FileWriter("data.txt");
        file.write("Hello World!");
        file.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```
```java
public static void main(String[] args) throws Exception {
    // throws로 예외 처리
    System.out.println("Hello World!");
    FileWriter file = new FileWriter("data.txt");
    file.write("Hello World!");
    file.close();
}
```

<br />
<br />

#### Unchecked Exception

반면에 Unchecked Exception의 경우에는 **명시적인 예외 처리를 하지 않아도 된다.**

Unchecked Exception도 예외이긴 하지만, 개발자의 충분한 주의로 미리 회피할 수 있는 경우가 대부분이라  <br />
그나마 상대적으로 미약한 예외로 처리되어 자바 컴파일러는 별도의 예외 처리를 하지 않도록 설계 되어 있기 때문이다. <br />

따라서 에러를 일부러 일으키는 코드가 있더라도 `try - catch` 처리하지 않더라도 컴파일도 되고 실행까지 가능하다

![Image](https://github.com/user-attachments/assets/38b3a53d-8908-4a8e-8a97-38b15d6d3a58)

주의 표시가 뜨긴 하지만 실행은 할 수 있다.

<br />
<br />

### Checked를 Unchecked 예외로 변환하기


checked Exception은 반드시 `try - catch`문으로 감싸야 된다.
![Image](https://github.com/user-attachments/assets/030bae51-dd5c-423b-b45a-dce632279858)

이런식으로 설계한 이유는, 처음 자바 언어를 개발 했을때 프로그래밍 경험이 적은 사람도 보다 견고한 프로그램을 작성할 수 있도록 유도하기 위해서인데, <br />
실제로 별것 아닌 예외도 Checked exception 으로 등록한 것이 꽤 많다.

따라서 연결된 예외(chained exception)을 이용해, checked 예외를 unchecked 예외로 바꾸면 예외처리가 선택적이 되므로 억지로 거추장 스러운 예외처리를 하지 않아도 된다.

```java
public static void main(String[] args) {

    System.out.println("Hello World!");
    install();
}

public static void install() {
    throw new RuntimeException(new IOException("설치할 공간이 부족합니다."));
}
```
![Image](https://github.com/user-attachments/assets/691ecdb1-bb24-4a7a-bf2d-0f5cf06aa622)