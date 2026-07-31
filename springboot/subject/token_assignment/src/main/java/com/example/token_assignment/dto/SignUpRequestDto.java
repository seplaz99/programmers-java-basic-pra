package com.example.token_assignment.dto;

import com.example.token_assignment.domain.entity.Role;
import com.example.token_assignment.domain.entity.User;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String userId;
    private String password;
    private String userName;
    private Role role;

    public User toUser(String encodedPassword) {
        return User.builder()
                .name(userName)
                .userId(userId)
                .password(encodedPassword)
                .role(role != null ? role : Role.ROLE_USER)
                .build();
    }
}
