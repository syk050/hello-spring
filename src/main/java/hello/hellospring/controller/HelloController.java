package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");

        /* 컨트롤러에서 리턴 값으로 문자를 반환하면
         * 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
         * resources: templates/ +{ViewName}+ .html    */
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 응답 바디부분에 데이터를 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        // 뷰 없이 데이터가 그대로 전송
        // @ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody  // default JSON 형태로 반환
    public  Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }


    static class Hello {
        // alt+insert getter setter 생성
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
