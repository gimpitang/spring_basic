package com.beyond.basic.b2_board.domain;

    //      통칭 entity

//dto --> entity : 회원가입 서비스
//entitiy --> dto : 조회
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
//      디버깅 용이하게 하기 위함.
@ToString


public class Member {

    private Long id;
    private String name;
    private String email;
    private String password;

    //      findAll() 간소화 하기 위한 작업 아래 주석 코드를 안만들게 하기 위함임.
    //      Service에서 안하는 이유는 Member에 있는 객체들이기때문에 떙기기 쉽고 서비스에서는 Member객체를 새로 선언해야함.
    //      MemberListRes m1 = new MemberListRes(m.getId(),m.getName(),m.getEmail());
    public MemberListRes listFromEntity(){
        return new MemberListRes(this.id, this.name, this.getEmail());
    }
    public MemberDetailDto detailFromEntity(){
        return new MemberDetailDto(this.name, this.email, this.password);
    }

}
