package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Users.UserCreateDto;
import com.example.springdonateweb.Services.interfaces.IUsersService;
import com.example.springdonateweb.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class AuthController {

    IUsersService usersService;

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("register") UserCreateDto register) {
        String user = SecurityUtil.getSessionUser();
        if (user != null) {
            return "redirect:/"; // Redirect logged-in users to home
        } else {
            return "auth/register"; // Show registration page
        }
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("register") UserCreateDto register,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "auth/register"; // Return to the registration page with errors
        }

        // Check if user already exists
        if (usersService.existsById(register.getUserId())) {
            redirectAttributes.addFlashAttribute("existUser", "true");
            redirectAttributes.addFlashAttribute("register", register);
            return "redirect:/register"; // Redirect with error message
        }

        usersService.register(register);
        redirectAttributes.addFlashAttribute("success", "true");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        String user = SecurityUtil.getSessionUser();
        if (user != null) {
            return "redirect:/"; // Redirect logged-in users to home
        }
        return "auth/login"; // Show login page
    }
}