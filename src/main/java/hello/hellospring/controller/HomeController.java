package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        // 컨트롤러가 정적 파일보다 우선순위가 높다
        // 그래서 index.html 파일이 아닌 home.html이 호출된다
        return "home";
    }
}
