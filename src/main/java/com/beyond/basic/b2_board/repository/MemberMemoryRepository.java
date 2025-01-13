package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberMemoryRepository {
    private List<Member> memberList = new ArrayList<>();

    public List<Member> findAll(){
        return this.memberList;
    }

    public void save(Member member){
        this.memberList.add(member);
    }
}
