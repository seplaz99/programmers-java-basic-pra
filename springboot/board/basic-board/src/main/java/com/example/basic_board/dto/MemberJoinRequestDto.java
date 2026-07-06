package com.example.basic_board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 요청을 담당하는 DTO
@Getter
@Setter
@NoArgsConstructor
public class MemberJoinRequestDto {
    private String userId;
    private String password;
    private String userName;
}
