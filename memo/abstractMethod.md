# 추상 클래스란?
## 1. 추상 클래스와 추상화
일반 클래스는 구체적인 데이터를 담아 인스턴스화할 수 있지만, **추상 클래스**는 추상적인 데이터를 담고 있어 인스턴스화가 불가능하다.
이를 위해 `abstract` 키워드를 사용한다. 그러나 중요한 것은 문법적인 특징이 아니라 **추상 클래스의 본질적인 개념과 사용 목적**이다.

### 예시: `String.toUpperCase()`
```java
String text = "Hello world";
String textUpper = text.toUpperCase();
// 우리는 메서드 내부가 어떤식으로 돌아가는지 생각 없이 결과값만 받을 뿐이다. 
// 그냥 메서드 내부에서 대충 알아서 잘 대문자로 마술같이 변환해주겠지 하고 막연하게 추상적으로 생각할 뿐이다.

System.out.println(textUpper); // "HELLO WORLD"
```
위 코드에서 우리는 `toUpperCase()`의 내부 로직을 몰라도 결과만 받아서 사용한다.
즉, 내부 동작을 추상적으로 받아들이고 활용하는 것이다. 이러한 추상화 개념을 클래스에 적용한 것이 추상 클래스이다.

## 2. 추상 클래스의 역할

- 클래스에 추상화를 접목하여 구조적으로 객체를 설계할 수 있다.
- 프로그램의 유지보수성을 향상시키고, 기능 수정/추가에 유연성을 제공한다.
- 주로 범용 라이브러리나 프레임워크 시스템 설계에 활용된다.

추상 클래스를 사용하면 보다 유연하고 확장성이 뛰어난 객체 지향적 프로그램을 만들 수 있다.

## 3. 추상 클래스 & 추상 메서드

### 추상 클래스와 추상 메서드의 정의
- `abstract` 키워드를 사용하여 **추상 클래스**와 **추상 메서드**를 선언한다.
- **추상 메서드**는 구현부 없이 선언부만 존재하는 메서드로, 이를 포함한 클래스가 **추상 클래스**가 된다.

### 추상 메서드의 역할
- 부모(추상) 클래스에서 **메서드의 선언부만 정의**하고, 실제 구현은 **자식 클래스가 담당**한다.
- 상속받는 클래스의 역할에 따라 메서드 내용이 달라지므로, **각각의 클래스에서 적절히 구현하도록 강제**하는 개념이다.

### 예제 코드
```java
// 추상 클래스 정의
// hello.hello_spring.domain.Pet.java
abstract class Pet {
    abstract public void walk(); // 추상 메서드
    abstract public void eat();  // 추상 메서드

    public int age;     // 인스턴스 필드
    public void run() { // 일반 메서드
        System.out.println("run run");
    }
}

// 추상 클래스를 상속받아 구현
// hello.hello_spring.domain.Dog.java
public class Dog extends Pet {
    public void walk() { System.out.println("Dog walk!"); }
    public void eat() { System.out.println("Dog eat!"); }
}

// 실행 코드
// hello.hello_spring.HelloSpringApplication.java
public static void main(String[] args) {
    Dog d = new Dog();
    d.eat();  // 추상 메서드 구현체 실행
    d.walk();
    d.run();  // 부모 클래스의 일반 메서드 실행
}
```

## 4. 추상 클래스의 생성자
추상 클래스는 직접 인스턴스화할 수 없지만, 생성자를 가질 수 있다. 
이는 상속받는 자식 클래스에서 `super()`를 통해 부모 클래스의 생성자를 호출하여 초기화 작업을 수행할 수 있게 한다.

```java
abstract class Pet {

    // 추상 클래스 생성자 (추가)
    public Pet(int age) {
        this.age = age;
    }
    abstract public void walk(); // 추상 메소드
    abstract public void eat(); // 추상 메소드

    public int age;      // 인스턴스 필드
    public void run() {  // 인스턴스 메소드
        System.out.println("run run");
    }
}

public class Dog extends Pet {
    
    // 자식 클래스의 생성자(추가)
    public Dog(int age, String name) {
        super(age);         // 부모 추상 클래스 생성자 호출하면서 값을 넣어줌
        this.name = name;
    }
    // 상속 받은 부모(추상) 메소드를 직접 구현
    public void walk() {
        System.out.println("Dog walk!");
    }
    public void eat() {
        System.out.println("Dog eat!");
    }

    public String name;
}

public static void main(String[] args) {
    // Pet p = new Pet(28);  // ERROR !! - 추상 클래스는 인스턴스를 직접 생성할 수 없음.
    Dog d = new Dog(28, "BaekSam");
    d.eat();	//  부모(추상) 클래스로 부터 상속받은 추상 메소드를 직접 구현한 메소드를 실행
    d.walk();
    d.run();	// 부모(추상) 클래스의 인스턴스 메소드 실행
    System.out.println("age: " + d.age + " name: " + d.name); /* 출력: age: 28 name: BaekSam */
}
```

## 5. 왜 추상 클래스로 설계하는걸까?
이렇게 예시를 작성하면서 드는 생각은 추상 클래스가 아닌 일반 클래스로 개발하여도 동일하게 구현할 수 있는데
왜 굳이..? 라는 생각이 들었다.

관련해서 내용을 찾아보니

**"구현의 강제성을 통한 기능 보장"** 이라는 내용을 알게 되었다!


## 6. 구현의 강제성을 통한 기능 보장

### 🚨 일반 클래스를 사용했을 때의 문제점
```java
class Pet {
    int age;

    public Pet(int age) {
        this.age = age;
    }
    
    public void run() {
        System.out.println("run run!!");
    }

    // 일반 메서드 (하지만 기본 동작이 없음!)
    public void walk() {
        System.out.println("어떤 동물이 걷는지 정해져있지 않습니다.");
    }
}

class Dog extends Pet {
    public Dog() {
        super("Dog");
    }

    @Override
    public void walk() {
        System.out.println("Dog가 걷고 있습니다!");
    }
}

class Cat extends Pet {
    public Cat() {
        super("Cat");
    }

    @Override
    public void walk() {
        System.out.println("Cat가 걷고 있습니다!");
    }
}
```

#### 🚨 문제점
- `Pet`클래스는 `walk()` 메서드를 가지고 있지만, **구체적인 동작이 정의되지 않음.**
- 즉, `Pet`클래스를 그래도 사용하거나 `walk()`를 구현하지 않은 자식 클래스가 있다면 **비어 있는 walk()를 호출하는 문제가 발생할 수 있음.**

<br />


### ✅ 추상 클래스를 사용할 경우
```java
abstract class Pet {
    int age;

    public Pet(int age) {
        this.age = age;
    }
    
    public void run() {
        System.out.println("run run!!");
    }
    // 🚨 추상 메서드: 반드시 구현하도록 강제!
    abstract public void walk();
}
```
#### ✅ 장점
- 강제성: `Pet`을 상속받는 모든 클래스는 `walk()`메서드를 반드시 구현해야 함.
- 불완전한 코드 방지: `Pet`의 인스턴스를 직접 만들 수 없으므로, `walk()`가 없는 객체가 생성되지 않음.
- 설계 명확성: `Pet` 클래스는 "walk() 라는 기능이 반드시 필요하다"는 **계약(Contract)** 을 명확하게 보장할 수 있음.

<br />

#### 📌 정리하면
- 만약 **상속받는 모든 클래스가 반드시 구현해야 하는 메서드**가 있다면? → `추상 클래스 사용이 적절함`
- 만약 **기본 동작이 있어서 그대로 사용해도 되는 메서드**라면? → `일반 클래스 사용 가능`
- **추상 클래스는 "미완성" 상태의 개념을 표현**하며, 자식 클래스가 반드시 구현해야 할 기능을 강제하기 위해 사용됨 <br />

즉, 추상 클래스는 단순히 상속을 위한 것이 아니라, **"이 메서드는 반드시 구현해야 한다"** 는 계약을 강제하는 역할을 한다!



<br />
<br />
<br />

> **참고문헌** <br />
> [추상클래스-용도-완변-이해하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%B6%94%EC%83%81-%ED%81%B4%EB%9E%98%EC%8A%A4Abstract-%EC%9A%A9%EB%8F%84-%EC%99%84%EB%B2%BD-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0)