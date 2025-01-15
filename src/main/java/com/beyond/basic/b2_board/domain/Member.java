package com.beyond.basic.b2_board.domain;

    //      통칭 entity

//dto --> entity : 회원가입 서비스
//entitiy --> dto : 조회
import com.beyond.basic.b2_board.dtos.MemberDetailDto;
import com.beyond.basic.b2_board.dtos.MemberListRes;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
//@AllArgsConstructor (dateTime 추가하면서 생긴 문제로 시간에 대한 생성자를 만들기 위해 해당 줄 주석처리)
@Getter
//      디버깅 용이하게 하기 위함.
@ToString

//      jpa의 엔티티매니저에게 객체를 위임하려면 @Entity어노테이션 필요.
//      jpa의 엔티티매니저가 객체를 스캔 후 수정해줌. 쿼리 생성 등등 뭐 여러가지 작업하는 것 같음.
@Entity


public class Member {
    @Id //pk설정
    //      IDENTITY :auto_increment 설정(AUTO설정은 jpa에게 적절한 전략을 위임하는 것)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //     String은 별다른 설정이 없을 경우 varchar(255)로  DB컬럼 설정. 변수명 == 컬럼명 으로 변경
    private String name;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
//    @Column(name = "pw") 이렇게 할 수는 있으나, 되도록이면 컬럼명과 변수명을 일치시키는 것이 개발의 혼선을 줄일 수 있음.
    private String password;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }


    public void updatePw(String newPassword) {
        this.password = newPassword;
    }


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
