package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.repository.MemberMemoryRepository;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

@Data

public class MemberCreateDto {
    private String name;
    private String email;
    private String password;


    //      toEntity: 멤버 서비스에 아래 주석을 간소화 히기 위한 것.

//    Member member = new Member(MemberMemoryRepository.id ,memberCreateDto.getName(),
//            memberCreateDto.getEmail(), memberCreateDto.getPassword());

    public Member toEntity(){

//        return new Member(MemberMemoryRepository.id, this.name, this.email, this.password);
        //      jdbc 이후에는 MemberMemoryRepository.id필요 없어짐)
        return new Member(this.name, this.email, this.password);
    }
//    MemberMemoryRepository.id 이건 db 에서 갖고올 때에는 문제 안생기는데 지금 상황에서 어쩔 수 없이 호출함.

}
