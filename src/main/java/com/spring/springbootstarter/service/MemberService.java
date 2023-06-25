package com.spring.springbootstarter.service;

import com.spring.springbootstarter.domain.Member;
import com.spring.springbootstarter.repository.MemberRepository;
import com.spring.springbootstarter.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //스프링컨테이너에 등록하고 서비스 레이어에 서비스라고 인식시켜줌
public class MemberService {

    private final MemberRepository memberRepository;
    //마찬가지로 서비스 객체가 생성될때 Repository를 주입해줌
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->
                {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 한명조회
     */
    public Optional<Member> findOne(Long memberid){
        return memberRepository.findById(memberid);
    }

}
