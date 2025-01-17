package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Member;
import com.beyond.basic.b2_board.domain.Post;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostCreateDto {
    @NotEmpty

    private String title;
    private String contents;
    private Long memberId;


    public Post toEntity(Member member) {
        //      빌터패턴은 엔티티명.builder().사이에 원하는 변수 세팅.build();
//        return new Post(this.title,this.contents,this.memberId);
        return Post.builder().title(this.title).contents(this.contents).member(member).build();
    }


}
