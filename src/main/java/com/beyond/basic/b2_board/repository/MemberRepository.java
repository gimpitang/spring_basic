package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

//      SpringDataJpa 되기 위해서는 JpaRepository를 상속해야 하고("extends JpaRepository<Member, Long>"),
//      상속 시에 Entity명과 entity(Member)의 pk(Long)타입을 명시
//      JpaRepository를 상속함으로서 JpaRepository의 주요 기능(각종 CRUD기능 사전 구현) 상속
public interface MemberRepository extends JpaRepository<Member, Long> {
    //      save, findAll, findByID는 사전에 구현.
    //      그외에 다른 컬럼으로 조회할 때는 findBy + 컬럼명 형식으로 선언만 하면 실행 시점에 구현.

    Optional<Member> findByEmail(String email);
}
