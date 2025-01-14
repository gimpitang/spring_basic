package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberMemoryRepository memberMemoryRepository;


    public List<MemberListRes> findAll(){


        return memberMemoryRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());

//        //      아래보다 더 간단하게 한줄로...
//        List<Member> members = memberMemoryRepository.findAll();
//        return members.stream().map(m->m.listFromEntity()).collect(Collectors.toList());

        //아래 대신 stream를 사용할 수 있음.
//        List<MemberListRes> memberListRes =new ArrayList<>();
//        for(Member m : members){
//            memberListRes.add(m.listFromEntity());
//        }
//        return memberListRes;

    }


    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException{
        if(memberMemoryRepository.findByEmail(memberCreateDto.getEmail()).isPresent() ){
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        if (memberCreateDto.getPassword().length()<8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습ㄴ디ㅏ.");
        }

        memberMemoryRepository.save(memberCreateDto.toEntity());
    }

    public MemberDetailDto findById(Long id) throws NoSuchElementException{
//        Optional<Member> optionalMember = memberMemoryRepository.findById(id);
//        Member member = optionalMember.orElseThrow(()->new NoSuchElementException("없는 ID 입니다."));
////        MemberDetailDto dto = new MemberDetailDto(member.getName(),member.getEmail(),member.getPassword());
//        MemberDetailDto dto = member.detailFromEntity();
//
//        return dto;

        return memberMemoryRepository.findById(id).orElseThrow(()-> new NoSuchElementException("없는 아이디에요")).detailFromEntity();
    }
}
