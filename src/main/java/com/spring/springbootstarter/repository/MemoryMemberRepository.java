package com.spring.springbootstarter.repository;

import com.spring.springbootstarter.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member>store= new HashMap<>();// 멤버를 저장할 Map
    private static long sequence= 0L; //순번

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //증가된 아이디값
        //아이디를 key로 member객체를 hashmap 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 널체크 후에 아이디값을 꺼내옴
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //리턴값을 일어온다음, filter로 member객체의 getName이 == name이면
        //리턴값중에 가장 빨리 걸리는거 찾아와라
        return  store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //다찾으려면 Store의 모든 값을 arrayList에 넣어서 전달.
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
