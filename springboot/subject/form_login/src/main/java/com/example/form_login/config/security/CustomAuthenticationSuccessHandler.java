package com.example.form_login.config.security;

import com.example.form_login.domain.entity.User;
import com.example.form_login.dto.SignInResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getName());

        SignInResponseDto builder = SignInResponseDto.builder()
                .isLoggedIn(true)
                .url("/")
                .userName(user.getName())
                .userId(user.getUserId())
                .message("로그인 성공")
                .build();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(builder));
    }
}
