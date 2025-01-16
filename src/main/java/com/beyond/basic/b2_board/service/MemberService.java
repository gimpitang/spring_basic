package com.beyond.basic.b2_board.service;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.dtos.MemberCreateDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;

import com.beyond.basic.b2_board.dtos.MemberDetailDto;
//import com.beyond.basic.b2_board.repository.MemberJdbcRepository;
import com.beyond.basic.b2_board.dtos.MemberUpdateDto;
import com.beyond.basic.b2_board.repository.MemberJpaRepository;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
//import com.beyond.basic.b2_board.repository.MemberMybatisRepository;
import com.beyond.basic.b2_board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
//Transactional 어노테이션을 통해 모든 메서드에 트랜잭션을 적용하고, 만약 예외(unchecked exception 만) 발생 시 롤백처리 자동화
@Transactional

public class MemberService {
    @Autowired
//    private MemberMemoryRepository memberMemoryRepository;
    //      원래 MemberMemoryRepository였는데 Jdbc로 넘어가면서 아래로 바꿈
//    private MemberJdbcRepository memberRepository;
    //      아래는 마이바티스 전용
//    private MemberMybatisRepository memberRepository;
    //      아래는 Jpa 일반 전용
//    private MemberJpaRepository memberRepository;
    //      아래는 Jpa 인터페이스 전용
    private MemberRepository memberRepository;


    public List<MemberListRes> findAll(){


        return memberRepository.findAll().stream().map(m->m.listFromEntity()).collect(Collectors.toList());

//        //      아래보다 더 간단하게 한줄로...
//        List<Member> members = memberMemoryRepository.findAll();
//        return members.stream().map(m->m.listFromEntity()).collect(Collectors.toList());

        //아래 대신 stream를 사용할 수 있음.
//        List<MemberListRes> memberListRes =new ArrayList<>();
//        List<Member> members = memberMemoryRepository.findAll();
//
//        for(Member m : members){
//            memberListRes.add(m.listFromEntity());
//        }
//        return memberListRes;

    }


    public void save(MemberCreateDto memberCreateDto) throws IllegalArgumentException{
        if(memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent() ){
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        if (memberCreateDto.getPassword().length()<8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습ㄴ디ㅏ.");
        }

        memberRepository.save(memberCreateDto.toEntity());
    }

    public Member save2(MemberCreateDto memberCreateDto) throws IllegalArgumentException{
        if(memberRepository.findByEmail(memberCreateDto.getEmail()).isPresent() ){
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        if (memberCreateDto.getPassword().length()<8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습ㄴ디ㅏ.");
        }

        Member member = memberRepository.save(memberCreateDto.toEntity());
        return member;
    }

    public MemberDetailDto findById(Long id) throws NoSuchElementException, RuntimeException{
//        Optional<Member> optionalMember = memberMemoryRepository.findById(id);
//        Member member = optionalMember.orElseThrow(()->new NoSuchElementException("없는 ID 입니다."));
////        MemberDetailDto dto = new MemberDetailDto(member.getName(),member.getEmail(),member.getPassword());
//        MemberDetailDto dto = member.detailFromEntity();
//
//        return dto;

        return memberRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("없는 아이디에요")).detailFromEntity();
    }

    public void updatePw(MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findByEmail(memberUpdateDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("없는 사용자입니다."));
        member.updatePw(memberUpdateDto.getNewPassword());
        //      기존객체를 조회 후에 다시 save할 경우에는 insert가 아니라 update 쿼리 실행.
        memberRepository.save(member);
        //      사실 윗줄이 없어도 되긴 하는데 영속성때문이다...신기함ㅎㅎ
    }

    public void delete(@PathVariable Long id) throws EntityNotFoundException {
//        memberRepository.deleteById(id);
        Member member =memberRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());

        memberRepository.delete(member);
    }

}
