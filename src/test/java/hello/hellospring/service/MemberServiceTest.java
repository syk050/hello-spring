package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /* 현재 repository store가 static이라 클래스에 속한 상태
     * service에서의 repository와 test에서의 repository가 동일한 store를 접근하지만
     * 만약 static이 아니라면
     * service에서 repository와 test의 repository가 다른 store를 접근하게 된다 */
    // MemberService service = new MemberService();
    // MemoryMemberRepository repository = new MemoryMemberRepository();

    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public  void beforeEach() {
        repository = new MemoryMemberRepository();
        // repository를 외부에서 넣어 주도록 변경
        // Dependency Injection (DI)
        service = new MemberService(repository);

    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 뭔가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        //when 그걸 실행하면
        Long saveid = service.join(member);

        //then 바라는 결과
        /* 테스트는 정상 플로우도 중요하지만
         * 예외 플로우도 중요하다 */
        Member findMember = service.findOne(saveid).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        /* 방법 1
        service.join(member1);
        try {
            service.join(member2);
            fail();
        }catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/

        service.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

    @Test
    void findMembers() {
        //given

        //when

        //then
    }

    @Test
    void findOne() {
        //given

        //when

        //then
    }
}
