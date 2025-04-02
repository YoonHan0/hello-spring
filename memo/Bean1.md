# 🥜 스프링 빈을 등록하는 2가지 방법

1. **컴포넌트 스캔과 자동 의존관계 설정**
2. [자바 코드로 직접 스프링 빈 등록하기](자바_코드로_직접_빈_등록.md)

<br />


## 1. 컴포넌트 스캔과 자동 의존관계 설정
- `@Component` 어노테이션이 있으면 스프링 빈으로 자동 등록된다.
- `@Controller` 어노테이션이 붙은 컨트롤러가 스프링 빈으로 자동 등록되는 이유도 컴포넌트 스캔 때문이다.


- `@Component`를 포함하는 아래 어노테이션들은 스프링 빈으로 자동 등록된다.
  - `@Controller`
  - `@Service`
  - `@Repository`

<br />

`@Controller`에 들어가서 코드를 확인해보면 아래처럼 `@Component`가 포함된 것을 확인할 수 있다.
```java
package org.springframework.stereotype;

import ...

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```
<br />

#### 그럼 `@Compoent`가 붙은 어떠한 클래스라도 모두 빈으로 만들어주는걸까? -> ❌
해당 프로젝트를 기준으로는 `HelloSpringApplication.java`가 실행이 되는데 실행되는 코드를 보면 <br />
`@SpringBootApplication` 어노테이션이 존재한다.
```java
package hello.hello_spring;

import ...

@SpringBootApplication                  // 해당 어노테이션을 확인해보면
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);

		System.out.println("Hello World!");

	}

}
```
<br />

`@SpringBootApplication` 어노테이션 내부를 살표보면 아래와 같은데 아래 코드의 `@ComponentScan`이 있으면
Spring이 실행될 때 해당 클래스 하위에 존재하는 클래스들을 확인하면서 `@Component` 어노테이션이 있는 클래스들을 Spring이 빈으로 등록하게 된다. 

```java
...
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
...
```

<br />
<br />

### 어노테이션으로 빈 등록해보기

우선 전체적인 Spring 프로젝트는 이렇게 구성되어 있습니다. <br />
<img width="649" alt="Image" src="https://github.com/user-attachments/assets/444e4c7d-8eeb-4d3a-813c-474554b27783" />


<br />

**이제 Controller -> Serivce -> Repository 순으로 클래스들을 빈으로 등록해보겠습니다.**

<br />

**MemberController**
```java
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // 의존성 주입 : DI, 연결하는 로직 (Spring 4.3 이상에서는 클래스에 생성자가 단 하나만 존재하면, @Autowired를 생략해도 자동으로 주입됩니다.)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
```

**MemberSerivce**
```java
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired   // Spring 4.3 이상에서는 클래스에 생성자가 단 하나만 존재하면, @Autowired를 생략해도 자동으로 주입됩니다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    ...
}
```

**MemoryMemberRepository**
```java
/* MemberRepository 구현하는 클래스 */
@Repository
public class MemoryMemberRepository implements MemberRepository{
    ...
}

```

이렇게 `@Controller`, `@Serivce`, `@Repository`와 같은 어노테이션으로 작성하면 <br />
스프링이 실행될 때 스프링 컨테이너가 어노테이션이 붙은 클래스들을 빈으로 등록해 관리해줍니다.

<br />

> 💡@Autowired란? <br />
> 의존성 주입을 할 때 사용하는 어노테이션으로 의존 객체의 타입에 해당하는 빈을 찾아 주입하는 역할을 한다.

<br />
<br />
<br />

### 🚨 오류 확인
어노테이션을 붙이고 빌드를 진행할 때 아래 이미지처럼 빌드 실패가 될 수도 있는데 <br />
![Image](https://github.com/user-attachments/assets/ae0a2377-25bb-468a-88df-b689bae1f929)

각각의 클래스에 올바르게 어노테이션을 붙였는지 확인을 해봐야 한다.

<br />

저의 경우에는 `MemberService`에 어노테이션을 붙이지 않아 스프링이 해당 클래스를 찾지 못해서 빌드 실패를 했었습니다. <br />
<img width="643" alt="Image" src="https://github.com/user-attachments/assets/360ba1aa-a09d-492d-a90b-3aae0ddbb25d" />

<br />

올바르게 어노테이션을 작성하여 모두 빈으로 등록이 완료되었다면 <br />
아래처럼 구성되게 됩니다. <br />
<img width="656" alt="Image" src="https://github.com/user-attachments/assets/6421aec5-9ee3-4006-b255-61a86b7fa9d9" />
