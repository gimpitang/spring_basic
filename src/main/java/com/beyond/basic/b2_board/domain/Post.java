package com.beyond.basic.b2_board.domain;

import com.beyond.basic.b2_board.dtos.PostCreateDto;
import com.beyond.basic.b2_board.dtos.PostDetailDto;
import com.beyond.basic.b2_board.dtos.PostListRes;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@ToString
@Getter
//      생성자가 없게 해줄 수 있는 것
//      Builder 어노테ㅔ이선을 사용하여, 빌더패턴으로 엔티티의 생성자를 구성
//      빌터패턴의 장점 : 매개변수의 순서와 개수를 유연하게 세팅할 수 있다.
@Builder // AllArgsConstructor와 함께 움직임
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 3000, nullable = false)
    private String contents;

    // post입장에서는 post가 n 이고 member 가 1이므로 ==> ManyToOne
    //      lazy(지연로딩)로 설정 시 member 객체를 사용하지 않는 한, member 테이블로 쿼리 발생하지 않음.
    //      이에 반해 eager(즉시로딩)타입으로 설정 시 사용하지 않아도 member 테이블로 쿼리 발생
    @ManyToOne(fetch = FetchType.LAZY) //       @ManyToOne 에서는 default 설정은 EAGER
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }


    public PostListRes toListDto(){
        return PostListRes.builder().id(this.id).title(this.title).build();
    }

    public PostDetailDto toDetailDto(String email){
        return PostDetailDto.builder().id(this.id).title(this.title).contents(this.contents).memberEmail(email).build();
    }

}
