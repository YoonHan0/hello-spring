## 스프링 데이터 JPA
- 스프링 데이터 JPA를 사용하면 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있게 됩니다.
- 추가로 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공합니다.

<br />

### 1. `SpringDataJpaMemberRepository` 인터페이스 생성
```java
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
```
스프링 데이터 JPA는 구현 클래스 없이 인터페이스 만으로 기본 CRUD 기능을 사용할 수 있습니다. <br />

`SpringDataJpaMemberRepository`가 상속받는 `JpaRepository` 인터페이스를 확인해보면

`JpaRepository > ListCrudRepository > CrudRepository` 인터페이스의 코드를 보면 우리가 구현했었던 기본 CRUD 메서드들이 모두 구현되어 있습니다.
```java
@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);

    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);
    
    ...
    
    // 정의되어 있는 메서드들이 추가로 있음
}
```
이처럼 기본 CRUD 기능들을 미리 구현해 놓음으로써 라이브러리를 사용하는 개발자들이 구현해야 되는 코드가 줄어들게 되고,
개발 생성상이 증가하게 됩니다.

<br />

### 2. 스프링 설정 파일 변경 (`SpringConfig`)
```java
    @Configuration     
    public class SpringConfig {

        private final MemberRepository memberRepository;
    
        @Autowired
        public SpringConfig(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    
        @Bean
        public MemberService memberService() {
            return new MemberService(memberRepository); // memberRepository() → memberRepository로 변경
        }
    }
```
스프링 데이터 JPA가 `SpringDataJpaMemberRepository`를 스프링 빈으로 자동 등록합니다. <br />
→ `SpringDataJpaMemberRepository` 인터페이스가 `JpaRepository`를 상속받는데 이 `JpaRepository`를 상속받게 되면 Spring이 해당 레포를 빈으로 등록해주게 됩니다.

<br />

### 3. 스프링 데이터 JPA가 제공해주는 기능
- 인터페이스를 통한 기본적인 CRUD
- `findByName()`, `findByEmail()`과 같이 메서드 이름 만으로 조회 기능 제공

    ```java
    // 메서드명 규칙으로 처리를 해준다.
    // SELECT m FROM Member m WHERE m.name = ?
    // find → SELECT
    // name → WHERE절의 조건
    @Override
    Optional<Member> findByName(String name);

    // SELECT m FROM Member m WHERE m.name = ? AND m.id = ?
    // find → SELECT
    // Name And Id → WHERE절의 조건
    @Override
    Optional<Member> findByNameAndId(String name, Long id); 
    ```
- 추가적으로 여러 기능 제공

<br />

> 💡 참고 <br />
> 실무에서는 JPA와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적 쿼리는 `Querydsl`라이브러리를 사용하면 된다고 합니다.