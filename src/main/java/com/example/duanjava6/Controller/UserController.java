package com.example.duanjava6.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.duanjava6.Dao.UserDAO;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    public String listUsers(Model model) {
        System.out.println("Số lượng người dùng: " + userDAO.findAll().size());
        model.addAttribute("users", userDAO.findAll());
        return "user-list"; // Tên file template: user-list.html
    }
}
