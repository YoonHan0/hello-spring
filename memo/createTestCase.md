## JUnit으로 테스트하는 방법

JUnit으로 테스트를 하기 전에 알아두어야 할 개념이 있다. 
- 테스트는 서로 의존관계가 없도록 설계되어야 한다.
- `@AfterEach`를 사용하면 `@Test`이 사용된 메서드가 끝날 때마다 실행되도록 할 수 있다.

<br />

JUnit으로 테스트를 할 때 사용할 수 있는 방법은 크게 2가지가 있는데
- 직접 생성
- 단축키 사용(클래스에서 command+shift+t)

<br />
<br />

### 1. 직접 생성하는 방법 ( 참고`MemoryMemberRepositoryTest.java` )

1. `test/java/hello/hello_spring` 아래에 package 생성
   - repository를 테스트 할거면 repository 패키지를 생성
   - service를 테스트 할거면 serivce 패키지를 생성

<br />

2. test 클래스 생성
    - 테스트하고자하는 `클래스명 + Test`, `MemberService.java -> MemberServiceTest.java`
    ```java
   class MemoryMemberRepositoryTest {
   ...
   }
    ```

<br />

3. 테스트할 메서드 생성

   `MemoryMemberRepository`클래스의 `save()` 메서드에 대한 테스트 케이스를 만든다면
   `@Test` 어노테이션을 붙이고 테스트 케이스를 작성해야 한다.

    ```java
   class MemoryMemberRepositoryTest {
    
   MemoryMemberRepository repository = new MemoryMemberRepository();
   
        @Test
        public void save() {

            Member member = new Member();
            member.setName("Spring");

            repository.save(member);    // 이게 DB에 데이터를 저장하는 동작처럼 구현한것 ( MemoryMemberRepository클래스의 save() 실행 )
            //      Optional.get()을 호출하기 전에 값이 존재하는지 확인하지 않으면서 warnning이 발생, isPresent()로 체크 후 get()을 호출하거나 아래 방법을 사용
            //      Member result = repository.findById(member.getId()).get();
   
            // findById() 메서드가 반환한 Optional 객체에서 값이 존재하지 않을 경우, NoSuchElementException 예외를 발생
            Member result = repository.findById(member.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다: " + member.getId()));

            // 검증단계 | member, result 객체가 동일한지 비교
            // Assertions.assertEquals(member, result);             // import org.junit.jupiter.api.Assertions;
            Assertions.assertThat(member).isEqualTo(result);        // import org.assertj.core.api.Assertions;  / static import를 하면 앞에 Assertions를 붙이지 않아도 사용 가능
        }
   ...
   }
   ```
   
<br />

4. 테스트 메서드 실행

    ![Image](https://github.com/user-attachments/assets/3ffceaa9-9c2e-49b6-896c-bf0e1caf2868)

<br />
   
5. 성공 여부 확인

   ![Image](https://github.com/user-attachments/assets/4016f796-fbd3-41ed-a83d-d77f1f0dcf0b)


<br />
<br />
<br />


### 2.단축키 사용하는 방법( 참고 `MemberServiceTest.java` )


1. 테스트 케이스를 만들고 싶은 클래스 내에서 `command+shift+t` -> `Create New Test...` 클릭
   ![Image](https://github.com/user-attachments/assets/2986122b-8da1-497a-bafe-cc69544f6753) <br /><br />
2. 하단 체크박스에서 테스트 케이스를 만들고 싶은 메서드를 선택 후 `OK 클릭`
   ![Image](https://github.com/user-attachments/assets/591f3fc4-6294-4b56-b88a-f35564646364) <br /><br />
3. 생성 완료
   ![Image](https://github.com/user-attachments/assets/170646f7-4a65-4431-ac13-5a9eec133daf)


<br />
<br />
<br />

> 💡 테스트케이스를 만들고 구현체 개발하는 방식을 => 테스트 주도 개발(TDD)라고 한다.