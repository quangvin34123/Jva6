package com.example.duanjava6.Controller;

import com.example.duanjava6.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@ModelAttribute("user") User user, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/"; // Nếu đã đăng nhập, về trang chủ
        }
        return "login"; // Trả về template login.html
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/"; // Chuyển hướng về http://localhost:8080/login?logout
    }
}