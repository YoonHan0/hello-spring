# 🧑‍💻 회원 관리 예제 - 웹 MVC 개발

<br />

공부한 내용을 바탕으로 간단한 예제를 만들어 보겠습니다 :) <br />
회원가입을 하고 회원 목록을 확인할 수 있는 간단한 프로그램입니다.

## 🖥️ 결과화면

### 메인화면
![Image1](https://github.com/user-attachments/assets/cc59cdf5-03be-4a49-8014-dc91041f7868)

### 회원가입 화면
![Image](https://github.com/user-attachments/assets/48d920f7-2f4e-48fc-877b-c140d20c953a)

### 회원 목록 화면
![Image](https://github.com/user-attachments/assets/39b56efb-f2c8-4737-8d2a-2fda2e880ad0)

<br />

---
## ⚙️ 기능 구현

### 1. ("/") 경로에 접근했을 때 출력될 메인 화면 개발
![Image1](https://github.com/user-attachments/assets/cc59cdf5-03be-4a49-8014-dc91041f7868)

1. `home.html` 파일 생성
```html
 <!DOCTYPE HTML>
 <html xmlns:th="http://www.thymeleaf.org">
 <body>

 <div class="container">
     <div>
         <h1>Hello Spring</h1>
         <p>회원 기능</p>
         <p>
             <a href="/members/new">회원 가입</a>
             <a href="/members">회원 목록</a>
         </p>
     </div>
 </div> <!-- container -->
 </body>
 </html>
```
2. 웹 브라우저에서 `localhost:8080`으로 요청이 왔을 때 맵핑될 메서드 구현
```java
/* MemberController */
@Controller
public class MemberController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```


---

<br />

### 2. ("/member/new") 경로에 접근했을 때 출력될 회원가입 화면 개발

![Image](https://github.com/user-attachments/assets/48d920f7-2f4e-48fc-877b-c140d20c953a)

1. 화면에 그려줄 파일 생성 `resourcces/templates/members/createMemberForm.html` 생성
    ```html
    <!-- ... -->
    <form action="/members/new" method="post">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="이름을 입력하세요.">
        </div>
        <button type="submit">등록</button>
    </form>
    <!-- ... -->
    ```
2. form 태그에서 처리하는 데이터 받아줄 model 생성 `MemberForm` 생성
    ```java
    public class MemberForm {
        private String name;        // 이름을 입력하면 해당 name 필드에 값이 저장되게 됩니다.
    
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    ```
3. `MemberController` 메서드 추가 
   ```java

   @Controller
   public class MemberController {

     private final MemberService memberService;

     @Autowired  // 의존성 주입 : DI, 연결하는 로직
     public MemberController(MemberService memberService) {
         this.memberService = memberService;
     }
   
     // `/members/new` 경로로 들어오게 되면 viewResolver가 방금 만든 createMemberForm.html을 화면에 그려줌
     @GetMapping("/members/new")
     public String createForm() {
         return "members/createMemberForm"; 
     }
    
     // createMemberForm 화면에서 "등록" 버튼 클릭 시 post 방식으로 호출되면서 create 메서드 호출
     // 파라미터로 MemberForm 클래스를 받으면서 html파일에서 submit한 name 데이터가 MemberForm 클래스의 name 필드로 받아짐
     @PostMapping("/members/new")
     public String create(MemberForm form) {
         Member member = new Member();
         member.setName(form.getName());
         
         System.out.println(form.getName()); // 입력한 데이터 확인
         memberService.join(member);
 
         return "redirect:/";
     }
   }
    ```
4. `MemberService` 메서드 구현
   ```java
   
   /* Service는 비지니스적으로 함수명을 작성하던지 해야합니다. 
   :. Service가 비지니스 처리를 하는 곳이기도 하고 추후에 어떤 문제가 생겨서 오류를 찾을 때도 찾기 편하기 때문에 */
   @Service
   public class MemberService {

      private final MemberRepository memberRepository;
   
      // 클래스 내에 생성자가 하나만 있으면 @Autowired 생략 가능(Spring 4.3 이상)
      public MemberService(MemberRepository memberRepository) {
           this.memberRepository = memberRepository;
      }
      /**
      * > 회원가입
      * 같은 이름이 있는 중복 회원X
      */
      public Long join(Member member) {
           // 요즘에는 null로 반환될 수 있을법한 로직에는 "변수 != null" 이렇게 처리하지 않고 Optional로 감싸서 처리
           // ex) Optional<Member> result = memberRepository.findByName(member.getName());
           validateDuplicateMember(member);    // 중복 회원 검증
           memberRepository.save(member);
   
           return member.getId();
      }
      /* 회원명으로 중복체크하는 메서드 */
      private void validateDuplicateMember(Member member) {
          // isPresent(): Optional이 값을 포함하고 있는지 확인만 하는 메서드, if인데 is로 오타가 나서 오류가 났었음..
          // ifPresent()는 Optional에 값이 존재하면 람다식을 실행하는 메서드이므로, 여기서 예외를 던지는 로직을 넣을 수 있습니다.
          memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
      }
   }
   ```
   
5. `MemberRepository` 생성 <br />
   인터페이스를 활용하여 여러 클래스가 동일한 메서드를 구현하도록 강제하여 일관성을 유지할 수 있음
   ```java
   public interface MemberRepository {
       Member save(Member member);
       Optional<Member> findById(Long id);
       Optional<Member> findByName(String name);
       List<Member> findAll();
   }
   ```

6. 구현부 `MemoryMemberRepository` 생성 <br />
   데이터베이스를 연결하기 전이므로 메모리에 데이터를 저장하고 조회하는 방식으로 진행하겠습니다.
   ```java
   /* MemberRepository 인터페이스를 구현하는 클래스 */
   @Repository
   public class MemoryMemberRepository implements MemberRepository{
   
       private static Map<Long, Member> store = new HashMap<>();    // 메모리 기반 데이터 저장소로 활용
       private static long sequence = 0L;
   
       @Override
       public Member save(Member member) {
           member.setId(++sequence);
           store.put(member.getId(), member);
           return member;
       }
   
       @Override
       public Optional<Member> findByName(String name) {
           return store.values().stream()
               .filter(member -> member.getName().equals(name))
               .findAny();
       }
   }
   ```

---

<br />

### 2. ("/members") 경로에 접근했을 때 출력될 회원목록 화면 개발

![Image](https://github.com/user-attachments/assets/39b56efb-f2c8-4737-8d2a-2fda2e880ad0)

1. 화면에 회원 목록을 그려줄 파일 생성 `memberList.html`
   ```html
   <!DOCTYPE HTML>
   <html xmlns:th="http://www.thymeleaf.org">
   <body>

   <div class="container">
       <div>
           <table>
               <thead>
               <tr>
                   <th>#</th>
                   <th>이름</th>
               </tr>
               </thead>
               <tbody>
               <tr th:each="member : ${members}">   <!-- thymeleaf 문법 -->
                   <td th:text="${member.id}"></td>
                   <td th:text="${member.name}"></td>
               </tr>
               </tbody>
           </table>
       </div>
   </div> <!-- container -->
   </body>
   </html>
   ```
2. `MemberController`에 메서드 추가
   ```java
   @GetMapping("/members")      // home.html의 경로 <a href="/members">회원 목록</a>
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);     // key: members, value: members(변수)
   
        return "members/memberList";
    }
   ```
3. `MemberService` 클래스의 `findMembers()` 메서드 구현
   ```java
    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
   ```
4. `MemoryMemberRepository` 클래스의 `findAll()` 메서드 구현
   ```java
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // 메모리에 저장된 데이터 반환 
    }
   ```
   
---

<br />

여기까지 완료되면 예제 프로그램을 개발하게 됩니다 :) <br />
코드 중간중간에 나오는 [의존성 주입](https://github.com/YoonHan0/hello-spring/blob/main/memo/Bean1.md), [@Controller, @Service, @Repository](https://github.com/YoonHan0/hello-spring/blob/main/memo/Bean1.md), [인터페이스](https://github.com/YoonHan0/hello-spring/blob/main/memo/interface.md)와 같은 내용들은 <br />
링크를 참고해서 공부해 주시면 감사하겠습니다 👍
