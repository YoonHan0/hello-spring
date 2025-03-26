## 회원 관리 예제 - 웹 MVC 개발

<br />

### 1. 회원가입 기능

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
        private String name;
    
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
    // `/members/new` 경로로 들어오게 되면 viewResolver가 createMemberForm.html을 화면에 글려줌
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
        
        System.out.println(form.getName()); // 입력된 데이터 출력됨
        memberService.join(member);

        return "redirect:/";
    }
    ```

<br />
<br />

### 2. 회원목록

1. 화면에 그려줄 파일 생성 `memberList.html`
   ```html
   <!-- ... -->
   <div>
       <table>
           <thead>
           <tr>
               <th>#</th>
               <th>이름</th>
           </tr>
           </thead>
           <tbody>
           <tr th:each="member : ${members}">       <!-- thymeleaf 문법 -->
               <td th:text="${member.id}"></td>
               <td th:text="${member.name}"></td>
           </tr>
           </tbody>
       </table>
   </div>
   <!-- ... -->
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