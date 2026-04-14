package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final List<User> userList = new ArrayList<>();

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, 
                               @RequestParam("password") String password, 
                               Model model) {
        if ("admin".equals(username) && !password.isEmpty()) {
            return "redirect:/home";
        }
        
        model.addAttribute("error", "Kredensial tidak valid.");
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("users", userList);
        return "home";
    }

    @GetMapping("/form")
    public String showFormPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/form")
    public String submitDataMahasiswa(@ModelAttribute User user) {
        userList.add(user);
        return "redirect:/home";
    }
}