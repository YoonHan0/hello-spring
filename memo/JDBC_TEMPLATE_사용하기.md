## 🧑‍💻 JDBC Template 사용하기

<br />

> 💡 이전 단계에서 아래 작업을 마치고 이어서 작업하셔야 합니다.
> - H2 데이터베이스 생성
> - member 테이블 생성

<br />

먼저 `repository` 패키지 아래에 `JdbcTemplateMemberRepository` 클래스를 생성 및 구현
```java
/* MemberRepository 인터페이스 구현하는 클래스 */


public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    // 생성자가 하나이기 때문에 @Autowired 생량
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));

        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        /*
        1. 문자열 형태로 되어 있는 쿼리를 실행
        2. memberRowMapper()로 값을 받음
        3. 결과를 result로 return
        */
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        /* 람다식으로 변환 전 코드 */
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return member;
//            }
//        };
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
```
<br />
<br />

bean 설정 파일인 `SpringConfig` 수정
```java
@Configuration      // @Configuration 을 달면 Spring이 실행할 때 해당 클래스를 읽게 됨.
public class SpringConfig {

    private DataSource dataSource;      // DB 연결을 위해

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() { 
        // ...기존 코드 유지
    }

    @Bean
    public MemberRepository memberRepository() {
//   return new MemoryMemberRepository();에서 DI 해주는 클래스만 아래처럼 변경
     return new JdbcTemplateMemberRepository(dataSource);
    }
}
```

<br />
<br />

이처럼 객체 지향적 설계 원칙을 활용해 인터페이스 기반으로 개발하면, 데이터베이스 접근 방식(store, JDBC Template, MyBatis, JPA...)이 확정되지 않은 초기 단계에서도 유연하게 개발을 진행할 수 있습니다.

인터페이스를 기준으로 비즈니스 로직을 구성하고, 실제 구현은 각각의 DB 접근 기술에 따라 분리하면, 구현체만 교체하는 방식으로 손쉽게 전환할 수 있고

구조화해두면 DB 접근 방식이 바뀌더라도 다른 코드에는 영향을 주지 않고 구현체만 간단히 수정하면 되기 때문에 유지보수성과 확장성이 크게 향상됩니다.

<br />

코드 추가 후 테스트 시 [발생했던 오류들](/memo/DB_사용시_오류.md)을 정리하였습니다.
