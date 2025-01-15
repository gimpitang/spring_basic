//package com.beyond.basic.b2_board.repository;
//
//import com.beyond.basic.b2_board.domain.Member;
//import org.apache.ibatis.annotations.Mapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
////      해당 레파지토리를 mybatis 레파지토리로 만들겠다는 어노테이션
//@Mapper
//public interface MemberMybatisRepository {
//    //      mybatis 레파지토리에서는 필요한 메서드명(리턴타임, 매개변수)만 정의하면 된다,
//
//    List<Member> findAll();
//
//    Optional<Member> findById( Long id);
//
//    Optional<Member> findByEmail(String email);
//
//    void save(Member member);
//
//}
