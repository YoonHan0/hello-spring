## 테스트하는 방법

- 직접 생성 ( 클래스 참고해서 설명 추가 필요.. )
- 단축키 사용(클래스에서 command+shift+t) ( 강의 들으면서 추가 필요... )

### 직접 생성하는 방법 `MemoryMemberRepositoryTest.java`
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
   
            Member result = repository.findById(member.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당 ID의 회원을 찾을 수 없습니다: " + member.getId()));

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


### 단축키 사용하는 방법 `MemberServiceTest.java`