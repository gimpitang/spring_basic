package com.beyond.basic.b2_board.dtos;

import com.beyond.basic.b2_board.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberUpdateDto {
    private String email;
    private String newPassword;



}
