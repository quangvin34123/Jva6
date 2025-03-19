package com.example.duanjava6.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.duanjava6.Dao.ProductDAO;



@Controller
public class HomeController {

    @Autowired
    private ProductDAO productDAO;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productDAO.findAll());
        return "/index"; // Tên file template (index.html)
    }
}
