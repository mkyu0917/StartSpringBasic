package com.spring.springbootstarter.repository;

import com.spring.springbootstarter.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//저장로직 끝 자동으로 인서트 쿼리생성 set가지 다해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //타입이랑 식별자만 넣어주면 조회됨
        return Optional.ofNullable(member);
    }

    // pk기반은 이나 단건은 jpa만으로 가능하지만 나머지 것들은 jpql이라는 쿼리를 작성해줘야함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //select시 member 객체자체를 셀렉트해옴,
        return em.createQuery("select m from Member m", Member.class).getResultList();
    };
}
