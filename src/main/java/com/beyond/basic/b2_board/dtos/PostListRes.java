package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostListRes {
    private Long id;
    private String title;
}