package com.SoLAW.controller;

import com.SoLAW.model.User;
import com.SoLAW.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {

    @Autowired
    private UserService userService;

    // Menambahkan metode @GetMapping untuk signup
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User()); // Menyediakan objek User kosong untuk form binding
        return "signup/signup"; // Mengarahkan ke view signup/signup.html atau sesuai konfigurasi template Anda
    }

    // Corrected the URL mapping here
    @PostMapping("/signup")
    public String handleSignup(@Validated User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "signup/signup";
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            model.addAttribute("emailError", "Email cannot be empty");
            return "signup/signup";
        }

        try {
            if (userService.emailExists(user.getEmail())) {
                model.addAttribute("emailError", "Email is already used");
                return "signup/signup";
            }

            userService.signup(user.getEmail(), user.getPassword(), user.getUsername());
            model.addAttribute("successMessage", "User successfully registered!");
            return "signup/signup";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "signup/signup";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User()); // Pastikan objek user ditambahkan
        return "login/login";
    }

    // Corrected the URL mapping here
    @PostMapping("/login")
    public String handleLogin(@Validated User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "login/login";
        }

        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null || email.isEmpty()) {
            model.addAttribute("emailError", "Email cannot be empty");
            return "login/login";
        }

        if (password == null || password.isEmpty()) {
            model.addAttribute("passwordError", "Password cannot be empty");
            return "login/login";
        }

        try {
            if (userService.authenticate(email, password)) {
                return "redirect:/";
            } else {
                model.addAttribute("loginError", "Invalid email or password");
                return "login/login";
            }
        } catch (Exception e) {
            model.addAttribute("loginError", "An error occurred: " + e.getMessage());
            return "login/login";
        }
    }
}
