package com.spring.springbootstarter;

import com.spring.springbootstarter.aop.TimeTraceAop;
import com.spring.springbootstarter.repository.*;
import com.spring.springbootstarter.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//수동으로 스프링빈을 컨테이너에 등록하고 사용하는 방법
@Configuration //설정클래스라고 명시해주는 annotation
public class springConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public springConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }


//    @Autowired
//    private final DataSource dataSource;
//    public springConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    private EntityManager entityManager;
//
//    @Autowired
//    public springConfig(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Bean //스프링 컨테이너에 등록하기 위한 annotation
//    public MemberService memberService(){
//        //memberRepository()->MemberService에 주입됨.
//        return new MemberService(memberRepository());
//    }
//
//    @Bean //MemberRepository 메소드에서 new MemoryMemberRepository 인스턴스를 생성
//    public MemberRepository memberRepository(){
//
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(entityManager);
//    }
}
