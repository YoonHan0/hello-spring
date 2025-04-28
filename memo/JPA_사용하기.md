## JPA

- JPA는 기존의 반복적인 코드는 물론이고, 기본적인 SQL도 직접 만들어서 실행시켜 준다.
- JPA를 사용하면 SQL과 데이터 중심의 설계에서 **객체 중심의 설계**로 패러다임을 전환할 수 있다.

    ```java
    /* JDBC Template 코드 */
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER", memberRowMapper());
    }
  
    /* -------------------- */
  
    /* JPA 코드 */
    // from절을 보면 테이블이 아닌 Entity 객체를 대상으로 사용
    @Override
    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
    ```
- 개발 생산성을 크게 높일 수 있다.

<br />

### 1. `build.gradle` 파일에 JPA 라이브러리 추가
```
dependencies {
    ...
    // implementation 'org.springframework.boot:spring-boot-starter-jdbc'   주석 처리 
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
```
`spring-boot-starter-data-jpa`는 내부에 jdbc 관련 라이브러리를 포함하기 때문에 jdbc는 제거해도 됩니다.

<br />

### 2. `application.properties` 파일에 JPA 설정 추가
```
...
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
```
- `show-sql`: JPA가 생생하는 SQL을 출력합니다.
- `ddl-auto`: JPA는 테이블을 자동으로 생성하는 기능을 제공하는데 `none`을 사용하여 해당 기능을 사용하지 않습니다.
  - `create`를 사용하면 엔티티 정보를 바탕으로 테이블도 직접 생성해줍니다.

<br />

### 3. JPA 엔티티 맵핑

- Member 클래스
    
    ```java
    @Entity
    public class Member {
    
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)     // IDENTITY 전략: 디비에 값을 넣을 때 자동으로 채번을 해주는 (ex/auto Increament) 것을 말한다.
        private Long id;
    
        // @Column(name = "userName") // 컬럼명이 userName이라면 이렇게 맵핑할 수 있음
        private String name;
    
        public Long getId() {
            return id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
    ```
  
- JpaMemberRepository 클래스
  - JPA는 `EntityManager`로 모두 동작을 함
  - gradle 파일에 jpa dependency 를 추가하게 되면 Spring이 현재 DB와 연결해서 `EntityManager`를 생성해줌
  - 개발자는 생성된 `EntityManager`를 injection 받기만 하면 됨
  ```java
    public class JpaMemberRepository implements MemberRepository {

        private final EntityManager em;
        /*
        * JPA는 EntityManager로 모두 동작을 함
        * gradle 파일에 jpa dependency 를 추가하게 되면 Spring이 현재 DB와 연결해서 EntityManager를 생성해줌
        * 우리는 생성된 EntityManager를 injection 받기만 하면 된다~!
        * */

        public JpaMemberRepository(EntityManager em) {
            this.em = em;
        }

        @Override
        public Member save(Member member) {
            em.persist(member);
            return member;
        }

        @Override
        public Optional<Member> findById(Long id) {
            Member member = em.find(Member.class, id);
            return Optional.ofNullable(member);
        }

        @Override
        public Optional<Member> findByName(String name) {
            List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", name)
                    .getResultList();

            return result.stream().findAny();
        }

        @Override
        public List<Member> findAll() {
            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();       // 테이블이 아닌 Entity 객체를 대상으로 사용
            return result;
        }
    }
    ```

<br />

### 4. 서비스 계층에 트랜잭션 추가
```java
@Transactional
public class MemberService {
    ...
}
```
- `org.springframework.transaction.annotation.Transactional` 를 사용하자
- **JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행하여야 한다.**

<br />

### 5. 스프링 설정 파일 변경 (`SpringConfig`)

```java
@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) { this.dataSource = dataSource; }
    
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    
    ...
    
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    
    /* Spring이 실행되면 MemberService, MemberRepository를 bean으로 등록하고, 위에 작성된 것처럼 MemberService에 memberRepository를 주입해준다. */
}
```