package com.example.formlogin.config.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// CustomUserDetails를 만드는 이유
// security의 인증 검증(DaoAuthenticationProvider)은 우리의 User엔티티를 모른다.
// 대신 UserDetails라는 "표준 인터페이스"로만 사용자 정보를 주고받는다.
// - getUsername()/getPassword() -> 사용자ID/비밀번호 대조에 사용
// - getAuthorities() -> 인가(권한 판단)에 사용
// 즉 이 클래스는 우리 도메인(User 엔티티)과 security 사이의 "어댑터"이다.
// UserDetailService.loadUserByUsername()이 DB에서 조회한 User를 이걸로 감싸서 반환하면,
// 이후에 비밀번호 대조/권한 판단은 전부 이 객체를 통해 이루어진다.

public class CustomUserDetails implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
