# 인터페이스란?
- 인터페이스를 작성하는 것은 추상 클래스를 작성하는 것과 동일하다. (추상 메서드 집합)
- 인터페이스도 필드를 선언할 수 있지만 변수가 아닌 상수(final)로서만 정의 가능하다.
- `public static final`과 `public abstract`제어자는 생략이 가능하다.
        
    인터페이스에 정의된 모든 멤버에 적용되는 사항이기 때문에 편의상 생략 가능하게 지원하는 것임. <br />
    생략된 제어자는 컴파일 시에 컴파일러가 자동으로 추가해 준다.

<br />

### 인터페이스 기본 문법

```java
interface 인터페이스명 {
    public static final 타입 상수이름 = 값;
    public abstract 타입 메서드이름(매개변수목록);
}

// ------------------------------------------------
interface TV {
    public static final int MAX_VOLUME = 10;    // public static final 생량 가능
    public static final int MIN_VOLUME = 10;
    
    public abstract void turnOn();      // public abstract 생략 가능
    public abstract void turnOff();
    void changeVolume(int volume);
    void changeChannel(int channel);
}
```

<br />

### 인터페이스 구현
인터페이스는 그 자체로 인스턴스를 생성할 수 없으며, `implements` 키워드를 사용하여 클래스를 통해 구현해야 한다.
인터페이스는 다중 상속이 가능하며, 클래스 상속(`extends`)과 인터페이스 구현(`implements`)을 동시에 할 수 있다.

```java
interface Animal {
    void cry();
}

interface Pet {
    void play();
}

class Tail {
    void wag() {
        System.out.println("꼬리를 흔든다~");
    }
}

class Cat extends Tail implements Animal, Pet {
    public void cry() {
        System.out.println("고양이 냐옹~");
    }
    public void play() {
      System.out.println("고양이 놀자~");
    }
}

public class Main {
  public static void main(String[] args) {
    Cat myCat = new Cat();
    myCat.cry();  // "냐옹냐옹!"
    myCat.play(); // "쥐 잡기 놀이하자~!"
    myCat.wag();  // "꼬리를 흔든다." (Tail 클래스에서 상속받은 기능)
  }
}
```
<br />

> 💡인터페이스도 따지고 보면 상속이지만, `extends` 키워드 대신 `implements` 라는 '구현'이라는 키워드를 사용하는 이유는,
> 상속은 클래스간의 부모-자식 관계를 연관 시키는데 의미가 중점 된다면, <br />
> 구현은 클래스를 확장시켜 다양히 이용하는데 중점이 되기 때문이다.

> 💡 인터페이스를 구현받고 추상 메서드를 구현할 때 **접근제어자 설정**에 주의해야 한다. <br />
> 기본적으로 오버라이딩(overriding) 할 때는 부모의 메서드보다 넒은 범위의 접근제어자를 지정해야 한다는 규칙이 존재한다. <br />
> 인터페이스의 추상 메서드는 기본적으로 `public abstract`가 생략된 상태이기 때문에 반드시 메서드 구현분에서는 제어자를 `public`로 설정해 주어야 한다.
 
 
🚨 클래스가 인터페이스를 구현할 때, 추상 메서드를 구체적으로 구현하지 않고 인터페이스의 메서드 중 일부만 구현한다면 `abstract`를 붙여 추상 클래스로 선언해야 한다.


<br />


### 인터페이스와 추상 클래스의 차이점


#### 인터페이스
- 다중 상속이 가능하다
- **인터페이스의 필드들은 모두** `public static final` 이기에, 서로 상속을 해도 **독립적으로 운용**
- 메서드는 기본적으로 추상 메서드이며, Java 8 이후부터는 `default` 메서드도 선언할 수 있다.

#### 추상 클래스
- 단일 상속만 가능
- 필드와 일반 메서드를 가질 수 있다.
- 추상 메서드를 포함할 수 있으며, 이를 상속받는 클래스에서 구현해야 한다.

<br />

### 인터페이스 활용 예제
디자인 패턴에서의 활용: 인터페이스는 클래스 간의 결합도를 낮추고, 유지보수성을 높이는 디자인 패턴에서 중요한 역할을 한다. 예를 들어, 여러 클래스가 동일한 메서드를 구현하도록 강제하여 일관성을 유지할 수 있다.


<br />
<br />
<br />


> **참고문헌** <br />
> [인터페이스-완벽-이해하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4Interface%EC%9D%98-%EC%A0%95%EC%84%9D-%ED%83%84%ED%83%84%ED%95%98%EA%B2%8C-%EA%B0%9C%EB%85%90-%EC%A0%95%EB%A6%AC#%EC%9E%90%EB%B0%948_%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4_%EA%B5%AC%ED%98%84_%EB%A9%94%EC%86%8C%EB%93%9C)