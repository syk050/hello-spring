package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    /* static
     * static 은 어떤 멤버변수 혹은 메서드가 '인스턴스'에 소속되지 않고 '클래스'에 속하게 됨
     * 간단하게 이해하면, 애플리케이션 내에서 유일하게 딱 하나만 존재하는 변수가 됩니다
     * 하지만 애플리케이션이 전체가 하나의 변수를 사용하는 셈이라
     * 누가 언제 값을 변경할지 모른다는 위험도 있습니다. */
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null이 반환될 가능성이 있으면 Optional로 감싸서 전달
        // 이후 클라이언트에서 조작 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾으면 그것을 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
