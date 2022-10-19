package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 스프링 컨테이너에 등록하여 스프링이 관리
public class MemberController {

    /* 스프링 컨테이너에 등록하여 컨테이너에 받아와 사용
     * 여러 컨트롤러에서 service를 사용할 텐데
     * 구지 여러 개를 만들 필요가 없음
     * 하나만 생성하여 공용으로 사용하면 됨 */
//    private final MemberService service = new MemberService();

    // 스프링 컨테이너에 등록하기
    private final MemberService service;

    // @Autowired 컨테이너에 등록된 service를 연결해줌
    // MemberService @Service 어노테이션을 추가해서 등록
    @Autowired
    public MemberController(MemberService service) {

        this.service = service;
    }
}
