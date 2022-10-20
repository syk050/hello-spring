package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private  final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist: 영속하다 영구 저장하다
        em.persist(member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 조회할 타입, id
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> list = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return list.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 멤버 엔티티를 대상으로 쿼리를 날림
        // select *이 아닌 select m 엔티티 자체를 조회
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
