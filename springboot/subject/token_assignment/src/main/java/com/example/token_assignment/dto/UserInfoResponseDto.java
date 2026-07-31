package com.example.token_assignment.dto;

import com.example.token_assignment.domain.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponseDto {

    private long id;
    private String userId;
    private String userName;
    private Role role;
}
