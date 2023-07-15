package com.spring.springbootstarter.service;

import com.spring.springbootstarter.domain.Member;
import com.spring.springbootstarter.repository.MemberRepository;
import com.spring.springbootstarter.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//데이터를 저장하거나 변경할땐 항상 Transactional안에서 실행되어야 한다.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

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
