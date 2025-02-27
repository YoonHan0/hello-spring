package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /* 1. 정적컨텐츠 방식 */
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!");
        return "hello";     // resources/templates/hello.html 을 찾아서 반환해줌
    }

    /* 2. 템플릿 엔진 방식 */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /* 3-1. API 방식 (반환 형식이 문자일 때) */
    @GetMapping("hello-string")
    @ResponseBody       // HTML이 아닌 HTTP에서 Head/Body의 그 body 부분에 내가 직접 값을 넣어주겠다~ 라는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    /* 3-2. API 방식 (반환 형식이 JSON일 때) */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static public class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
