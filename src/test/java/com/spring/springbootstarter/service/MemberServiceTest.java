package com.spring.springbootstarter.service;

import com.spring.springbootstarter.domain.Member;
import com.spring.springbootstarter.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 뭔가 주어짐
        Member member = new Member();
        member.setName("해적왕");

        //when 실행했을때
        Long saveId = memberService.join(member);

        //then 이런결과가 나와야함
        Member findMemberId = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMemberId.getName());


    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        Member member2 = new Member();
        member2.setName("spring1");
        //when
        memberService.join(member1);
        assertThrows(NullPointerException.class, ()-> memberService.join(member2));

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //assertThat(e.getMessage()).isEqulTo("이미 존재하는 회원입니다.");

         /*try {
            memberService.join(member2);
            //fail();
        }catch (IllegalStateException e){
            //assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            assertThat(e.getMessage()).isEqualTo("이미 존재안하는 회원입니다.");
        }*/


        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}