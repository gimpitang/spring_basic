package com.beyond.basic.b2_board.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

@Data

public class MemberCreateDto {
    private String name;
    private String email;
    private String password;
}
