package hello.hello_spring.domain;


import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)     // IDENTITY 전략: 디비에 값을 넣을 때 자동으로 채번을 해주는 (ex/auto Increament) 것을 말한다.
    private Long id;

    // @Column(name = "userName") // 컬럼명 맵핑
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
