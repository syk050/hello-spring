package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        /* 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
         * 현재는 메모리 리포지트리를 사용하지만
         * 리포지트리를 바꾸게 될 때
         * 다른 코드 수정 없이 교체할 수 있다
         */
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
/* DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있다
 * 필드 주입 @Autowired private MemberRepository repository;
 *      - 커스텀? 불가능
 *
 * setter 주입
 * public void setMemberService(MemberService service) {
 *      this.service = service;
 * }
 * => service가 셋팅이 되면 중간에 바꿀 일이 없는데
 *    public으로 열려있어 위험하다
 *
 * 생성자 주입
 * @Autowired
    public MemberController(MemberService service) {

        this.service = service;
    }
 * => 생성되는 시점에 셋팅하고 이후 변경이 불가능
 * */
