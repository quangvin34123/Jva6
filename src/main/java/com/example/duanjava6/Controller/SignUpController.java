package com.example.duanjava6.Controller;

import com.example.duanjava6.Entity.User;
import com.example.duanjava6.Dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SignUpController {
    
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hiển thị form đăng ký
    @GetMapping("/signup")
    public String showSignUpPage(@ModelAttribute("user") User user) {
        return "signup"; // Trả về signup.html
    }

    // Xử lý form đăng ký
    @PostMapping("/signup")
    public String processSignUp(@ModelAttribute("user") User user, Model model) {
        // Kiểm tra xem email đã tồn tại chưa
        if (userDAO.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "signup";
        }
        
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Role mặc định là "user" (đã được set trong entity)
        // createdAt mặc định là LocalDateTime.now() (đã được set trong entity)
        userDAO.save(user);
        return "redirect:/login"; // Chuyển hướng sau khi đăng ký thành công
    }
}