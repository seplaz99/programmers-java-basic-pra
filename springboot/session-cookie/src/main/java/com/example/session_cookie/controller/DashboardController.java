package com.example.session_cookie.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class DashboardController {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            @CookieValue(value = "lastVisit", required = false) String lastVisit,
            @CookieValue(value = "theme", defaultValue = "Light") String theme,
            HttpServletResponse response,
            Model model
    ) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", username);

        if (lastVisit != null) {
            long ms = Long.parseLong(lastVisit);
            String readableTime = Instant.ofEpochMilli(ms).atZone(ZoneId.systemDefault()).format(FMT);
            model.addAttribute("lastVisit", readableTime);
        }

        Cookie visitCookie = new Cookie("lastVisit", String.valueOf(System.currentTimeMillis()));
        visitCookie.setMaxAge(30 * 24 * 60 * 60);
        visitCookie.setPath("/");
        visitCookie.setHttpOnly(true);
        response.addCookie(visitCookie);

        model.addAttribute("theme", theme);

        return "dashboard";
    }

    @GetMapping("/theme")
    public String setTheme(@RequestParam String mode, HttpServletResponse response) {
        String themeValue = "dark".equals(mode) ? "dark" : "light";
        Cookie themeCookie = new Cookie("theme", themeValue);
        themeCookie.setMaxAge(30 * 24 * 60 * 60);
        themeCookie.setPath("/");
        response.addCookie(themeCookie);

        return "redirect:/dashboard";
    }
}
